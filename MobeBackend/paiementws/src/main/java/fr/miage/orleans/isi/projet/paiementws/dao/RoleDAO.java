package fr.miage.orleans.isi.projet.paiementws.dao;


import fr.miage.orleans.isi.projet.paiementws.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends CrudRepository<RoleEntity, Integer> {
    RoleEntity findRoleEntityByValue(String value);
    RoleEntity save(RoleEntity roleEntity);
}
