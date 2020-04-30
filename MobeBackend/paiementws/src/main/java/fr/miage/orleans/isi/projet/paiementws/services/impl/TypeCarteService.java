package fr.miage.orleans.isi.projet.paiementws.services.impl;

import fr.miage.orleans.isi.projet.paiementws.dao.TypeCarteDAO;
import fr.miage.orleans.isi.projet.paiementws.entities.TypeCarteEntity;
import fr.miage.orleans.isi.projet.paiementws.services.ITypeCarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TypeCarteService implements ITypeCarteService {

    @Autowired
    TypeCarteDAO typeCarteDAO;

    @Override
    public void create(String autre) {
        TypeCarteEntity typeCarteEntity = new TypeCarteEntity();
        typeCarteEntity.setValue(autre);
        typeCarteDAO.save(typeCarteEntity);
    }

    @Override
    public TypeCarteEntity getByValue(String value) {
        return typeCarteDAO.findByValue(value);
    }
}
