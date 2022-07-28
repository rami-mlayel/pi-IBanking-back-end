package esprit.pi.SoftIB.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import esprit.pi.SoftIB.entities.CreditRisk;
import esprit.pi.SoftIB.enumeration.Housing;
import esprit.pi.SoftIB.enumeration.Job;
import esprit.pi.SoftIB.enumeration.LoanType;
import esprit.pi.SoftIB.enumeration.Sex;
import esprit.pi.SoftIB.repository.LoanRequestRepository;
import esprit.pi.SoftIB.repository.UserRepository;

@Service
public class CreditRiskServiceImpl implements ICreditRiskService {
	@Autowired
	private LoanRequestRepository loanRequestRepository;
	@Override
	public String GetRisk(CreditRisk creditRisk) throws IOException, InterruptedException {
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
		predictionData.add(Math.round(creditRisk.getSaving()));
		predictionData.add(Math.round(creditRisk.getCurrent()));
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

		
		return response.body()+"%";
	}

}
