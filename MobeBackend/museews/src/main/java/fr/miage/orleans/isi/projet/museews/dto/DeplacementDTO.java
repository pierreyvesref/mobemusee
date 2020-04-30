package fr.miage.orleans.isi.projet.museews.dto;

import fr.miage.orleans.isi.projet.museews.entities.DeplacementEntity;
import fr.miage.orleans.isi.projet.museews.entities.SallesEntity;

import java.util.Date;

public class DeplacementDTO {
    private int id;
    private SalleDTO salle;
    private OeuvreDTO oeuvre;
    private Date dateDeplacement;


    public static DeplacementDTO createFromEntity(DeplacementEntity deplacementEntity){
        DeplacementDTO deplacementDTO = new DeplacementDTO();
        deplacementDTO.setId(deplacementEntity.getId());
        deplacementDTO.setSalle(SallesEntity.createDTOFromEntity(deplacementEntity.getSalle()));
        deplacementDTO.setOeuvre(OeuvreDTO.createOeuvreDTOFromEntity(deplacementEntity.getOeuvre()));
        deplacementDTO.setDateDeplacement(deplacementEntity.getDateDeplacement());
        return deplacementDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SalleDTO getSalle() {
        return salle;
    }

    public void setSalle(SalleDTO salle) {
        this.salle = salle;
    }

    public OeuvreDTO getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(OeuvreDTO oeuvre) {
        this.oeuvre = oeuvre;
    }

    public Date getDateDeplacement() {
        return dateDeplacement;
    }

    public void setDateDeplacement(Date dateDeplacement) {
        this.dateDeplacement = dateDeplacement;
    }
}
