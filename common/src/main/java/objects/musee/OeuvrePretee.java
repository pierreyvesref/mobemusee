package objects.musee;

import java.util.Date;

public class OeuvrePretee extends Oeuvre {
    private String museePreteur;
    private Date datePret;
    private Date dateRendu;

    public OeuvrePretee(){}

    public OeuvrePretee(int id, String nomOeuvre, Date dateAchat, double prixAchat, String museePreteur, Date datePret, Date dateRendu) {
        super(id, nomOeuvre, dateAchat, prixAchat);
        this.museePreteur = museePreteur;
        this.datePret = datePret;
        this.dateRendu = dateRendu;
    }

    public String getMuseePreteur() {
        return museePreteur;
    }

    public void setMuseePreteur(String museePreteur) {
        this.museePreteur = museePreteur;
    }

    public Date getDatePret() {
        return datePret;
    }

    public void setDatePret(Date datePret) {
        this.datePret = datePret;
    }

    public Date getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(Date dateRendu) {
        this.dateRendu = dateRendu;
    }

    @Override
    public String toString() {
        return "OeuvrePretee{" +
                "museePreteur='" + museePreteur + '\'' +
                ", datePret=" + datePret +
                ", dateRendu=" + dateRendu +
                '}';
    }
}
