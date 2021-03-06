package dto;

public enum TypeTarifDTO {

    NORMAL("Tarif normal", 10),
    JUNIOR("Tarif junior", 5),
    SENOR("Tarif senor", 8);

    private final String value;
    private final double montantTarif;

    TypeTarifDTO(final String value, double montantTarif) {
        this.value = value;
        this.montantTarif = montantTarif;
    }

    public String getValue() {
        return value;
    }

    public double getMontantTarif() {
        return montantTarif;
    }

    @Override
    public String toString() {
        return "TypeTarif{" +
                "value='" + value + '\'' +
                ", montantTarif=" + montantTarif +
                '}';
    }

}
