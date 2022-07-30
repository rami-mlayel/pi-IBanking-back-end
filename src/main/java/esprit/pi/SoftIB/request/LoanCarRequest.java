package esprit.pi.SoftIB.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoanCarRequest extends LoanTypeRequest {

    private String carPrice;

    private String carPower;

    private String month;
}
