package fr.miage.orleans.isi.projet.paiementws.dao;

import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoyenPaiementDAO extends CrudRepository<MoyenPaiementEntity, Integer> {
    MoyenPaiementEntity findByIdMoyenpaiement(int idMoyenPaiement);
}
