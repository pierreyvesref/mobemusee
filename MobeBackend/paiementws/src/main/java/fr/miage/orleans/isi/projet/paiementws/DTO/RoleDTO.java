package fr.miage.orleans.isi.projet.paiementws.DTO;

import objects.users.Role;

public class RoleDTO {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public RoleDTO() {
    }

    public static RoleDTO createFromBasic(Role role){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setValue(role.getValue());
            return roleDTO;
    }
}
