package fr.miage.orleans.isi.projet.museews.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ExpositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private Date dateDebutExpo;
    private Date dateFinExpo;

    public ExpositionEntity(){

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
}
