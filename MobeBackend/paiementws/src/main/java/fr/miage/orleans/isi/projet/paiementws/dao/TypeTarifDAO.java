package fr.miage.orleans.isi.projet.paiementws.dao;

import fr.miage.orleans.isi.projet.paiementws.entities.TypeTarifEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTarifDAO extends CrudRepository <TypeTarifEntity, Integer> {
    TypeTarifEntity findTypeTarifEntityByValue(String value);
}
