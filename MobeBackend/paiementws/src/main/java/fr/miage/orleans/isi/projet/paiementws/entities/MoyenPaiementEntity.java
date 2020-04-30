package fr.miage.orleans.isi.projet.paiementws.entities;

import fr.miage.orleans.isi.projet.paiementws.DTO.MoyenPaiementDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MoyenPaiementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMoyenpaiement;
    @OneToOne
    private TypeCarteEntity typeCarte;
    private long numCompte;
    private Date dateExpiration;
    private int cryptogramme;
    private String titulaire;


    public MoyenPaiementEntity() {
    }

    public static MoyenPaiementEntity createFromDTO(MoyenPaiementDTO moyenPaiementDTO){
        MoyenPaiementEntity moyenPaiementEntity = new MoyenPaiementEntity();
        moyenPaiementEntity.setTypeCarte(TypeCarteEntity.createTypeFromDTO(moyenPaiementDTO.getTypeCarte()));
        moyenPaiementEntity.setNumCompte(moyenPaiementDTO.getNumCompte());
        moyenPaiementEntity.setDateExpiration(moyenPaiementDTO.getDateExpiration());
        moyenPaiementEntity.setCryptogramme(moyenPaiementDTO.getCryptogramme());
        moyenPaiementEntity.setTitulaire(moyenPaiementDTO.getTitulaire());
        return moyenPaiementEntity;

    }
    public int getIdMoyenpaiement() {
        return idMoyenpaiement;
    }

    public void setIdMoyenpaiement(int idMoyenpaiement) {
        this.idMoyenpaiement = idMoyenpaiement;
    }

    public TypeCarteEntity getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(TypeCarteEntity typeCarte) {
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
        return "MoyenPaiementEntity{" +
                "idMoyenpaiement=" + idMoyenpaiement +
                ", typeCarte=" + typeCarte +
                ", numCompte=" + numCompte +
                ", dateExpiration=" + dateExpiration +
                ", cryptogramme=" + cryptogramme +
                ", titulaire='" + titulaire + '\'' +
                '}';
    }
}
