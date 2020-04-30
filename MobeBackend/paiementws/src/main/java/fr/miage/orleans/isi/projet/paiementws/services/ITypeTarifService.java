package fr.miage.orleans.isi.projet.paiementws.services;

import fr.miage.orleans.isi.projet.paiementws.entities.TypeTarifEntity;

public interface ITypeTarifService {
    void create(String value, int i);

    TypeTarifEntity getTypeTarif(String value);
}
