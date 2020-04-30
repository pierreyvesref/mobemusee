package dto;

import objects.musee.Oeuvre;

import java.util.ArrayList;
import java.util.List;

public class SalleDTO {

    /*
    GRAND_HALL("Grand Hall"),
    ENTREE ("Entrée"),
    PETITE_SALLE1 ("Petite Salle 1"),
    PETITE_SALLE2 ("Petite Salle 2"),
    PETITE_SALLE3 ("Petite Salle 3"),
    SALLE_1 ("Salle 1"),
    SALLE_2 ("Salle 2"),
    SALLE_3 ("Salle 3");

    private final String value; //Libellé (RESERVE, SALLE1, etc...)

    private List<Oeuvre> oeuvres;

    SalleDTO(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    */

    private int id;
    private String value; // (RESERVE, SALLE1, etc...)
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
