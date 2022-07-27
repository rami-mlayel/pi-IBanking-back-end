package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.entities.Transaction;
import esprit.pi.SoftIB.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by omarzougui on 7/25/2022
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Optional<Transaction> getTransactionById(long id) {
        return transactionRepository.findById(id);
    }


    @Override
    public boolean retrieveMoney(String AccountNumber, BigDecimal amount) {
        Transaction transaction = new Transaction();
        //Todo create a transaction
        BigDecimal accountBalance = account.getBalance();
        BigDecimal retrievedMoney = transaction.getAmount().add(transaction.getCommissionRate());
        if (accountBalance.compareTo(retrievedMoney) != -1) {
            account.setBalance(accountBalance.subtract(retrievedMoney));
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean depositMoney(String AccountNumber, BigDecimal amount) {
        Transaction transaction = new Transaction();
        //Todo create a transaction
        BigDecimal accountBalance = account.getBalance();
        BigDecimal retrievedMoney = transaction.getAmount().add(transaction.getCommissionRate());
        account.setBalance(accountBalance.subtract(retrievedMoney));
        return true;
    }

    @Override
    public boolean depositCheck(String AccountNumber, BigDecimal amount) {
        return false;
    }


    @Override
    @Transactional
    public boolean sendMoney(String senderAccountNumber, String receiverAccountNumber, BigDecimal amount) {

        BigDecimal senderBalance = sender.getBalance();
        BigDecimal receiverBalance = receiver.getBalance();

        receiver.getBalance().add(amount);
        sender.getBalance().subtract(amount);


        return false;

    }

    @Override
    public List<Transaction> getAllTransaction(String type, String senderAccountNumber, String receiverAccountNumber) {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionRepository.findAll().forEach(transactionList::add);

        if (type != null)
            transactionList.stream().filter(transaction -> transaction.getTransactionType().toString().equalsIgnoreCase(type));

        if (senderAccountNumber != null)
            transactionList.stream().filter(transaction -> transaction.getSenderAccountNumber().equalsIgnoreCase(senderAccountNumber));

        if (receiverAccountNumber != null)
            transactionList.stream().filter(transaction -> transaction.getReceiverAccountNumber().equalsIgnoreCase(receiverAccountNumber));

        return transactionList;
    }

}
