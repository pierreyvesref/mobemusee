package fr.miage.orleans.isi.projet.paiementws.dao;


import fr.miage.orleans.isi.projet.paiementws.entities.BilletMuseeEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilletDAO extends CrudRepository<BilletMuseeEntity, Integer> {
    List<BilletMuseeEntity> findAll();
    BilletMuseeEntity findBilletMuseeEntityById(Integer id);
    BilletMuseeEntity save(BilletMuseeEntity billetMuseeEntity);
    BilletMuseeEntity findFirstByOrderByIdDesc();
}
