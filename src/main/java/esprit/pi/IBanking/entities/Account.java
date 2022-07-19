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
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 40)
    private String accountNumber;

    @NotEmpty
    @Column(name = "RIB", nullable = false, length = 40)
    private String RIB;

    @NotEmpty
    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy="userAgent")
    private List<Agent> agents;

    @Enumerated(EnumType.STRING)
    private AccountType type;
}
