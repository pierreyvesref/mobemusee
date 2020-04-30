package fr.miage.orleans.isi.projet.museews.dto;

import objects.musee.Oeuvre;

import java.util.ArrayList;
import java.util.List;

public class SalleDTO {

    private int id;
    private String value; //Libellé (RESERVE, SALLE1, etc...)
    private List<OeuvreDTO> oeuvres;

    public SalleDTO() {
        this.oeuvres = new ArrayList<>();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<OeuvreDTO> getOeuvres() {
        return oeuvres;
    }

    public void setOeuvres(List<OeuvreDTO> oeuvres) {
        this.oeuvres = oeuvres;
    }

    public String getValue() {
        return value;
    }

    public void addOeuvre(OeuvreDTO oeuvre){
        this.oeuvres.add(oeuvre);
    }

    public void removeOeuvre(OeuvreDTO oeuvre){
        this.oeuvres.remove(oeuvre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "value='" + value + '\'' +
                ", oeuvres=" + oeuvres +
                '}';
    }
}
