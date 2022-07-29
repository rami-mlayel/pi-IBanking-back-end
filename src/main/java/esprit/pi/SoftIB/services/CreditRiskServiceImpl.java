package esprit.pi.SoftIB.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.Period;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

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

}
