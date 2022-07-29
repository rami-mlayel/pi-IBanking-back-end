package esprit.pi.SoftIB.services;

import esprit.pi.SoftIB.entities.Account;
import esprit.pi.SoftIB.entities.AccountRequest;
import esprit.pi.SoftIB.entities.Meeting;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface IAccountService {

        Account addAccount (Account account);

        List<Account> listAccounts();

        Account updateAccount(Account account);

        void deleteAccount(Long id);

        Optional<Account> getAccountById(Long id);

}
