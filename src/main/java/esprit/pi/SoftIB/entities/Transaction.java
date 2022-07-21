package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.pi.SoftIB.enumeration.TransactionType;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@ToString
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false, length = 40)
    private Float amount;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false, length = 40)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "DATE")
    private Date date;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern="HH:MM")
    @Column(name = "TIME", nullable = false)
    private Date time;

    @NotEmpty
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @NotEmpty
    @Column(name = "COMMISSION_RATE")
    private BigDecimal commissionRate;

}
