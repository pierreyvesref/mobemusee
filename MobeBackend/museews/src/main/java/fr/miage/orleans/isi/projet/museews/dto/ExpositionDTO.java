package fr.miage.orleans.isi.projet.museews.dto;

import objects.musee.Exposition;

import java.util.Date;

public class ExpositionDTO {

    private int id;
    private String nom;
    private Date dateDebutExpo;
    private Date dateFinExpo;

    public static ExpositionDTO createExpositionDTO(Exposition exposition) {
        ExpositionDTO expositionDTO = new ExpositionDTO();
        expositionDTO.setNom(exposition.getNom());
        expositionDTO.setDateDebutExpo(exposition.getDateDebutExpo());
        expositionDTO.setDateFinExpo(exposition.getDateFinExpo());

        return expositionDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebutExpo() {
        return dateDebutExpo;
    }

    public void setDateDebutExpo(Date dateDebutExpo) {
        this.dateDebutExpo = dateDebutExpo;
    }

    public Date getDateFinExpo() {
        return dateFinExpo;
    }

    public void setDateFinExpo(Date dateFinExpo) {
        this.dateFinExpo = dateFinExpo;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebutExpo=" + dateDebutExpo +
                ", dateFinExpo=" + dateFinExpo +
                '}';
    }

}
