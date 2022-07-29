package esprit.pi.SoftIB.entities;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import esprit.pi.SoftIB.enumeration.Job;
import esprit.pi.SoftIB.enumeration.Sex;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @NotEmpty
    @Column(name = "CIN", unique = true, nullable = false, length = 8)
    private String cin;

    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEX", nullable = false, length = 40)
    private Sex sex;

    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false, length = 40)
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false, length = 40)
    private String lastName;
    
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "JOB_STATUS", nullable = false, length = 40)
    private Job jobStatus;

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

    @OneToOne(mappedBy="customer")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idAgency", referencedColumnName = "id", updatable=false)
    private Agency agencyCustomer;

    @OneToOne
    private AccountRequest accountRequest;

    @OneToMany(mappedBy = "customer")
	private Set<QuestionAndAnswer> questionAndAnswer = new HashSet<QuestionAndAnswer>();
}
