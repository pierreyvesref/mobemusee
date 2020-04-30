package dto;

import java.util.Date;

public class MoyenPaiementDTO {
    private TypeCarteDTO typeCarte;
    private long numCompte;
    private Date dateExpiration;
    private int cryptogramme;
    private String titulaire;

    public MoyenPaiementDTO() {
    }

    public MoyenPaiementDTO(TypeCarteDTO typeCarte, long numCompte, Date dateExpiration, int cryptogramme, String titulaire) {
        this.typeCarte = typeCarte;
        this.numCompte = numCompte;
        this.dateExpiration = dateExpiration;
        this.cryptogramme = cryptogramme;
        this.titulaire = titulaire;
    }

    public TypeCarteDTO getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(TypeCarteDTO typeCarte) {
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
    public String toString() {
        return "MoyenPaiementDTO{" +
                "typeCarte=" + typeCarte +
                ", numCompte=" + numCompte +
                ", dateExpiration=" + dateExpiration +
                ", cryptogramme=" + cryptogramme +
                ", titulaire='" + titulaire + '\'' +
                '}';
    }
}
