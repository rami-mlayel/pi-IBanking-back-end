package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.pi.SoftIB.enumeration.TransactionStatus;
import esprit.pi.SoftIB.enumeration.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "AMOUNT", nullable = false, length = 40)
    private BigDecimal amount;

    private String senderAccountNumber;

    private String receiverAccountNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss")
    @CreationTimestamp
    @Column(name = "TRANSACTION_DATE_TIME", nullable = false)
    private Date transactionDateTime;

    @NotEmpty
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @NotEmpty
    @Column(name = "TRANSACTION_STATUS")
    private TransactionStatus transactionStatus;

    @NotEmpty
    @Column(name = "COMMISSION_RATE")
    private BigDecimal commissionRate;

}
