package objects.paiement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MoyenPaiement {
    private int idMoyenpaiement;
    private TypeCarte typeCarte;
    private long numCompte;
    private Date dateExpiration;
    private int cryptogramme;
    private String titulaire;

    public MoyenPaiement() {
    }

    public MoyenPaiement(int id, TypeCarte typeCarte, long numCompte, Date dateExpiration, int cryptogramme, String titulaire) {
        this.idMoyenpaiement = id;
        this.typeCarte = typeCarte;
        this.numCompte = numCompte;
        this.dateExpiration = dateExpiration;
        this.cryptogramme = cryptogramme;
        this.titulaire = titulaire;
    }

    public int getIdMoyenpaiement() {
        return idMoyenpaiement;
    }

    public TypeCarte getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(TypeCarte typeCarte) {
        this.typeCarte = typeCarte;
    }

    public long getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(long numCompte) {
        this.numCompte = numCompte;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getCryptogramme() {
        return cryptogramme;
    }

    public void setCryptogramme(int cryptogramme) {
        this.cryptogramme = cryptogramme;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoyenPaiement)) return false;
        MoyenPaiement that = (MoyenPaiement) o;
        return numCompte == that.numCompte &&
                cryptogramme == that.cryptogramme &&
                typeCarte == that.typeCarte &&
                dateExpiration.equals(that.dateExpiration) &&
                titulaire.equals(that.titulaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeCarte, numCompte, dateExpiration, cryptogramme, titulaire);
    }

    @Override
    public String toString() {
        return "MoyenPaiement{" +
                "idMoyenpaiement=" + idMoyenpaiement +
                ", typeCarte=" + typeCarte +
                ", numCompte=" + numCompte +
                ", dateExpiration=" + dateExpiration +
                ", cryptogramme=" + cryptogramme +
                ", titulaire='" + titulaire + '\'' +
                '}';
    }
}
