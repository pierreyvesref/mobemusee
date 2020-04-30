package fr.miage.orleans.isi.projet.paiementws.services.impl;

import fr.miage.orleans.isi.projet.paiementws.dao.BilletDAO;
import fr.miage.orleans.isi.projet.paiementws.entities.BilletMuseeEntity;
import fr.miage.orleans.isi.projet.paiementws.services.IBilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BilletService implements IBilletService {
    @Autowired
    BilletDAO billetDAO;

    @Override
    public List<BilletMuseeEntity> getAll() {
       return billetDAO.findAll();
    }

    @Override
    public void setInvalideValide(int id) {
        BilletMuseeEntity billetMuseeEntity = billetDAO.findBilletMuseeEntityById(id);
        billetMuseeEntity.setValide(false);
        billetDAO.save(billetMuseeEntity);
    }

    @Override
    public void save(BilletMuseeEntity billet) {
        billetDAO.save(billet);
    }

    @Override
    public void createBilletVierge() {
        BilletMuseeEntity billetMuseeEntity = new BilletMuseeEntity();
        billetDAO.save(billetMuseeEntity);
    }

    @Override
    public BilletMuseeEntity getBilletVierge() {
        return billetDAO.findFirstByOrderByIdDesc();
    }
}
