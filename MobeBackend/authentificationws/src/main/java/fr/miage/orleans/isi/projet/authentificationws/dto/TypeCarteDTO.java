package fr.miage.orleans.isi.projet.authentificationws.dto;

import fr.miage.orleans.isi.projet.authentificationws.entities.TypeCarteEntity;
import objects.paiement.TypeCarte;

public class TypeCarteDTO {
    private String value;

    public TypeCarteDTO(String value) {
        this.value = value;
    }

    public static TypeCarteDTO createDTOFromBasic(TypeCarte typeCarte) {
        return new TypeCarteDTO(typeCarte.getValue());
    }

    public static TypeCarteDTO createDTOFromEntity(TypeCarteEntity typeCarte) {
        return new TypeCarteDTO(typeCarte.getValue());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
