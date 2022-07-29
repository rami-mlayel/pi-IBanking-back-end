package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.pi.SoftIB.enumeration.TransactionStatus;
import esprit.pi.SoftIB.enumeration.TransactionType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "AMOUNT", nullable = false, length = 40)
    private BigDecimal amount;

    private String senderAccountNumber;

    private String receiverAccountNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss")
    @CreationTimestamp
    @Column(name = "TRANSACTION_DATE_TIME", nullable = false)
    private Date transactionDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_STATUS")
    private TransactionStatus transactionStatus;

    @Column(name = "COMMISSION_RATE")
    private BigDecimal commissionRate;

}
