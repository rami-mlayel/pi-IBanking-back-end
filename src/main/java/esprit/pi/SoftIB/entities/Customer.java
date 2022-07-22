package esprit.pi.SoftIB.entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@Getter
@ToString
@Table(name = "CUSTOMER")
public class Customer {

	
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @NotEmpty
    @Column(name = "CIN", unique = true, nullable = false, length = 8)
    private String cin;

    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @OneToOne(mappedBy="customer")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idAgency", referencedColumnName = "id", insertable=false, updatable=false)
    private Agency agencyCustomer;

    @OneToOne
    private AccountRequest accountRequest;
}
