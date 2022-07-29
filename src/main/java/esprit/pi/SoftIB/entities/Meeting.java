package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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
    @JoinColumn(name = "idAgent", referencedColumnName = "id", updatable=false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "id", updatable=false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idAccountRequest", referencedColumnName = "id", updatable=false)
    private AccountRequest accountRequest;
}

