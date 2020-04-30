package fr.miage.orleans.isi.projet.authentificationws.dao;

import fr.miage.orleans.isi.projet.authentificationws.entities.TypeTarifEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTarifDAO extends CrudRepository <TypeTarifEntity, Integer> {
}
