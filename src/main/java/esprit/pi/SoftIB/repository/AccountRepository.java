package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
