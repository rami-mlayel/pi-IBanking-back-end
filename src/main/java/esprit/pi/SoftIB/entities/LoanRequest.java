package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import esprit.pi.SoftIB.enumeration.Housing;
import esprit.pi.SoftIB.enumeration.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor(onConstructor = @__({ @JsonCreator}))
@Table(name = "LOAN_REQUEST")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PURPOSE", nullable = false, length = 40)
    private LoanType purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "HOUSING", nullable = false, length = 40)
    private Housing housing;

    @NotEmpty
    @Column(name = "DETAIL", nullable = false, length = 250)
    private String detail;

    @NotNull
    @Column(name = "SUM")
    private BigDecimal sum;

    @NotNull
    @Column(name = "MONTH_DURATION")
    private int monthDuration;

    @Column(name = "IS_APPROVED", columnDefinition = "boolean default false")
    private boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "idAgent", referencedColumnName = "id", updatable=false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "idAccount", referencedColumnName = "id", updatable=false)
    private Account account;

    @OneToOne
    private Loan loan;
}
