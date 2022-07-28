package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.pi.SoftIB.enumeration.TransactionType;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@ToString
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "AMOUNT", nullable = false, length = 40)
    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "DATE")
    private Date date;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="HH:MM")
    @Column(name = "TIME", nullable = false)
    private Date time;

    @ManyToOne(optional = false)
    @JoinColumn(name="ID_SENDER")
    private Account sender;

    @ManyToOne(optional = false)
    @JoinColumn(name="ID_RECEIVER")
    private Account receiver;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss")
    @CreationTimestamp
    @Column(name = "TRANSACTION_DATE_TIME", nullable = false)
    private Date transactionDateTime;

    @NotEmpty
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @NotEmpty
    @Column(name = "COMMISSION_RATE")
    private BigDecimal commissionRate;

}
