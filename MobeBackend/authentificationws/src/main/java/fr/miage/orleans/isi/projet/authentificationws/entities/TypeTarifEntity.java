package fr.miage.orleans.isi.projet.authentificationws.entities;

import fr.miage.orleans.isi.projet.authentificationws.dto.TypeTarifDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeTarifEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String value;
    private double montantTarif;

    public TypeTarifEntity() {
    }

    public static TypeTarifEntity createFromDTO(TypeTarifDTO typeTarif) {
        TypeTarifEntity typeTarifEntity = new TypeTarifEntity();
        typeTarifEntity.setValue(typeTarif.getValue());
        typeTarifEntity.setMontantTarif(typeTarif.getMontantTarif());
        return typeTarifEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getMontantTarif() {
        return montantTarif;
    }

    public void setMontantTarif(double montantTarif) {
        this.montantTarif = montantTarif;
    }
}
