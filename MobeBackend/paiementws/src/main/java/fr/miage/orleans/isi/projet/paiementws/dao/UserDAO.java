package fr.miage.orleans.isi.projet.paiementws.dao;

import fr.miage.orleans.isi.projet.paiementws.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends CrudRepository<UserEntity, Integer> {
    UserEntity findUserEntityById(Integer id);
    List<UserEntity> findAll();
    void removeById(Integer id);
    UserEntity save(UserEntity userEntity);
    UserEntity findUserEntityByLogin(String login);
}
