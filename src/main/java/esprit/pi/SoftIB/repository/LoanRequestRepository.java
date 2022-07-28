package esprit.pi.SoftIB.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import esprit.pi.SoftIB.entities.LoanRequest;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

}
