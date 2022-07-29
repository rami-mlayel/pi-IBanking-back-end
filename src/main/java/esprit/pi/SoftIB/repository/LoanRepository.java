package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {
}
