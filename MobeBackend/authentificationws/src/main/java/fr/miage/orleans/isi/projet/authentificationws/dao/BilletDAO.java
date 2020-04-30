package fr.miage.orleans.isi.projet.authentificationws.dao;

import fr.miage.orleans.isi.projet.authentificationws.entities.BilletMuseeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilletDAO extends CrudRepository<BilletMuseeEntity, Integer> {
}
