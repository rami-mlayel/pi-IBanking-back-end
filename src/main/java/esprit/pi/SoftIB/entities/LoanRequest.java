package esprit.pi.SoftIB.entities;

import esprit.pi.SoftIB.enumeration.LoanType;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.math.BigDecimal;

@Entity
@Getter
@ToString
@Table(name = "LOAN_REQUEST")
public class LoanRequest {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "PURPOSE", nullable = false, length = 40)
    private LoanType purpose;

    @NotEmpty
    @Column(name = "DETAIL", nullable = false, length = 40)
    private TextArea detail;

    @NotNull
    @Column(name = "SUM")
    private BigDecimal sum;

    @NotEmpty
    @Column(name = "PERIOD")
    private String period;

    @Column(name = "IS_APPROVED")
    private boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "idAgent", referencedColumnName = "id", insertable=false, updatable=false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "idAccount", referencedColumnName = "id", insertable=false, updatable=false)
    private Account account;
}
