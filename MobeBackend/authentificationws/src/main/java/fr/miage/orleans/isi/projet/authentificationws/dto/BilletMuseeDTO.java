package fr.miage.orleans.isi.projet.authentificationws.dto;

import fr.miage.orleans.isi.projet.authentificationws.entities.BilletMuseeEntity;
import objects.paiement.TypeTarif;

import java.util.Date;

public class BilletMuseeDTO {
    private int id;
    private String keyQrCode;
    private Date dateVisite;
    private boolean isValide;
    private TypeTarifDTO typeTarif;

    public BilletMuseeDTO(int id, String keyQrCode, Date dateVisite, boolean valide, TypeTarifDTO fromEntity) {
        this.id=id;
        this.keyQrCode=keyQrCode;
        this.setValide(valide);
        this.typeTarif=fromEntity;
    }

    public static BilletMuseeDTO createFromEntity(BilletMuseeEntity billetMuseeEntity) {
        return new BilletMuseeDTO(billetMuseeEntity.getId(), billetMuseeEntity.getKeyQrCode(), billetMuseeEntity.getDateVisite(), billetMuseeEntity.isValide(), TypeTarifDTO.createFromEntity(billetMuseeEntity.getTypeTarif()));
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

    public TypeTarifDTO getTypeTarif() {
        return typeTarif;
    }

    public void setTypeTarif(TypeTarifDTO typeTarif) {
        this.typeTarif = typeTarif;
    }
}
