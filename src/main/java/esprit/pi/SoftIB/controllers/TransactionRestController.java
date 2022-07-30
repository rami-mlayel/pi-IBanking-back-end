package esprit.pi.SoftIB.controllers;

import esprit.pi.SoftIB.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by omarzougui on 7/26/2022
 */

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    @Autowired
    ITransactionService transactionService;

    @GetMapping(value = "/GET/{transaction-id}")
    public ResponseEntity getTransaction(@RequestParam(name = "TRANSACTION-ID") String transactionID) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionById(Long.parseLong(transactionID)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/GET/all")
    public ResponseEntity getAllTransactions(@RequestParam(name = "TYPE", required = false) String type, @RequestParam(name = "STATUS", required = false) String status,@RequestParam(name = "SENDER", required = false) String sender, @RequestParam(name = "RECEIVER", required = false) String receiver) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllTransaction(type, status, sender, receiver));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/DepositMoney")
    public ResponseEntity depistMoney(@RequestParam(name = "ACCOUNT") String account, @RequestParam(name = "AMOUNT") String amount) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.depositMoney(account, new BigDecimal(amount)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/RetrieveMoney")
    public ResponseEntity retrieveMoney(@RequestParam(name = "ACCOUNT") String account, @RequestParam(name = "AMOUNT") String amount) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.retrieveMoney(account, new BigDecimal(amount)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/DepositCheck")
    public ResponseEntity depositCheck(@RequestParam(name = "ACCOUNT") String account, @RequestParam(name = "AMOUNT") String amount) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.depositCheck(account, new BigDecimal(amount)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/TransferMoney")
    public ResponseEntity TransferMoney(@RequestParam(name = "SENDER") String sender, @RequestParam(name = "RECEIVER") String receiver, @RequestParam(name = "AMOUNT") String amount) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.transferMoney(sender, receiver, new BigDecimal(amount)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
