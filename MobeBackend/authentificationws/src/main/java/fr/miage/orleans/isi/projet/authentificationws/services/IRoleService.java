package fr.miage.orleans.isi.projet.authentificationws.services;

import fr.miage.orleans.isi.projet.authentificationws.dto.RoleDTO;
import fr.miage.orleans.isi.projet.authentificationws.entities.RoleEntity;
import fr.miage.orleans.isi.projet.authentificationws.exceptions.RoleDejaExistantException;

public interface IRoleService {
    RoleDTO getRoleDTOByValue(String value);
    RoleDTO createRole(String value);
    RoleDTO createRoleFromDTO(RoleDTO roleDTO);
    RoleEntity getRoleEntityByValue(String value);
}
