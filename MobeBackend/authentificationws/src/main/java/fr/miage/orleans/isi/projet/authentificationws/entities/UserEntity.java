package fr.miage.orleans.isi.projet.authentificationws.entities;

import fr.miage.orleans.isi.projet.authentificationws.dto.MoyenPaiementDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.RoleDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.TransactionDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.UserDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String mdp;
    @OneToMany
    private List<RoleEntity> roles;
    private String nom;
    private String prenom;
    private String mail;
    private Date dateInscription;
    @OneToMany
    private List<MoyenPaiementEntity> moyenPaiements;
    @OneToMany
    private List<TransactionEntity> transactions;

    public UserEntity() {
    }

    public UserEntity createUserEntityFromDTO(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(user.getLogin());
        userEntity.setMdp(user.getMdp());
        userEntity.setRoles(this.getListREFromRD(user.getRoles()));
        userEntity.setNom(user.getNom());
        userEntity.setPrenom(user.getPrenom());
        userEntity.setMail(user.getMail());
        userEntity.setDateInscription(user.getDateInscription());
        userEntity.setMoyenPaiements(this.getListMPEFromMPDTO(user.getMoyenPaiements()));
        userEntity.setTransactions(this.getListTransEntityFromTransacDTO(user.getTransactions()));
        return userEntity;
    }

    private List<TransactionEntity> getListTransEntityFromTransacDTO(List<TransactionDTO> transactions) {
        List<TransactionEntity> mpe = new ArrayList<>();
        for (TransactionDTO moe : transactions) {
            mpe.add(TransactionEntity.createFromDTO(moe));
        }
        return mpe;
    }

    private List<MoyenPaiementEntity> getListMPEFromMPDTO(List<MoyenPaiementDTO> moyenPaiements) {
        List<MoyenPaiementEntity> mpe = new ArrayList<>();
        for (MoyenPaiementDTO moe : moyenPaiements) {
            mpe.add(MoyenPaiementEntity.createFromDTO(moe));
        }
        return mpe;
    }

    public static UserDTO createUserDTOFromEntity(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setMdp(user.getMdp());
        userDTO.setRoles(user.getListRoleDTOFromListRoleEntity());
        userDTO.setNom(user.getNom());
        userDTO.setPrenom(user.getPrenom());
        userDTO.setMail(user.getMail());
        userDTO.setDateInscription(user.getDateInscription());
        userDTO.setMoyenPaiements(user.getListMPDFromMPE(user.getMoyenPaiements()));
        userDTO.setTransactions(user.getListTransacDTOFromEntity(user.getTransactions()));
        return userDTO;
    }

    private List<TransactionDTO> getListTransacDTOFromEntity(List<TransactionEntity> transactionEntities){
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for(TransactionEntity tr : transactionEntities){
            transactionDTOS.add(TransactionDTO.createFromEntity(tr));
        }
        return transactionDTOS;
    }

    private List<MoyenPaiementDTO> getListMPDFromMPE(List<MoyenPaiementEntity> moyenPaiements) {
        List<MoyenPaiementDTO> moyenPaiementDTOS = new ArrayList<>();
            for(MoyenPaiementEntity mp : moyenPaiements) {
                moyenPaiementDTOS.add(MoyenPaiementDTO.createFromEntity(mp));
            }
        return moyenPaiementDTOS;
    }

    public List<RoleEntity> getListREFromRD(List<RoleDTO> roleDTOS){
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (RoleDTO roleDTO : roleDTOS){
            roleEntities.add(RoleEntity.createEntityFromDTO(roleDTO));
        }
        return roleEntities;
    }

    public List<RoleDTO> getListRoleDTOFromListRoleEntity(){
        return this.getRoles().stream().map(RoleEntity::createDTOFromEntity).collect(Collectors.toList());
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

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
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

    public List<MoyenPaiementEntity> getMoyenPaiements() {
        return moyenPaiements;
    }

    public void setMoyenPaiements(List<MoyenPaiementEntity> moyenPaiements) {
        this.moyenPaiements = moyenPaiements;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
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
