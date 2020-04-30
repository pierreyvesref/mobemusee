package fr.miage.orleans.isi.projet.paiementws.entities;


import fr.miage.orleans.isi.projet.paiementws.DTO.BilletMuseeDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BilletMuseeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String keyQrCode;
    private Date dateVisite;
    private boolean isValide;
    @OneToOne
    private TypeTarifEntity typeTarif;

    public BilletMuseeEntity() {
    }

    public static BilletMuseeEntity createFromDTO(BilletMuseeDTO bTO) {
        BilletMuseeEntity billetMuseeEntity = new BilletMuseeEntity();
        billetMuseeEntity.setKeyQrCode(bTO.getKeyQrCode());
        billetMuseeEntity.setDateVisite(bTO.getDateVisite());
        billetMuseeEntity.setValide(bTO.isValide());
        billetMuseeEntity.setTypeTarif(TypeTarifEntity.createFromDTO(bTO.getTypeTarif()));
        return billetMuseeEntity;
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

    public TypeTarifEntity getTypeTarif() {
        return typeTarif;
    }

    public void setTypeTarif(TypeTarifEntity typeTarif) {
        this.typeTarif = typeTarif;
    }

    @Override
    public String toString() {
        return "BilletMuseeEntity{" +
                "id=" + id +
                ", keyQrCode='" + keyQrCode + '\'' +
                ", dateVisite=" + dateVisite +
                ", isValide=" + isValide +
                ", typeTarif=" + typeTarif +
                '}';
    }
}
