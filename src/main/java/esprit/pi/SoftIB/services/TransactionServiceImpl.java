package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.entities.Account;
import esprit.pi.SoftIB.entities.Transaction;
import esprit.pi.SoftIB.enumeration.TransactionStatus;
import esprit.pi.SoftIB.enumeration.TransactionType;
import esprit.pi.SoftIB.repository.AccountRepository;
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

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Optional<Transaction> getTransactionById(long id) {
        return transactionRepository.findById(id);
    }

    @Override
    @Transactional
    public Transaction retrieveMoney(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.WITHDRAWAL);
            transaction.setTransactionStatus(TransactionStatus.PENDING);
            transaction.setCommissionRate(new BigDecimal(1));
            transaction.setAmount(amount);
            transaction.setReceiverAccountNumber(accountNumber);
            transaction.setSenderAccountNumber(accountNumber);

            BigDecimal accountBalance = account.getBalance();
            BigDecimal retrievedMoney = transaction.getAmount().add(transaction.getCommissionRate());
            if (accountBalance.compareTo(retrievedMoney) != -1) {
                account.setBalance(accountBalance.subtract(retrievedMoney));
                transaction.setTransactionStatus(TransactionStatus.COMPLETED);
            }
            transactionRepository.save(transaction);
            accountRepository.save(account);
            return transaction;
        }
        return null;
    }

    @Override
    @Transactional
    public Transaction depositMoney(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.DEPOSIT);
            transaction.setTransactionStatus(TransactionStatus.PENDING);
            transaction.setCommissionRate(new BigDecimal(0));
            transaction.setAmount(amount);
            transaction.setReceiverAccountNumber(accountNumber);
            transaction.setSenderAccountNumber(accountNumber);
            account.setBalance(account.getBalance().add(amount));
            transaction.setTransactionStatus(TransactionStatus.COMPLETED);
            transactionRepository.save(transaction);
            accountRepository.save(account);
            return transaction;
        }
        return null;
    }

    @Override
    @Transactional
    public Transaction depositCheck(String accountNumber, BigDecimal amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.DEP_CHECK);
            transaction.setTransactionStatus(TransactionStatus.PENDING);
            transaction.setCommissionRate(new BigDecimal(2));
            transaction.setAmount(amount);
            transaction.setReceiverAccountNumber(accountNumber);
            account.setBalance(account.getBalance().add(amount));
            transaction.setTransactionStatus(TransactionStatus.COMPLETED);
            transactionRepository.save(transaction);
            accountRepository.save(account);
            return transaction;
        }
        return null;
    }


    @Override
    @Transactional
    public Transaction transferMoney(String senderAccountNumber, String receiverAccountNumber, BigDecimal amount) {

        Account sender = accountRepository.findByAccountNumber(senderAccountNumber);
        Account receiver = accountRepository.findByAccountNumber(receiverAccountNumber);

        if (sender != null) {
            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setTransactionStatus(TransactionStatus.PENDING);
            transaction.setCommissionRate(BigDecimal.valueOf(1.5));
            transaction.setAmount(amount);
            transaction.setReceiverAccountNumber(receiverAccountNumber);
            transaction.setSenderAccountNumber(senderAccountNumber);
            BigDecimal senderBalance = sender.getBalance();

            BigDecimal sentMoney = transaction.getAmount().add(transaction.getCommissionRate());

            if (senderBalance.compareTo(sentMoney) != -1 && receiver != null) {
                receiver.setBalance(receiver.getBalance().add(amount));
                sender.setBalance(sender.getBalance().subtract(sentMoney));
                transaction.setTransactionStatus(TransactionStatus.COMPLETED);
                accountRepository.save(receiver);
            } else {
                sender.setBalance(sender.getBalance().subtract(sentMoney));
            }
            transactionRepository.save(transaction);
            accountRepository.save(sender);
            return transaction;
        }
        return null;
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
