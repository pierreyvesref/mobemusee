package fr.miage.orleans.isi.projet.authentificationws.services.impl;

import fr.miage.orleans.isi.projet.authentificationws.dto.MoyenPaiementDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.RoleDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.TransactionDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.UserDTO;
import fr.miage.orleans.isi.projet.authentificationws.entities.UserEntity;
import fr.miage.orleans.isi.projet.authentificationws.exceptions.*;
import fr.miage.orleans.isi.projet.authentificationws.services.IFacadeAuthentification;
import objects.paiement.BilletMusee;
import objects.paiement.MoyenPaiement;
import objects.paiement.TypeCarte;
import objects.paiement.TypeTarif;
import objects.users.Role;
import objects.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.management.relation.RoleNotFoundException;
import java.util.*;
import java.util.function.Predicate;

import static factory.ObjectsFactory.*;

@Service
public class FacadeAuthentification implements IFacadeAuthentification {

    Map<String, User> users = new HashMap<>();

    Map<String,UserDTO> usersConnectes = new HashMap<>();

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    //A LANCER SI BASE VIDE
    /*@PostConstruct
    public void init() {
        roleService.createRoleFromDTO(RoleDTO.createFromBasic(Role.USER));
        roleService.createRoleFromDTO(RoleDTO.createFromBasic(Role.ADMIN));
    }*/

    @Override
    public UserDTO creerUser(String login, String mdp, List<RoleDTO> roles, String nom, String prenom, String mail) throws LoginDejaUtiliseException, LoginOuMdpNonConformeException, RoleNotFoundException {
        Predicate<String> isOk = s -> !s.isEmpty() && s.length() >= 2 ;
        UserDTO user = new UserDTO();
        if(isOk.test(login) && isOk.test(mdp)) {
            if(userService.getUserEntityByLogin(login)!=null){
                throw new LoginDejaUtiliseException();
            }else{
                RoleDTO role = new RoleDTO();
                for (RoleDTO r : roles) {
                    role = roleService.getRoleDTOByValue(r.getValue());
                    if(role == null){
                        throw new RoleNotFoundException();
                    }
                }
                UserDTO user2 = userService.createUser(login, mdp, nom, prenom, mail);
                for (RoleDTO r : roles) {
                    System.out.println((roleService.getRoleDTOByValue(r.getValue())).toString());
                    userService.ajouterRoleUser(login, roleService.getRoleEntityByValue(r.getValue()));
                }
                return userService.getUserByLogin(login);
            }
        }else throw new LoginOuMdpNonConformeException();
    }

    @Override
    public UserDTO connexionUser(String login, String mdp) throws MdpIncorrectException, LoginInexistantException, LoginDejaConnecteException {
        UserEntity userEntity = userService.getUserEntityByLogin(login);
        UserDTO user = null;
        if(userEntity != null){
            user = userService.getUserByLogin(login);
            if(user.getMdp().equals(mdp)){
                if(!usersConnectes.containsKey(login)){
                    usersConnectes.put(login, user);
                } else throw new LoginDejaConnecteException();
            }else throw new MdpIncorrectException();
        } else throw new LoginInexistantException();
        return user;
    }

    @Override
    public UserDTO deconnexionUser(String login) throws LoginInexistantException, LoginNonConnecteException {
        UserDTO user = userService.getUserByLogin(login);
        if(user != null){
            if(usersConnectes.containsKey(user.getLogin())){
                usersConnectes.remove(user.getLogin());
            } else throw new LoginNonConnecteException();
        } else throw new LoginInexistantException();
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }


    @Override
    public Collection<UserDTO> getUsersConnectes() {
        return usersConnectes.values();
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return userService.getUserByLogin(login);
    }

    @Override
    public List<RoleDTO> getRoleByLogin(String login) {
        return userService.getUserByLogin(login).getRoles();
    }


}
