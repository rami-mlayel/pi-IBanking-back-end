package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Getter
@ToString
@Table(name = "AGENT")
public class Agent {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "AGENT_ID", length = 30, unique = true)
    private String agentID;

    @NotNull
    @Column(name = "IS_ADMIN")
    private boolean isAdmin;

    @OneToOne(mappedBy="agent")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idAgency", referencedColumnName = "id", insertable=false, updatable=false)
    private Agency agencyAgent;
}
