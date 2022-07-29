package esprit.pi.SoftIB.repository;

import esprit.pi.SoftIB.entities.Timesheet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TimeSheetRepository extends CrudRepository<Timesheet, Long> {

    @Query(value = "from Timesheet where date = :preferedDate")
    public List<Timesheet> getTimesheetByDate(@Param("preferedDate") Date preferedDate);
}
