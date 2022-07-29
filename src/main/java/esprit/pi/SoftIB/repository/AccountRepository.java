package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query(value = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER = ?1", nativeQuery = true)
    Account findByAccountNumber(String accountNumber);
}
