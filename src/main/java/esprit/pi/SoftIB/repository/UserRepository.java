package esprit.pi.SoftIB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import esprit.pi.SoftIB.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	Boolean existsByUsername(String username);
	
    @Query(value = "SELECT * FROM USERS WHERE EMAIL = ?1", nativeQuery = true)
    User findByEmail(String email);
}
