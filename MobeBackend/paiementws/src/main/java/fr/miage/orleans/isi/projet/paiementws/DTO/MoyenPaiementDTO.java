package fr.miage.orleans.isi.projet.paiementws.DTO;

import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import objects.paiement.MoyenPaiement;
import objects.paiement.Transaction;
import objects.paiement.TypeCarte;
import objects.users.User;

import java.util.Date;
import java.util.List;

public class MoyenPaiementDTO {
    private int idMoyenpaiement;
    private TypeCarteDTO typeCarte;
    private long numCompte;
    private Date dateExpiration;
    private int cryptogramme;
    private String titulaire;

    public MoyenPaiementDTO() {
    }

    public static MoyenPaiementDTO createFromBasic(MoyenPaiement mp) {
        MoyenPaiementDTO mpDTO = new MoyenPaiementDTO();
        mpDTO.setIdMoyenpaiement(mp.getIdMoyenpaiement());
        mpDTO.setTypeCarte(TypeCarteDTO.createDTOFromBasic(mp.getTypeCarte()));
        mpDTO.setNumCompte(mp.getNumCompte());
        mpDTO.setDateExpiration(mp.getDateExpiration());
        mpDTO.setCryptogramme(mp.getCryptogramme());
        mpDTO.setTitulaire(mp.getTitulaire());
        return mpDTO;
    }

    public static MoyenPaiementDTO createFromEntity(MoyenPaiementEntity mp){
        MoyenPaiementDTO mpDTO = new MoyenPaiementDTO();
        mpDTO.setIdMoyenpaiement(mp.getIdMoyenpaiement());
        mpDTO.setTypeCarte(TypeCarteDTO.createDTOFromEntity(mp.getTypeCarte()));
        mpDTO.setNumCompte(mp.getNumCompte());
        mpDTO.setDateExpiration(mp.getDateExpiration());
        mpDTO.setCryptogramme(mp.getCryptogramme());
        mpDTO.setTitulaire(mp.getTitulaire());
        return mpDTO;
    }

    public int getIdMoyenpaiement() {
        return idMoyenpaiement;
    }

    public void setIdMoyenpaiement(int idMoyenpaiement) {
        this.idMoyenpaiement = idMoyenpaiement;
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
