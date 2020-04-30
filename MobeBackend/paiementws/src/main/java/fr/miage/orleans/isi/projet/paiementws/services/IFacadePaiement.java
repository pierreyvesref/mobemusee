package fr.miage.orleans.isi.projet.paiementws.services;

import fr.miage.orleans.isi.projet.paiementws.DTO.MoyenPaiementDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.TransactionDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.TypeCarteDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.UserDTO;
import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import fr.miage.orleans.isi.projet.paiementws.exceptions.*;
import objects.paiement.BilletMusee;
import objects.paiement.Transaction;
import objects.paiement.TypeCarte;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IFacadePaiement {
    List<MoyenPaiementDTO> getMoyensPaiementByLogin(String login) throws UtilisateurInexistantException;

    MoyenPaiementDTO ajouterMoyenPaiement(String login, TypeCarteDTO typeCarte, long numCompte, Date dateExpiration, int cryptogramme, String titulaire) throws UtilisateurInexistantException, MoyenPaiementDejaAjouteException;

    void supprimerMoyenPaiement(String login, int idMoyenPaiement) throws MoyenPaiementInexistantException, UtilisateurInexistantException;

    TransactionEntity effectuerTransaction(String login, int moyenPaiementId, TransactionDTO transaction) throws MoyenPaiementInexistantException, ErreurTransactionException, UtilisateurInexistantException, AucunBilletAcheteException;

    default boolean verificationBanque(MoyenPaiementEntity moyenPaiement, double montant) {
        return true;
    }

    List<TransactionDTO> getTransactionsByLogin(String login) throws UtilisateurInexistantException;

    List<BilletMusee> getAllBilletsMusee();

    //Mettre à jour si la date du billet a expiré
    void majValiditeBillets();

    void scannerQRCode(String qrcodeKey) throws QrCodeNonReconnuException, BilletInvalideException;

    Collection<UserDTO> getAllUsers();

    Collection<UserDTO> getUsersConnectes();

    UserDTO getUserByLogin(String login);
}
