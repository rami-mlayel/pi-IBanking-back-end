package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


@Data
@Entity
@Table(name = "AGENT")
public class Agent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "AGENT_ID", length = 30, unique = true)
    private String agentID;

    @NotNull
    @Column(name = "IS_ADMIN")
    private boolean isAdmin;
    
    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false, length = 40)
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false, length = 40)
    private String lastName;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @NotEmpty
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @NotEmpty
    @Column(name = "PERSONAL_ADDRESS")
    private String personalAddress;

    @OneToOne(mappedBy="agent")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idAgency", referencedColumnName = "id", updatable=false)
    private Agency agencyAgent;

    @JsonIgnore
    @OneToMany(mappedBy = "agent")
    private List<LoanRequest> loanRequestList;

    @OneToMany(mappedBy = "agent")
	private Set<QuestionAndAnswer> questionAndAnswer = new HashSet<QuestionAndAnswer>();


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "agent")
    private List<Timesheet> timesheets;
}
