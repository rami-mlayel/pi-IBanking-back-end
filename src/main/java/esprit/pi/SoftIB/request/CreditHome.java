package esprit.pi.SoftIB.request;

import esprit.pi.SoftIB.enumeration.CreditHomeType;
import lombok.Getter;

@Getter
public class CreditHome {

    private CreditHomeType type;

    private String sum;

    private String year;

}
