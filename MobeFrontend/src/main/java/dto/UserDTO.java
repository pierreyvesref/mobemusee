package dto;

import java.util.List;

public class UserDTO {

    private String login;
    private String mdp;
    private List<RoleDTO> roles;
    private String nom;
    private String prenom;
    private String mail;

    private List<MoyenPaiementDTO> moyensPaiement;

    private List<TransactionDTO> transactions;

    public UserDTO() {
    }

    public UserDTO(String login, String mdp, List<RoleDTO> roles, String nom, String prenom, String mail,
                   List<MoyenPaiementDTO> moyensPaiement, List<TransactionDTO> transactions) {
        this.login = login;
        this.mdp = mdp;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.moyensPaiement = moyensPaiement;
        this.transactions = transactions;
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

    public List<MoyenPaiementDTO> getMoyensPaiement() {
        return moyensPaiement;
    }

    public void setMoyensPaiement(List<MoyenPaiementDTO> moyensPaiement) {
        this.moyensPaiement = moyensPaiement;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

}
