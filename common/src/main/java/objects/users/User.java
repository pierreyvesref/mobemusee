package objects.users;

import objects.paiement.MoyenPaiement;
import objects.paiement.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String login;
    private String mdp;
    private List<Role> roles;
    private String nom;
    private String prenom;
    private String mail;
    private Date dateInscription;

    private List<MoyenPaiement> moyensPaiement;

    private List<Transaction> transactions;

    public User() {
    }

    public User(String login, String mdp, List<Role> roles, String nom, String prenom, String mail) {
        this.login = login;
        this.mdp = mdp;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.dateInscription = new Date();
        moyensPaiement = new ArrayList<>();
        transactions = new ArrayList<>();
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
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

    public void addMoyenPaiement(MoyenPaiement moyenPaiement){
        moyensPaiement.add(moyenPaiement);
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public List<MoyenPaiement> getMoyensPaiement() {
        return moyensPaiement;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public MoyenPaiement getMoyenPaiementById(int moyenPaiementId){
        MoyenPaiement moyenPaiement = moyensPaiement.stream()
                .filter(m -> m.getIdMoyenpaiement() == moyenPaiementId)
                .findFirst().get();

        return moyenPaiement;
    }

    public Transaction getTransactionById(int transactionId){
        Transaction transaction = transactions.stream()
                .filter(m -> m.getIdTransaction() == transactionId)
                .findFirst().get();

        return transaction;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                ", roles=" + roles +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", dateInscription=" + dateInscription +
                ", moyensPaiement=" + moyensPaiement +
                ", transactions=" + transactions +
                '}';
    }
}
