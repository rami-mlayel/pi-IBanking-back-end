package esprit.pi.SoftIB.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoanMortgageRequest extends LoanTypeRequest {

    private String year;
}
