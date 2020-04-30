package fr.miage.orleans.isi.projet.museews.dao;

import fr.miage.orleans.isi.projet.museews.entities.SallesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalleDAO extends CrudRepository<SallesEntity, Integer> {
    SallesEntity findSallesEntityById(Integer id);
    List<SallesEntity> findAll();
    SallesEntity save(SallesEntity sallesEntity);
    SallesEntity findSallesEntityByValue(String value);
    void removeById(int id);
}
