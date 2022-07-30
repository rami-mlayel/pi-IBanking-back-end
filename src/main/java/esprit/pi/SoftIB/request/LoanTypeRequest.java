package esprit.pi.SoftIB.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import esprit.pi.SoftIB.enumeration.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type" )
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoanCarRequest.class, name = "CAR"),
        @JsonSubTypes.Type(value = LoanMortgageRequest.class, name = "MORTGAGE"),
})
public class LoanTypeRequest {

    private LoanType type;

    private String sum;
}
