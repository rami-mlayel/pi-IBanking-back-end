package esprit.pi.SoftIB.entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


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

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable=false, updatable=false)
    private User userAgent;

    @ManyToOne
    @JoinColumn(name = "idAgency", referencedColumnName = "id", insertable=false, updatable=false)
    private Agency agencyAgent;
}
