package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "TIMESHEET")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "DATE")
    private Date date;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "timesheet")
    private List<TimeSlot> timeSlots ;


    @ManyToOne
    @JoinColumn(name = "idAgent", referencedColumnName = "id", updatable=false)
    private Agent agent;
}