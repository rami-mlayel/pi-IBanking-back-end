package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import esprit.pi.SoftIB.enumeration.Housing;
import esprit.pi.SoftIB.enumeration.LoanRequestStatus;
import esprit.pi.SoftIB.enumeration.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
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

    @Column(length = 32, name = "LOAN_REQUEST_STATUS", columnDefinition = "varchar(32) default 'WAITING'")
    @Enumerated(value = EnumType.STRING)
    private LoanRequestStatus loanRequestStatus = LoanRequestStatus.WAITING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgent", referencedColumnName = "id", updatable=false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "idAccount", referencedColumnName = "id", updatable=false)
    private Account account;

    @OneToOne
    private Loan loan;
}
