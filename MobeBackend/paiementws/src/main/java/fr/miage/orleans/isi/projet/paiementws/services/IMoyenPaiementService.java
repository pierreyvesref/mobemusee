package fr.miage.orleans.isi.projet.paiementws.services;

import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TypeCarteEntity;

import java.util.Date;

public interface IMoyenPaiementService {
    MoyenPaiementEntity creerMP(TypeCarteEntity value, long numCompte, Date dateExpiration, int cryptogramme, String titulaire);
    MoyenPaiementEntity getById(int idMoyenPaiement);
    void supprimerTP(MoyenPaiementEntity mp);
}
