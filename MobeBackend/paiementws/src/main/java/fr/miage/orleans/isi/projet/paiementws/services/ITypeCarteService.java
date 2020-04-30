package fr.miage.orleans.isi.projet.paiementws.services;

import fr.miage.orleans.isi.projet.paiementws.entities.TypeCarteEntity;

public interface ITypeCarteService {
    void create(String autre);
    TypeCarteEntity getByValue(String value);
}
