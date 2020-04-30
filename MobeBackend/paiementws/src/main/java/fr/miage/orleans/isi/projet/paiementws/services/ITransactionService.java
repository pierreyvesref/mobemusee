package fr.miage.orleans.isi.projet.paiementws.services;

import fr.miage.orleans.isi.projet.paiementws.entities.BilletMuseeEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;

import java.util.List;

public interface ITransactionService {
    TransactionEntity create(MoyenPaiementEntity moyenPaiement, List<BilletMuseeEntity> billetsMusee, double montant);
}
