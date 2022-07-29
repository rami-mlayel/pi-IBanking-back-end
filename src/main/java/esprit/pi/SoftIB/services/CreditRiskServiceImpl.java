package esprit.pi.SoftIB.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

import esprit.pi.SoftIB.entities.Account;
import esprit.pi.SoftIB.entities.CreditRisk;
import esprit.pi.SoftIB.entities.LoanRequest;
import esprit.pi.SoftIB.enumeration.AccountType;
import esprit.pi.SoftIB.enumeration.Housing;
import esprit.pi.SoftIB.enumeration.Job;
import esprit.pi.SoftIB.enumeration.LoanType;
import esprit.pi.SoftIB.enumeration.Sex;
import esprit.pi.SoftIB.repository.LoanRequestRepository;

@Service
public class CreditRiskServiceImpl implements ICreditRiskService {
	@Autowired
	private LoanRequestRepository loanRequestRepository;
	@Override
	public String getRisk(CreditRisk creditRisk) throws IOException, InterruptedException {
		List<Integer> predictionData = new ArrayList<>();
		predictionData.add(creditRisk.getAge());
		if(creditRisk.getSex()==Sex.MALE) {	predictionData.add(1);}
		if(creditRisk.getSex()==Sex.FEMALE) {	predictionData.add(0);}
		if(creditRisk.getJob()==Job.NONRESIDENT) {	predictionData.add(0);}
		if(creditRisk.getJob()==Job.RESIDENT) {	predictionData.add(1);}
		if(creditRisk.getJob()==Job.EXPERIMENTEDRESIDENT) {	predictionData.add(2);}
		if(creditRisk.getJob()==Job.OWNER) {	predictionData.add(3);}
		if(creditRisk.getHousing()==Housing.FREE) {	predictionData.add(0);}
		if(creditRisk.getHousing()==Housing.RENT) {	predictionData.add(1);}
		if(creditRisk.getHousing()==Housing.OWN) {	predictionData.add(2);}
		//predictionData.add(Math.round(creditRisk.getSaving()));
		if(creditRisk.getSaving()<1000) {	predictionData.add(0);}
		if(creditRisk.getSaving()>=1000 && creditRisk.getSaving()<10000 ) {	predictionData.add(1);}
		if(creditRisk.getSaving()>=10000 && creditRisk.getSaving()<20000 ) {	predictionData.add(2);}
		if(creditRisk.getSaving()>=20000) {	predictionData.add(3);}
		if(creditRisk.getCurrent()<1000) {	predictionData.add(0);}
		if(creditRisk.getCurrent()>=1000 && creditRisk.getCurrent()<10000 ) {	predictionData.add(1);}
		if(creditRisk.getCurrent()>=10000 && creditRisk.getCurrent()<20000 ) {	predictionData.add(2);}
		if(creditRisk.getCurrent()>=20000) {	predictionData.add(3);}
		//predictionData.add(Math.round(creditRisk.getCurrent()));
		predictionData.add(Math.round(creditRisk.getAmount()));
		predictionData.add(creditRisk.getDuration());
		if(creditRisk.getPurpose()==LoanType.PERSONAL) {predictionData.add(7);}
		if(creditRisk.getPurpose()==LoanType.MORTGAGE) {predictionData.add(0);}
		if(creditRisk.getPurpose()==LoanType.CAR) {	predictionData.add(1);}
        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(predictionData);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:5000/api"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

		
		return response.body().concat("%");
	}
	@Override
	public String getRiskByLoanRequestId(Long id) throws IOException, InterruptedException {
		LoanRequest loanRequest= loanRequestRepository.findById(id).get();
		CreditRisk risk= new CreditRisk();
		Date userBD=loanRequest.getAccount().getUserAccount().getCustomer().getBirthDate();
		LocalDate today= LocalDate.now();
		LocalDate date= userBD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		risk.setAge(Period.between(date, today).getYears());
		risk.setSex(loanRequest.getAccount().getUserAccount().getCustomer().getSex());
		risk.setJob(loanRequest.getAccount().getUserAccount().getCustomer().getJobStatus());
		risk.setHousing(loanRequest.getHousing());
		List<Account> accounts =loanRequest.getAccount().getUserAccount().getCustomer().getUser().getAccounts() ;
		Float savings=0F;
		Float currents=0F;
		for(Account account : accounts) {
			if( account.getType()==AccountType.SAVING_ACCOUNT) {savings=savings+account.getBalance().floatValue();}
			if( account.getType()==AccountType.CURRENT_ACCOUNT) {currents=currents+account.getBalance().floatValue();}
		}
		risk.setSaving(savings);
		risk.setCurrent(currents);
		risk.setAmount(loanRequest.getSum().floatValue());
		risk.setDuration(loanRequest.getMonthDuration());
		risk.setPurpose(loanRequest.getPurpose());
		return this.getRisk(risk);
	}
	@Override
	public String generateTrainingData() throws IOException {
		CSVWriter csvWriter = new CSVWriter(new FileWriter("c:\\test\\data.csv"));
        String[] header = { "Age", "Sex", "Job", "Housing", "Saving accounts", "Checking account", "Credit amount", "Duration", "Purpose", "Risk" };
        csvWriter.writeNext(header);
		List<LoanRequest> requests= loanRequestRepository.findAll();
		for(LoanRequest loanRequest : requests) {
			String[] record= new String[10];
			List<String> recordList= new ArrayList<>();			
			Date userBD=loanRequest.getAccount().getUserAccount().getCustomer().getBirthDate();
			LocalDate today= LocalDate.now();
			LocalDate date= userBD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			recordList.add(((Integer) Period.between(date, today).getYears()).toString());
			recordList.add(loanRequest.getAccount().getUserAccount().getCustomer().getSex().toString().toLowerCase());
			Job job=loanRequest.getAccount().getUserAccount().getCustomer().getJobStatus();
			if(job==Job.NONRESIDENT) {recordList.add("0");}
			if(job==Job.RESIDENT) {recordList.add("1");}
			if(job==Job.EXPERIMENTEDRESIDENT) {recordList.add("2");}
			if(job==Job.OWNER) {recordList.add("3");}
			recordList.add(loanRequest.getHousing().toString().toLowerCase());
			List<Account> accounts =loanRequest.getAccount().getUserAccount().getCustomer().getUser().getAccounts() ;
			Float savings=0F;
			Float currents=0F;
			for(Account account : accounts) {
				if( account.getType()==AccountType.SAVING_ACCOUNT) {savings=savings+account.getBalance().floatValue();}
				if( account.getType()==AccountType.CURRENT_ACCOUNT) {currents=currents+account.getBalance().floatValue();}
			}
			if(savings<1000) {	recordList.add("little");}
			if(savings>=1000 && savings<10000 ) {	recordList.add("moderate");}
			if(savings>=10000 && savings<20000 ) {	recordList.add("quite rich");}
			if(savings>=20000) {	recordList.add("rich");}
			if(currents<1000) {	recordList.add("little");}
			if(currents>=1000 && currents<10000 ) {	recordList.add("moderate");}
			if(currents>=10000 && currents<20000 ) {	recordList.add("quite rich");}
			if(currents>=20000) {	recordList.add("rich");}
			recordList.add(loanRequest.getSum().toString());
			recordList.add(( (Integer) loanRequest.getMonthDuration()).toString());
			LoanType purpose=loanRequest.getPurpose();
			if(purpose==LoanType.CAR) {recordList.add("car");}
			if(purpose==LoanType.PERSONAL) {recordList.add("vacation/others");}
			if(purpose==LoanType.MORTGAGE) {recordList.add("business");}
			if(loanRequest.getLoanRequestStatus().equals("APPROVED")) {
			    recordList.add("good");
                csvWriter.writeNext(recordList.toArray(record));
			}
			if(!(loanRequest.getLoanRequestStatus().equals("REFUSED"))) {
			    recordList.add("bad");
                csvWriter.writeNext(recordList.toArray(record));}
			}
    byte[] byteData = Files.readAllBytes(Paths.get("c:\\test\\data.csv"));
    String base64String = Base64.getEncoder().encodeToString(byteData);
	return base64String;
	}

}
