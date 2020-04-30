package fr.miage.orleans.isi.projet.authentificationws.dao;

import fr.miage.orleans.isi.projet.authentificationws.entities.TypeCarteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCarteDAO extends CrudRepository<TypeCarteEntity, Integer> {
}
