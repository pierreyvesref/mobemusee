package fr.miage.orleans.isi.projet.paiementws.services.impl;

import fr.miage.orleans.isi.projet.paiementws.dao.TransactionDAO;
import fr.miage.orleans.isi.projet.paiementws.entities.BilletMuseeEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import fr.miage.orleans.isi.projet.paiementws.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionService implements ITransactionService {
    @Autowired
    TransactionDAO transactionDAO;

    @Override
    public TransactionEntity create(MoyenPaiementEntity moyenPaiement, List<BilletMuseeEntity> billetsMusee, double montant) {
       TransactionEntity transactionEntity = new TransactionEntity();
       transactionEntity.setMontant(montant);
       transactionEntity.setBilletsMusee(billetsMusee);
       transactionEntity.setMoyenPaiement(moyenPaiement);
       Date dt = Date.from(Instant.now());
       transactionEntity.setDateTransaction(dt);
       System.out.println("-----------" + transactionEntity);
       transactionDAO.save(transactionEntity);
       return  transactionEntity;

    }
}
