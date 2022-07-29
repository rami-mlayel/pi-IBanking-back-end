package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ITransactionService {


    Optional<Transaction> getTransactionById(long id);

    Transaction retrieveMoney(String accountNumber, BigDecimal amount);

    Transaction depositMoney(String accountNumber, BigDecimal amount);

    Transaction depositCheck(String accountNumber, BigDecimal amount);

    Transaction transferMoney(String senderAccountNumber, String receiverAccountNumber, BigDecimal amount);

    List<Transaction> getAllTransaction(String type, String senderAccountNumber, String receiverAccountNumber);

}
