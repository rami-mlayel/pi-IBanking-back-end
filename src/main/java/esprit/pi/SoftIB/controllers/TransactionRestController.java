package esprit.pi.SoftIB.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @RequestMapping(value = "/GET/all", method = RequestMethod.POST, produces = "application/json")
    public Collection<Transaction> getAllTransactions(@RequestBody ObjectNode objectNode) {
        return transactionService.getAllTransaction("TYPE",null,null);
    }






}
