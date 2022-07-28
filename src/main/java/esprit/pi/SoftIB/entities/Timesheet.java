package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@ToString
@Table(name = "TIMESHEET")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "TO_DATE")
    private Date toDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="HH:MM")
    @Column(name = "TO_TIME", nullable = false)
    private Date toTtime;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="HH:MM")
    @Column(name = "FROM_TIME", nullable = false)
    private Date fromTime;

    @ManyToOne
    @JoinColumn(name = "idAgent", referencedColumnName = "id", insertable=false, updatable=false)
    private Agent agent;
}