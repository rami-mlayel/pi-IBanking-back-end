package esprit.pi.SoftIB.controllers;


import esprit.pi.SoftIB.dto.CustomerAccount;
import esprit.pi.SoftIB.entities.*;
import esprit.pi.SoftIB.services.IAccountResquestService;
import esprit.pi.SoftIB.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    IAccountResquestService iAccountRequestService;
    @Autowired
    IAccountService iAccountService;

    @PostMapping("/fillOutAccountReuqest")
    @ResponseStatus(HttpStatus.OK)
    public void fillOutAccountReuqest(@RequestBody AccountRequest accountRequest){
        iAccountRequestService.fillOutAccountReuqest(accountRequest);
    }


    @GetMapping(value = "/getAccount/{id}")
    public ResponseEntity getAccount(@PathVariable Long id) {
        Optional<Account> account = iAccountService.getAccountById(id);
        if(!account.isPresent())
            return ResponseEntity.badRequest().body("Account doesn't exist");
        return ResponseEntity.status(HttpStatus.OK).body(account.get());
    }

    @GetMapping(value = "/listAccounts")
    public ResponseEntity listAccounts() {
        List<Account> accounts = new ArrayList<>();
        try {
            accounts = iAccountService.listAccounts();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @PostMapping(value = "/addAccount" , produces = "application/json; charset=utf-8")
    public ResponseEntity addAccount(@RequestBody Account account) {
        Account accountPostSave = null;
        try {
            accountPostSave = iAccountService.addAccount(account);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(accountPostSave);
    }

    @PutMapping(value = "/updateAccount")
    public ResponseEntity updateAccount(@RequestBody Account account) {
        Account accountPostSave = null;
        try {
            accountPostSave = iAccountService.updateAccount(account);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountPostSave);
    }

    @DeleteMapping(value = "/deleteAccount/{id}")
    public ResponseEntity deleteAccount(@PathVariable Long id) {
        try {
            iAccountService.deleteAccount(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Account deleted");
    }

    @GetMapping("/getLeastBusyAgentByDay/{date}")
    @ResponseBody
    public Agent getLeastBusyTimeSheetByDay(@PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy")Date date) {

        return null;
    }


    @GetMapping("/getLeastBusyAgentByDay/{startDate}/{endDate}")
    List<LocalDate> checkAvailibleDates(@PathVariable("startDate")@DateTimeFormat(pattern = "dd-MM-yyyy")LocalDate startDate, @PathVariable("endDate")@DateTimeFormat(pattern = "dd-MM-yyyy")LocalDate endDate){
        return iAccountRequestService.checkAvailibleDates(startDate,endDate);
    }

    //void fixUpAMeeting(Meeting meeting) throws Exception;

    @PostMapping("/approveAccountRequest")
    void approveAccountRequest(@RequestBody CustomerAccount customerAccount) {
        iAccountRequestService.approveAccountRequest(customerAccount);
    }

    //List<AccountRequest> getAlLAccountRequest();


}
