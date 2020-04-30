package fr.miage.orleans.isi.projet.museews.entities;

import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import objects.musee.Oeuvre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class SallesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String value;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OeuvreEntity> oeuvres;

    public SallesEntity() {
    }

    public static SallesEntity createEntityFromDTO(SalleDTO salleDTO){
        SallesEntity sallesEntity = new SallesEntity();
        sallesEntity.setId(salleDTO.getId());
        sallesEntity.setValue(salleDTO.getValue());
        if(salleDTO.getOeuvres()==null){
            sallesEntity.setOeuvres(new ArrayList<OeuvreEntity>());
        }else {
            for (OeuvreDTO oe : salleDTO.getOeuvres()) {
                sallesEntity.getOeuvres().add(OeuvreDTO.createEntityFromDto(oe));
            }
        }
        return sallesEntity;
    }

    public static SalleDTO createDTOFromEntity(SallesEntity sallesEntity) {
        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setId(sallesEntity.getId());
        salleDTO.setValue(sallesEntity.getValue());
        if(sallesEntity.getOeuvres()==null){
            salleDTO.setOeuvres(new ArrayList<OeuvreDTO>());
        }else{
            for(OeuvreEntity oe : sallesEntity.getOeuvres()){
                salleDTO.addOeuvre(OeuvreDTO.createOeuvreDTOFromEntity(oe));
            }
        }
        return salleDTO;
    }

    public List<OeuvreEntity> getOeuvres() {
        return oeuvres;
    }

    public void setOeuvres(List<OeuvreEntity> oeuvres) {
        this.oeuvres = oeuvres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
