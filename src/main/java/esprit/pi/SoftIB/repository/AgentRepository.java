package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Agent;
import esprit.pi.SoftIB.entities.Timesheet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {


    @Query(value = "SELECT A.* FROM agent A , timesheet T WHERE A.id = T.id_agent and T.date=:preferedDate",nativeQuery = true)
    Agent findAgentWithTimeSheetDate(@Param("preferedDate") Date preferedDate);

}
