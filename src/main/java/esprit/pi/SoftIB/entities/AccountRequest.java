package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.pi.SoftIB.enumeration.Job;
import esprit.pi.SoftIB.enumeration.State;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@ToString
@Table(name = "ACCOUNT_REQUEST")
public class AccountRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

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
    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "JOB", nullable = false, length = 8)
    private Job job;

    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToOne(mappedBy="accountRequest")
    private Customer customer;

    @Column(name = "IS_APPROVED", nullable = false)
    private boolean isApproved;

    @Column(name = "REFERRENCE", nullable = false)
    private boolean reference;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "PREFERED_DATE")
    private Date preferedDate;



}

