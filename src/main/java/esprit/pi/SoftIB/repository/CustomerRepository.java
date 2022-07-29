package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
