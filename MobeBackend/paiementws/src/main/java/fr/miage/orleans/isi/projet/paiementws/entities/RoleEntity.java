package fr.miage.orleans.isi.projet.paiementws.entities;

import fr.miage.orleans.isi.projet.paiementws.DTO.RoleDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String value;

    public RoleEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleDTO createDTOFromEntity(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setValue(roleEntity.getValue());
        return roleDTO;
    }

    public static RoleEntity createEntityFromDTO(RoleDTO roleDTO){
        RoleEntity roleEntity= new RoleEntity();
        roleEntity.setValue(roleDTO.getValue());
        return roleEntity;
    }
}
