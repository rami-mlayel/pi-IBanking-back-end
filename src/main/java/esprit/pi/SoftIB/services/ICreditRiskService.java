package esprit.pi.SoftIB.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import esprit.pi.SoftIB.entities.CreditRisk;
import esprit.pi.SoftIB.enumeration.Housing;
import esprit.pi.SoftIB.enumeration.Job;
import esprit.pi.SoftIB.enumeration.LoanType;
import esprit.pi.SoftIB.enumeration.Sex;

public interface ICreditRiskService {
	String GetRisk(CreditRisk creditRisk) throws JsonProcessingException, IOException, InterruptedException;

}
