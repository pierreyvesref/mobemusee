package fr.miage.orleans.isi.projet.paiementws.entities;


import fr.miage.orleans.isi.projet.paiementws.DTO.TypeCarteDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeCarteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String value;

    public TypeCarteEntity() {
    }

    public static TypeCarteEntity createTypeFromDTO(TypeCarteDTO typeCarte) {
        TypeCarteEntity typeCarteEntity = new TypeCarteEntity();
        typeCarteEntity.setValue(typeCarte.getValue());
        return typeCarteEntity;
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

    @Override
    public String toString() {
        return "TypeCarteEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
