package objects.musee;

import java.util.Date;

public class Oeuvre {
    private int id;
    private String nomOeuvre;
    private Date dateAchat;
    private double prixAchat;

    public Oeuvre(){}

    public Oeuvre(int id, String nomOeuvre, Date dateAchat, double prixAchat) {
        this.id = id;
        this.nomOeuvre = nomOeuvre;
        this.dateAchat = dateAchat;
        this.prixAchat = prixAchat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomOeuvre() {
        return nomOeuvre;
    }

    public void setNomOeuvre(String nomOeuvre) {
        this.nomOeuvre = nomOeuvre;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }
}
