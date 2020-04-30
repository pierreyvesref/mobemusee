package fr.miage.orleans.isi.projet.paiementws.services.impl;


import fr.miage.orleans.isi.projet.paiementws.DTO.RoleDTO;
import fr.miage.orleans.isi.projet.paiementws.dao.RoleDAO;
import fr.miage.orleans.isi.projet.paiementws.entities.RoleEntity;
import fr.miage.orleans.isi.projet.paiementws.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleService implements IRoleService {
    @Autowired
    RoleDAO roleDAO;

    @Override
    public RoleDTO getRoleDTOByValue(String value) {
        return RoleEntity.createDTOFromEntity(roleDAO.findRoleEntityByValue(value));
    }

    @Override
    @Transactional
    public RoleDTO createRole(String value) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setValue(value);
        roleDAO.save(roleEntity);
        return RoleEntity.createDTOFromEntity(roleEntity);
    }

    @Override
    public RoleDTO createRoleFromDTO(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setValue(roleDTO.getValue());
        roleDAO.save(roleEntity);
        return RoleEntity.createDTOFromEntity(roleEntity);
    }

    @Override
    public RoleEntity getRoleEntityByValue(String value) {
        return roleDAO.findRoleEntityByValue(value);
    }
}
