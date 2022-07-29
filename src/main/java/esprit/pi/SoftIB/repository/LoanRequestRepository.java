package esprit.pi.SoftIB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import esprit.pi.SoftIB.entities.LoanRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
}
