package esprit.pi.SoftIB.controllers;

import esprit.pi.SoftIB.entities.Transaction;
import esprit.pi.SoftIB.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public Optional<Transaction> getTransaction(@PathVariable("transaction-id") Long transactionID) {
        return transactionService.getTransactionById(transactionID);
    }

    @GetMapping(value = "/GET/all")
    public Collection<Transaction> getAllTransactions(@RequestParam(name = "TYPE", required = false) String type, @RequestParam(name = "SENDER", required = false) String sender, @RequestParam(name = "RECEIVER", required = false) String receiver) {
        return transactionService.getAllTransaction(type, sender, receiver);
    }



}
