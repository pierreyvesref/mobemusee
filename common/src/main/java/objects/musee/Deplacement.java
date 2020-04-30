package objects.musee;

import java.util.Date;

public class Deplacement {
    private int id;
    private Salle salle;
    private Oeuvre oeuvre;
    private Date dateDeplacement;

    public Deplacement(){}

    public Deplacement(int id, Salle salle, Oeuvre oeuvre, Date dateDeplacement) {
        this.id = id;
        this.salle = salle;
        this.oeuvre = oeuvre;
        this.dateDeplacement = dateDeplacement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public Date getDateDeplacement() {
        return dateDeplacement;
    }

    public void setDateDeplacement(Date dateDeplacement) {
        this.dateDeplacement = dateDeplacement;
    }

    @Override
    public String toString() {
        return "Deplacement{" +
                "id=" + id +
                ", salle=" + salle +
                ", oeuvre=" + oeuvre +
                ", dateDeplacement=" + dateDeplacement +
                '}';
    }
}
