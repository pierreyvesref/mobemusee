package fr.miage.orleans.isi.projet.authentificationws.services;

import fr.miage.orleans.isi.projet.authentificationws.dto.RoleDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.UserDTO;
import fr.miage.orleans.isi.projet.authentificationws.exceptions.*;
import objects.users.User;

import javax.management.relation.RoleNotFoundException;
import java.util.Collection;
import java.util.List;

public interface IFacadeAuthentification {
    UserDTO creerUser(String login, String mdp, List<RoleDTO> roles, String nom, String prenom, String mail) throws LoginDejaUtiliseException, LoginOuMdpNonConformeException, RoleNotFoundException;
    UserDTO connexionUser(String login, String mdp) throws MdpIncorrectException, LoginInexistantException, LoginDejaConnecteException;
    UserDTO deconnexionUser(String login) throws LoginNonConnecteException, LoginInexistantException;
    Collection<User> getAllUsers();
    Collection<UserDTO> getUsersConnectes();
    UserDTO getUserByLogin(String login);

    List<RoleDTO> getRoleByLogin(String login);
}
