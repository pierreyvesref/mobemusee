package fr.miage.orleans.isi.projet.museews.dao;

import fr.miage.orleans.isi.projet.museews.entities.ExpositionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpositionDAO extends CrudRepository<ExpositionEntity, Integer> {
    ExpositionEntity findExpositionEntityById(Integer id);
    ExpositionEntity findExpositionEntityByNom(String nom);
    List<ExpositionEntity> findAll();
    void removeById(Integer id);
    ExpositionEntity save(ExpositionEntity expositionEntity);
}
