package fr.miage.orleans.isi.projet.museews.entities;

import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import objects.musee.Oeuvre;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class OeuvreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nomOeuvre;
    private Date dateAchat;
    private double prixAchat;

    public OeuvreEntity() {
    }

    public static OeuvreDTO createDTOFromSelf(OeuvreEntity oe) {
        OeuvreDTO oeuvreDTO = new OeuvreDTO();
        oeuvreDTO.setId(oe.getId());
        oeuvreDTO.setDateAchat(oe.getDateAchat());
        oeuvreDTO.setPrixAchat(oe.getPrixAchat());
        return oeuvreDTO;
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

    public OeuvreDTO createDTOFromSelf() {
        OeuvreDTO oeuvreDTO = new OeuvreDTO();
        oeuvreDTO.setId(this.getId());
        oeuvreDTO.setDateAchat(this.getDateAchat());
        oeuvreDTO.setPrixAchat(this.getPrixAchat());
        return oeuvreDTO;
    }
}
