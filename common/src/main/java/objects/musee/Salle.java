package objects.musee;

import java.util.List;

public enum Salle {

    RESERVE("Reserve"),
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

    Salle(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void addOeuvre(Oeuvre oeuvre){
        this.oeuvres.add(oeuvre);
    }

    public void removeOeuvre(Oeuvre oeuvre){
        this.oeuvres.remove(oeuvre);
    }

    @Override
    public String toString() {
        return "Salle{" +
                "value='" + value + '\'' +
                ", oeuvres=" + oeuvres +
                '}';
    }
}
