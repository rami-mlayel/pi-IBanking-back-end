package esprit.pi.SoftIB.enumeration;

public enum CreditAutoType {
    EQUAL_FOUR("4"),
    BETWEEN_FIVE_AND_EIGHT("58"),
    SUPERIOR_THAN_EIGHT("8plus");

    public final String type;

    CreditAutoType(String s) {
        this.type = s;
    }
}
