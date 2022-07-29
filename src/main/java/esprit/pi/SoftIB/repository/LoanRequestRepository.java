package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import esprit.pi.SoftIB.entities.LoanRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

    List<LoanRequest> findByAccount(Account account);
}
