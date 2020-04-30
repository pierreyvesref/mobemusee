package fr.miage.orleans.isi.projet.authentificationws.dao;

import fr.miage.orleans.isi.projet.authentificationws.entities.MoyenPaiementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoyenPaiementDAO extends CrudRepository<MoyenPaiementEntity, Integer> {
}
