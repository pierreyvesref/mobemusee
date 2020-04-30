package dto;

import java.util.Date;

public class BilletMuseeDTO {
    private String keyQrCode;
    private Date dateVisite;
    private boolean isValide;

    private TypeTarifDTO typeTarif;

    public BilletMuseeDTO() {
    }

    public BilletMuseeDTO(Date dateVisite, TypeTarifDTO typeTarif) {
        this.dateVisite = dateVisite;
        this.typeTarif = typeTarif;
    }

    public BilletMuseeDTO(String keyQrCode, Date dateVisite, boolean isValide, TypeTarifDTO typeTarif) {
        this.keyQrCode = keyQrCode;
        this.dateVisite = dateVisite;
        this.isValide = isValide;
        this.typeTarif = typeTarif;
    }

    public String getKeyQrCode() {
        return keyQrCode;
    }

    public void setKeyQrCode(String keyQrCode) {
        this.keyQrCode = keyQrCode;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public boolean isValide() {
        return isValide;
    }

    public void setValide(boolean valide) {
        isValide = valide;
    }

    public TypeTarifDTO getTypeTarif() {
        return typeTarif;
    }

    public void setTypeTarif(TypeTarifDTO typeTarif) {
        this.typeTarif = typeTarif;
    }

    @Override
    public String toString() {
        return "BilletMuseeDTO{" +
                "keyQrCode='" + keyQrCode + '\'' +
                ", dateVisite=" + dateVisite +
                ", isValide=" + isValide +
                ", typeTarifDTO=" + typeTarif +
                '}';
    }
}
