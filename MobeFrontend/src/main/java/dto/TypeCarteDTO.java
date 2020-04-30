package dto;

public enum TypeCarteDTO {
    CB("Carte Bancaire"),
    VISA("Visa"),
    MASTERCARD("MasterCard"),
    VISA_ELECTRON("Visa Electron"),
    MAESTRO("Maestro");

    private final String value;

    TypeCarteDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
