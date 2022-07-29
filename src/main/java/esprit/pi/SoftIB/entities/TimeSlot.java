package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TIMESLOT")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DURATION", nullable = false)
    private int duration;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="HH:MM")
    @Column(name = "START_TIME", nullable = false)
    private Date startTime;

    @ManyToOne
    @JoinColumn(name = "idTimesheet", referencedColumnName = "id", updatable=false)
    private Timesheet timesheet;
}
