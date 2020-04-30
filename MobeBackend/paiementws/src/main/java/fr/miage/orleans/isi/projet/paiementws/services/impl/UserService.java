package fr.miage.orleans.isi.projet.paiementws.services.impl;


import fr.miage.orleans.isi.projet.paiementws.DTO.RoleDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.UserDTO;
import fr.miage.orleans.isi.projet.paiementws.dao.UserDAO;
import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.RoleEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.UserEntity;
import fr.miage.orleans.isi.projet.paiementws.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDTO getUserById(Integer id) {
        UserEntity userEntity =userDAO.findUserEntityById(id);
        if(userEntity==null){
            return null;
        }else{
            return UserEntity.createUserDTOFromEntity(userEntity);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userDAO.findAll();
        return userEntities.stream().map(UserEntity::createUserDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO createUser(String login, String mdp, List<RoleDTO> roles, String nom, String prenom, String mail) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setMdp(mdp);
        userEntity.setRoles(userEntity.getListREFromRD(roles));
        userEntity.setNom(nom);
        userEntity.setPrenom(prenom);
        userEntity.setMail(mail);
        userEntity.setMoyenPaiements(new ArrayList<>());
        userEntity.setTransactions(new ArrayList<>());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        userEntity.setDateInscription(date);
        userDAO.save(userEntity);
        return UserEntity.createUserDTOFromEntity(userEntity);
    }

    @Override
    public UserDTO createUser(String login, String mdp, String nom, String prenom, String mail) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setMdp(mdp);
        userEntity.setRoles(new ArrayList<>());
        userEntity.setNom(nom);
        userEntity.setPrenom(prenom);
        userEntity.setMail(mail);
        userEntity.setMoyenPaiements(new ArrayList<>());
        userEntity.setTransactions(new ArrayList<>());
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        userEntity.setDateInscription(date);
        userDAO.save(userEntity);
        return UserEntity.createUserDTOFromEntity(userEntity);
    }


    @Override
    public void delete(int id) {
        userDAO.removeById(id);
    }

    @Override
    public void ajouterRoleUser(String login, RoleEntity r) {
        UserEntity userEntity = userDAO.findUserEntityByLogin(login);
        List<RoleEntity> list = userEntity.getRoles();
        list.add(r);
        userEntity.setRoles(list);
        userDAO.save(userEntity);
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return UserEntity.createUserDTOFromEntity(userDAO.findUserEntityByLogin(login));
    }

    @Override
    public UserEntity getUserEntityByLogin(String login) {
        return userDAO.findUserEntityByLogin(login);
    }

    @Override
    public String getMdp(String login) {
        return userDAO.findUserEntityByLogin(login).getMdp();
    }

    @Override
    public void addMoyenPaiement(String login, MoyenPaiementEntity moyenPaiement) {
        UserEntity us = userDAO.findUserEntityByLogin(login);
        us.getMoyenPaiements().add(moyenPaiement);
        userDAO.save(us);
    }

    @Override
    public void removeMoyenPaiement(String login, MoyenPaiementEntity byId) {
        UserEntity us = userDAO.findUserEntityByLogin(login);
        us.getMoyenPaiements().remove(byId);
        userDAO.save(us);
    }

    @Override
    public void addTransaction(String login, TransactionEntity transactionEffectuee) {
        UserEntity us = userDAO.findUserEntityByLogin(login);
        us.getTransactions().add(transactionEffectuee);
        userDAO.save(us);
    }


}
