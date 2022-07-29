package esprit.pi.SoftIB.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import esprit.pi.SoftIB.enumeration.Housing;
import esprit.pi.SoftIB.enumeration.Job;
import esprit.pi.SoftIB.enumeration.LoanType;
import esprit.pi.SoftIB.enumeration.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditRisk {
	private Integer age;
	private Sex sex; 
	private Job job;

    @Enumerated(EnumType.STRING)
	private Housing housing;

	private Float saving;
	private Float current; 
	private Float amount;
	private Integer duration; 
	private LoanType purpose;


}
