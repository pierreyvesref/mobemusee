package objects.musee;

import java.util.Date;

public class Exposition {
    private int id;
    private String nom;
    private Date dateDebutExpo;
    private Date dateFinExpo;

    public Exposition(){}

    public Exposition(int id, String nom, Date dateDebutExpo, Date dateFinExpo) {
        this.id = id;
        this.nom = nom;
        this.dateDebutExpo = dateDebutExpo;
        this.dateFinExpo = dateFinExpo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebutExpo() {
        return dateDebutExpo;
    }

    public void setDateDebutExpo(Date dateDebutExpo) {
        this.dateDebutExpo = dateDebutExpo;
    }

    public Date getDateFinExpo() {
        return dateFinExpo;
    }

    public void setDateFinExpo(Date dateFinExpo) {
        this.dateFinExpo = dateFinExpo;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebutExpo=" + dateDebutExpo +
                ", dateFinExpo=" + dateFinExpo +
                '}';
    }
}
