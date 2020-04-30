package fr.miage.orleans.isi.projet.authentificationws.services;

import fr.miage.orleans.isi.projet.authentificationws.dto.RoleDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.UserDTO;
import fr.miage.orleans.isi.projet.authentificationws.entities.RoleEntity;
import fr.miage.orleans.isi.projet.authentificationws.entities.UserEntity;
import objects.users.Role;

import java.util.Date;
import java.util.List;

public interface IUserService {

    public UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();
    UserDTO createUser(String login, String mdp, List<RoleDTO> roles, String nom, String prenom, String mail);
    UserDTO createUser(String login, String mdp, String nom, String prenom, String mail);
    void delete(int id);
    void ajouterRoleUser(String login, RoleEntity r);
    UserDTO getUserByLogin(String login);
    UserEntity getUserEntityByLogin(String login);
    String getMdp(String login);
}
