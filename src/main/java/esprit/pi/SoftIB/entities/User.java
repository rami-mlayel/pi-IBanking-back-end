package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@ToString
@Table(name = "USER")
public class User {

    @Id
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
    @Column(name = "PERSONAL_ADDRESS")
    private String personalAddress;

    @NotEmpty
    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy="userAgent")
    private List<Agent> agents;

    @JsonIgnore
    @OneToMany(mappedBy="userCustomer")
    private List<Customer> customers;
}
