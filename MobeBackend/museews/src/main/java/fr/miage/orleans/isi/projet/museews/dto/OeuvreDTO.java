package fr.miage.orleans.isi.projet.museews.dto;

import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import objects.musee.Oeuvre;

import java.util.Date;

public class OeuvreDTO {

    private int id;
    private String nomOeuvre;
    private Date dateAchat;
    private double prixAchat;

    public OeuvreDTO() {
    }

    public OeuvreDTO(String nomOeuvre, Date dateAchat, double prixAchat) {
        this.setId(0);
        this.setNomOeuvre(nomOeuvre);
        this.setDateAchat(dateAchat);
        this.setPrixAchat(prixAchat);
    }

    public OeuvreDTO(int id, String nomOeuvre, Date dateAchat, double prixAchat) {
        this.setId(id);
        this.setNomOeuvre(nomOeuvre);
        this.setDateAchat(dateAchat);
        this.setPrixAchat(prixAchat);
    }

    public static OeuvreDTO createOeuvreDTO(Oeuvre oeuvre) {
        OeuvreDTO oeuvreDTO = new OeuvreDTO();
        oeuvreDTO.setNomOeuvre(oeuvre.getNomOeuvre());
        oeuvreDTO.setDateAchat(oeuvre.getDateAchat());
        oeuvreDTO.setPrixAchat(oeuvre.getPrixAchat());
        return oeuvreDTO;
    }

    public static OeuvreDTO createOeuvreDTOFromEntity(OeuvreEntity oeuvre) {
        OeuvreDTO oeuvreDTO = new OeuvreDTO();
        oeuvreDTO.setNomOeuvre(oeuvre.getNomOeuvre());
        oeuvreDTO.setDateAchat(oeuvre.getDateAchat());
        oeuvreDTO.setPrixAchat(oeuvre.getPrixAchat());
        return oeuvreDTO;
    }

    public static OeuvreEntity createEntityFromDto(OeuvreDTO oeuvreDTO) {
        OeuvreEntity oeuvreEntity = new OeuvreEntity();
        oeuvreEntity.setId(oeuvreDTO.getId());
        oeuvreEntity.setPrixAchat(oeuvreDTO.getPrixAchat());
        oeuvreEntity.setDateAchat(oeuvreDTO.getDateAchat());
        oeuvreEntity.setNomOeuvre(oeuvreDTO.getNomOeuvre());
        return oeuvreEntity;
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

    @Override
    public String toString() {
        return "OeuvreDTO{" +
                "nomOeuvre='" + nomOeuvre + '\'' +
                ", dateAchat=" + dateAchat +
                ", prixAchat=" + prixAchat +
                '}';
    }

}
