package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.pi.SoftIB.enumeration.AccountType;
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
    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 40, unique = true)
    private String accountNumber;

    @NotEmpty
    @Column(name = "RIB", nullable = false, length = 40, unique = true)
    private String RIB;

    @NotEmpty
    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable=false, updatable=false)
    private User userAccount;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<LoanRequest> loanRequestList;
}
