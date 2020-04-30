package fr.miage.orleans.isi.projet.museews.dao;

import fr.miage.orleans.isi.projet.museews.dto.DeplacementDTO;
import fr.miage.orleans.isi.projet.museews.entities.DeplacementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeplacementDAO extends CrudRepository<DeplacementEntity, Integer> {
    DeplacementEntity save(DeplacementEntity deplacementEntity);
    List<DeplacementEntity> findAllByOeuvre_Id(Integer id);
}
