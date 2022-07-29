package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.AccountRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRequestRepository  extends CrudRepository<AccountRequest, Long> {
}
