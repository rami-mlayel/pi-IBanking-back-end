package esprit.pi.IBanking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@ToString
@Table(name = "USER")
public class Agency {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "CODE_AGENCY", length = 40, unique = true)
    private String codeAgency;

    @NotEmpty
    @Column(name = "NAME", length = 40)
    private String name;

    @NotEmpty
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @NotEmpty
    @Column(name = "ADDRESS")
    private String address;

    @NotEmpty
    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "agencyAgent")
    private List<Agent> agents;

    @JsonIgnore
    @OneToMany(mappedBy = "agencyCustomer")
    private List<Customer> customers;
}
