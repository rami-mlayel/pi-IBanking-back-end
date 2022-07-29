package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository  extends CrudRepository<Meeting,Long> {
}
