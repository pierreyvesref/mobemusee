package fr.miage.orleans.isi.projet.authentificationws.dto;

import objects.users.Role;

public class RoleDTO {
    private String value;

    public RoleDTO() {
    }

    public RoleDTO(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleDTO createFromBasic(Role role){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setValue(role.getValue());
            return roleDTO;
    }
}
