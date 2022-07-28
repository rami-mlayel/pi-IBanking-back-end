package esprit.pi.SoftIB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import esprit.pi.SoftIB.entities.CreditRisk;
import esprit.pi.SoftIB.entities.User;
import esprit.pi.SoftIB.services.ICreditRiskService;

@Controller
public class CreditRiskController {
	@Autowired
	private ICreditRiskService creditRiskService;
	@PostMapping(value = "/GetCreditRisk")
	public ResponseEntity getCreditRisk(@RequestBody CreditRisk creditRisk) {
		String risk=null;
		try {
			risk=creditRiskService.GetRisk(creditRisk);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(risk);
	}
	
	


}
