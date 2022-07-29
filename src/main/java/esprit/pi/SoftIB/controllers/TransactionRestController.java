package esprit.pi.SoftIB.controllers;

import esprit.pi.SoftIB.entities.Transaction;
import esprit.pi.SoftIB.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by omarzougui on 7/26/2022
 */

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    @Autowired
    ITransactionService transactionService;

    @GetMapping(value = "/GET/{transaction-id}", produces = "application/json")
    public ResponseEntity getTransaction(@PathVariable("transaction-id") Long transactionID) {

        Optional<Transaction> transaction;
        try {
            transaction = transactionService.getTransactionById(transactionID);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @GetMapping(value = "/GET/all")
    public ResponseEntity getAllTransactions(@RequestParam(name = "TYPE", required = false) String type, @RequestParam(name = "SENDER", required = false) String sender, @RequestParam(name = "RECEIVER", required = false) String receiver) {

        List<Transaction> allTransaction;
        try {
            allTransaction = transactionService.getAllTransaction(type, sender, receiver);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(allTransaction);
    }

    @PostMapping(value = "/DepositMoney")
    public ResponseEntity depistMoney(@RequestParam(name = "ACCOUNT", required = false) String account, @RequestParam(name = "Amount") BigDecimal amount) {

        Transaction transaction;
        try {
            transaction = transactionService.depositMoney(account, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @PostMapping(value = "/RetrieveMoney")
    public ResponseEntity retrieveMoney(@RequestParam(name = "ACCOUNT", required = false) String account, @RequestParam(name = "Amount") BigDecimal amount) {

        Transaction transaction;
        try {
            transaction = transactionService.retrieveMoney(account, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @PostMapping(value = "/DepositCheck")
    public ResponseEntity depositCheck(@RequestParam(name = "ACCOUNT", required = false) String account, @RequestParam(name = "Amount") BigDecimal amount) {

        Transaction transaction;
        try {
            transaction = transactionService.depositCheck(account, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @PostMapping(value = "/SentMoney")
    public ResponseEntity SentMoney(@RequestParam(name = "SENDER", required = false) String sender, @RequestParam(name = "RECEIVER", required = false) String receiver, @RequestParam(name = "Amount") BigDecimal amount) {

        Transaction transaction;
        try {
            transaction = transactionService.sendMoney(sender, receiver, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

}
