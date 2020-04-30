package objects.paiement;

import java.util.Date;

public class BilletMusee {

    private int id;
    private String keyQrCode;
    private Date dateVisite;
    private boolean isValide;

    private TypeTarif typeTarif;

    public BilletMusee() {
    }

    public BilletMusee(int id, String keyQrCode, Date dateVisite, TypeTarif type) {
        this.id = id;
        this.keyQrCode = keyQrCode;
        this.dateVisite = dateVisite;
        this.typeTarif = type;
        this.isValide = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TypeTarif getTypeTarif() {
        return typeTarif;
    }

    public void setTypeTarif(TypeTarif typeTarif) {
        this.typeTarif = typeTarif;
    }

    @Override
    public String toString() {
        return "BilletMusee{" +
                "id=" + id +
                ", keyQrCode='" + keyQrCode + '\'' +
                ", dateVisite=" + dateVisite +
                ", isValide=" + isValide +
                ", typeTarif=" + typeTarif +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BilletMusee)) return false;
        BilletMusee that = (BilletMusee) o;
        return keyQrCode == that.keyQrCode;
    }
}
