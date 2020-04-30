package fr.miage.orleans.isi.projet.museews.dao;


import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import objects.musee.Oeuvre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface OeuvreDAO extends CrudRepository<OeuvreEntity, Integer> {
    OeuvreEntity findOeuvreEntityById(Integer id);

    OeuvreEntity findOeuvreEntityByNomOeuvre(String name);
    List<OeuvreEntity> findAll();
    OeuvreEntity save(OeuvreEntity oeuvreEntity);
    void removeById(Integer id);
    OeuvreEntity findOeuvreEntitiesByNomOeuvre(String nom);
}
