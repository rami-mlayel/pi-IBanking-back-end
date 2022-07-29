package esprit.pi.SoftIB.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import esprit.pi.SoftIB.entities.CreditRisk;

public interface ICreditRiskService {
	String getRisk(CreditRisk creditRisk) throws JsonProcessingException, IOException, InterruptedException;
    String getRiskByLoanRequestId(Long id) throws IOException, InterruptedException;
    String generateTrainingData() throws IOException;
}
