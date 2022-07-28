package esprit.pi.SoftIB.entities;

import javax.persistence.Entity;

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
	private Housing housing; 
	private Float saving;
	private Float current; 
	private Float amount;
	private Integer duration; 
	private LoanType purpose;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Housing getHousing() {
		return housing;
	}
	public void setHousing(Housing housing) {
		this.housing = housing;
	}
	public Float getSaving() {
		return saving;
	}
	public void setSaving(Float saving) {
		this.saving = saving;
	}
	public Float getCurrent() {
		return current;
	}
	public void setCurrent(Float current) {
		this.current = current;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public LoanType getPurpose() {
		return purpose;
	}
	public void setPurpose(LoanType purpose) {
		this.purpose = purpose;
	}

}
