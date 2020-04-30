package fr.miage.orleans.isi.projet.paiementws.dao;

import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends CrudRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findAll();
    TransactionEntity save(TransactionEntity transactionEntity);
}
