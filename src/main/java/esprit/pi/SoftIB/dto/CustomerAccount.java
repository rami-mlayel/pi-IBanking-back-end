package esprit.pi.SoftIB.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.pi.SoftIB.entities.LoanRequest;
import esprit.pi.SoftIB.enumeration.AccountType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CustomerAccount implements Serializable {
    private String accountNumber;
    private BigDecimal balance;
    private String rib;
    private AccountType type;
    private String personalAddress;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthDate;
    private String phoneNumber;
    private String cin;
    private String email;
    private BigDecimal salary;
    private Long idAccountRequest;
    private Long idAgent;


}
