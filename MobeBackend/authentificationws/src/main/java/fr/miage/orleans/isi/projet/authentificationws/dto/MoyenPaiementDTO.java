package fr.miage.orleans.isi.projet.authentificationws.dto;

import fr.miage.orleans.isi.projet.authentificationws.entities.MoyenPaiementEntity;
import objects.paiement.MoyenPaiement;
import objects.paiement.TypeCarte;

import java.util.Date;

public class MoyenPaiementDTO {
    private int idMoyenpaiement;
    private TypeCarteDTO typeCarte;
    private long numCompte;
    private Date dateExpiration;
    private int cryptogramme;
    private String titulaire;


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
}
