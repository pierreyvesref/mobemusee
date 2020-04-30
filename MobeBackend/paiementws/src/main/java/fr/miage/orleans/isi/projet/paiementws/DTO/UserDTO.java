package fr.miage.orleans.isi.projet.paiementws.DTO;

import objects.paiement.MoyenPaiement;
import objects.paiement.Transaction;
import objects.users.Role;
import objects.users.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {

    private String login;
    private String mdp;
    private List<RoleDTO> roles;
    private String nom;
    private String prenom;
    private String mail;
    private Date dateInscription;
    private List<MoyenPaiementDTO> moyenPaiements;
    private List<TransactionDTO> transactions;

    public UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setMdp(user.getMdp());
        userDTO.setRoles(this.getListRoleDTOFromListBasic(user.getRoles()));
        userDTO.setNom(user.getNom());
        userDTO.setPrenom(user.getPrenom());
        userDTO.setMail(user.getMail());
        userDTO.setDateInscription(user.getDateInscription());
        userDTO.setMoyenPaiements(this.getListMPDTOFromBasic(user.getMoyensPaiement()));
        userDTO.setTransactions(this.getListTransacDTOFromBasic(user.getTransactions()));
        return userDTO;
    }

    private List<TransactionDTO> getListTransacDTOFromBasic(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for(Transaction transaction : transactions){
            transactionDTOS.add(TransactionDTO.createFromBasic(transaction));
        }
        return transactionDTOS;
    }

    private List<RoleDTO> getListRoleDTOFromListBasic(List<Role> roles) {
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles) {
            roleDTOS.add(RoleDTO.createFromBasic(role));
        }
        return  roleDTOS;
    }

    private List<MoyenPaiementDTO> getListMPDTOFromBasic(List<MoyenPaiement> mps){
        List<MoyenPaiementDTO> moyenPaiementDTOS = new ArrayList<>();
        for(MoyenPaiement mp : mps){
            moyenPaiementDTOS.add(MoyenPaiementDTO.createFromBasic(mp));
        }
        return moyenPaiementDTOS;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public List<MoyenPaiementDTO> getMoyenPaiements() {
        return moyenPaiements;
    }

    public void setMoyenPaiements(List<MoyenPaiementDTO> moyenPaiements) {
        this.moyenPaiements = moyenPaiements;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                ", roles=" + roles +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", dateInscription=" + dateInscription +
                ", moyenPaiements=" + moyenPaiements +
                '}';
    }
}
