package fr.miage.orleans.isi.projet.paiementws.services.impl;

import fr.miage.orleans.isi.projet.paiementws.dao.TypeTarifDAO;
import fr.miage.orleans.isi.projet.paiementws.entities.TypeTarifEntity;
import fr.miage.orleans.isi.projet.paiementws.services.ITypeTarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TypeTarifService implements ITypeTarifService {

    @Autowired
    TypeTarifDAO typeTarifDAO;

    @Override
    public void create(String value, int i) {
        TypeTarifEntity typeTarifEntity = new TypeTarifEntity();
        typeTarifEntity.setValue(value);
        typeTarifEntity.setMontantTarif(i);
        typeTarifDAO.save(typeTarifEntity);
    }

    @Override
    public TypeTarifEntity getTypeTarif(String value) {
        return typeTarifDAO.findTypeTarifEntityByValue(value);
    }
}
