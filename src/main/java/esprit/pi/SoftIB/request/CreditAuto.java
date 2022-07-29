package esprit.pi.SoftIB.request;

import esprit.pi.SoftIB.enumeration.CreditAutoType;
import lombok.Getter;

@Getter
public class CreditAuto {

    private String carPrice;

    private CreditAutoType carPower;

    private String sum;

    private String months;
}
