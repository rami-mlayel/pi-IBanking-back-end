package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@ToString
@Table(name = "LOAN")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "ACTUAL_SUM", nullable = false)
    private BigDecimal actualSum;

    @NotNull
    @Column(name = "RETURNED_SUM", nullable = false)
    private BigDecimal returnedSum;

    @NotNull
    @Column(name = "MONTHLY_PAYMENT", nullable = false)
    private BigDecimal monthlyPayment;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "END_DATE")
    private Date endDate;

    @NotNull
    @Column(name = "INTEREST_RATE", nullable = false)
    private BigDecimal interestRate;
}
