package fr.miage.orleans.isi.projet.museews.entities;

import fr.miage.orleans.isi.projet.museews.dto.DeplacementDTO;
import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import objects.musee.Deplacement;
import objects.musee.Oeuvre;
import objects.musee.Salle;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DeplacementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private SallesEntity salle;
    @ManyToOne
    private OeuvreEntity oeuvre;
    private Date dateDeplacement;

    public DeplacementEntity() {
    }

    public static DeplacementEntity createFromDTO(DeplacementDTO deplacementDTO){
        DeplacementEntity deplacementEntity = new DeplacementEntity();
        deplacementEntity.setSalle(SallesEntity.createEntityFromDTO(deplacementDTO.getSalle()));
        deplacementEntity.setOeuvre(OeuvreDTO.createEntityFromDto(deplacementDTO.getOeuvre()));
        deplacementEntity.setDateDeplacement(deplacementDTO.getDateDeplacement());
        return deplacementEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SallesEntity getSalle() {
        return salle;
    }

    public void setSalle(SallesEntity salle) {
        this.salle = salle;
    }

    public OeuvreEntity getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(OeuvreEntity oeuvre) {
        this.oeuvre = oeuvre;
    }

    public Date getDateDeplacement() {
        return dateDeplacement;
    }

    public void setDateDeplacement(Date dateDeplacement) {
        this.dateDeplacement = dateDeplacement;
    }
}
