package fr.miage.orleans.isi.projet.authentificationws.entities;

import fr.miage.orleans.isi.projet.authentificationws.dto.RoleDTO;

import javax.persistence.*;
import java.util.List;

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
