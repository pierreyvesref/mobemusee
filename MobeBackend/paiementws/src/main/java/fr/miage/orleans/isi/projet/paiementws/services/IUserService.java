package fr.miage.orleans.isi.projet.paiementws.services;



import fr.miage.orleans.isi.projet.paiementws.DTO.RoleDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.UserDTO;
import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.RoleEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.UserEntity;

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
    void addMoyenPaiement(String login, MoyenPaiementEntity moyenPaiement);

    void removeMoyenPaiement(String login, MoyenPaiementEntity byId);

    void addTransaction(String login, TransactionEntity transactionEffectuee);
}
