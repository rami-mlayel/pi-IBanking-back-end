package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@ToString
@Table(name = "MEETING")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "DATE")
    private Date date;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="HH:MM")
    @Column(name = "TIME", nullable = false)
    private Date time;

    @Column(name = "IS_CONFIRMED")
    private boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "idAgent", referencedColumnName = "id", insertable=false, updatable=false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "id", insertable=false, updatable=false)
    private Customer customer;
}

