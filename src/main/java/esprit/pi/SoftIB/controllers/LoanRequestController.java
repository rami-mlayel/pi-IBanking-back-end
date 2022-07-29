package esprit.pi.SoftIB.controllers;

import esprit.pi.SoftIB.entities.LoanRequest;
import esprit.pi.SoftIB.services.LoanRequestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("loan")
public class LoanRequestController {

    @Autowired
    private LoanRequestServiceImpl loanRequestService;

    @PostMapping(value = "/request/add")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity addLoanRequest(@RequestBody LoanRequest loanRequest) {
        try {
            return ResponseEntity.ok(loanRequestService.addLoanRequest(loanRequest));
        } catch (Exception e) {
            log.info("There was an error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Couldn't submit please try again or contact your agency");
        }
    }

    @GetMapping(value = "/request/get-all")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity consultLoanRequest() {
        try {
            return ResponseEntity.ok(loanRequestService.getAllLoanRequest());
        } catch (Exception e) {
            log.info("There was an error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Couldn't get loan request please try again or contact your agency");
        }
    }

    @PutMapping(value = "/request/cancel/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity cancelLoanRequest(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(loanRequestService.cancelLoanRequest(id));
        } catch (Exception e) {
            log.info("There was an error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Couldn't cancel loan request please try again or contact your agency");
        }
    }
}