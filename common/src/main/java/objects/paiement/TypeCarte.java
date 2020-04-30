package objects.paiement;

public enum TypeCarte {
    CB("Carte Bancaire"),
    VISA("Visa"),
    MASTERCARD("MasterCard"),
    VISA_ELECTRON("Visa Electron"),
    MAESTRO("Maestro");

    private final String value;

    TypeCarte(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
