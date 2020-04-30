package fr.miage.orleans.isi.projet.authentificationws.dto;

import fr.miage.orleans.isi.projet.authentificationws.entities.TypeTarifEntity;

public class TypeTarifDTO {
    private int id;
    private String value;
    private double montantTarif;

    public TypeTarifDTO(int id, String value, double montantTarif) {
        this.id=id;
        this.value=value;
        this.montantTarif=montantTarif;
    }

    public static TypeTarifDTO createFromEntity(TypeTarifEntity typeTarif) {
        return new TypeTarifDTO(typeTarif.getId(), typeTarif.getValue(), typeTarif.getMontantTarif());
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
