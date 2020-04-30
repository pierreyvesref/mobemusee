package fr.miage.orleans.isi.projet.paiementws.dao;

import fr.miage.orleans.isi.projet.paiementws.entities.TypeCarteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCarteDAO extends CrudRepository<TypeCarteEntity, Integer> {
    TypeCarteEntity findByValue(String value);
}
