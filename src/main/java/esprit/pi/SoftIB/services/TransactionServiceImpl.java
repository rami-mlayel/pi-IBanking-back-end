package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.entities.Account;
import esprit.pi.SoftIB.entities.Transaction;
import esprit.pi.SoftIB.enumeration.TransactionStatus;
import esprit.pi.SoftIB.enumeration.TransactionType;
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
    @Transactional
    public Transaction retrieveMoney(String accountNumber, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setCommissionRate(new BigDecimal(1));
        transaction.setAmount(amount);
        transaction.setReceiverAccountNumber(accountNumber);
        transaction.setSenderAccountNumber(accountNumber);

        Account account = new Account();
        //todo get account by accountNumber
        BigDecimal accountBalance = account.getBalance();
        BigDecimal retrievedMoney = transaction.getAmount().add(transaction.getCommissionRate());
        if (accountBalance.compareTo(retrievedMoney) != -1) {
            account.setBalance(accountBalance.subtract(retrievedMoney));
            transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        }
        transactionRepository.save(transaction);
        //save account
        return transaction;
    }

    @Override
    @Transactional
    public Transaction depositMoney(String accountNumber, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setCommissionRate(new BigDecimal(0));
        transaction.setAmount(amount);
        transaction.setReceiverAccountNumber(accountNumber);
        transaction.setSenderAccountNumber(accountNumber);
        //todo get account by accountNumber
        Account account = new Account();
        account.setBalance(account.getBalance().add(amount));
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);
        //save account
        return transaction;
    }

    @Override
    @Transactional
    public Transaction depositCheck(String accountNumber, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEP_CHECK);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setCommissionRate(new BigDecimal(2));
        transaction.setAmount(amount);
        transaction.setReceiverAccountNumber(accountNumber);
        //todo get account by accountNumber
        Account account = new Account();
        account.setBalance(account.getBalance().add(amount));
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);
        //save account
        return transaction;
    }


    @Override
    @Transactional
    public Transaction sendMoney(String senderAccountNumber, String receiverAccountNumber, BigDecimal amount) {

        //todo get sender and receiver account by number
        Account sender = new Account();
        Account receiver = new Account();

        BigDecimal senderBalance = sender.getBalance();
        BigDecimal receiverBalance = receiver.getBalance();


        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        //todo create commision Rate
        transaction.setCommissionRate(BigDecimal.valueOf(1.5));
        transaction.setAmount(amount);
        transaction.setReceiverAccountNumber(receiverAccountNumber);
        transaction.setSenderAccountNumber(senderAccountNumber);

        BigDecimal sentMoney = transaction.getAmount().add(transaction.getCommissionRate());

        if (senderBalance.compareTo(sentMoney) != -1) {
            receiver.setBalance(receiver.getBalance().add(amount));
            sender.setBalance(sender.getBalance().subtract(sentMoney));
            transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        }

        transactionRepository.save(transaction);
        //save account

        return transaction;

    }

    @Override
    public List<Transaction> getAllTransaction(String type, String senderAccountNumber, String receiverAccountNumber) {
        List<Transaction> transactionList = new ArrayList<>();
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