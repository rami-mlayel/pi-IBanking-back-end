package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER = ?1", nativeQuery = true)
    Account findByAccountNumber(String accountNumber);
}
