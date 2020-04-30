package fr.miage.orleans.isi.projet.paiementws.services;


import fr.miage.orleans.isi.projet.paiementws.DTO.RoleDTO;
import fr.miage.orleans.isi.projet.paiementws.entities.RoleEntity;

public interface IRoleService {
    RoleDTO getRoleDTOByValue(String value);
    RoleDTO createRole(String value);
    RoleDTO createRoleFromDTO(RoleDTO roleDTO);
    RoleEntity getRoleEntityByValue(String value);
}
