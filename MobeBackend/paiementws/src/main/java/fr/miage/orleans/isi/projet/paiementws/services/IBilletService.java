package fr.miage.orleans.isi.projet.paiementws.services;

import fr.miage.orleans.isi.projet.paiementws.entities.BilletMuseeEntity;

import java.util.List;

public interface IBilletService {
    List<BilletMuseeEntity> getAll();

    void setInvalideValide(int id);

    void save(BilletMuseeEntity billet);

    void createBilletVierge();

    BilletMuseeEntity getBilletVierge();
}
