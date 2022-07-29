package esprit.pi.SoftIB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import esprit.pi.SoftIB.entities.CreditRisk;
import esprit.pi.SoftIB.services.ICreditRiskService;

@Controller
public class CreditRiskController {
	@Autowired
	private ICreditRiskService creditRiskService;
	@PostMapping(value = "/CreditRisk")
	public ResponseEntity getCreditRisk(@RequestBody CreditRisk creditRisk) {
		String risk=null;
		try {
			risk=creditRiskService.getRisk(creditRisk);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(risk);
	}
	
	@PostMapping(value = "/LoanCreditRisk/{id}")
	public ResponseEntity getLoanCreditRisk(@PathVariable Long id) {
		String risk = null;
		try {
			risk=creditRiskService.getRiskByLoanRequestId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(risk);
	}
	@GetMapping(value = "/GenerateTrainingData")
	public ResponseEntity generateTrainingData() {
		String base64String = null;
		try {
			base64String=creditRiskService.generateTrainingData();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(base64String);
	}
	
	


}
