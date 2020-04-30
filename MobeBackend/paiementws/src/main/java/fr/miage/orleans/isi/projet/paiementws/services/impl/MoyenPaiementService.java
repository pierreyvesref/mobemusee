package fr.miage.orleans.isi.projet.paiementws.services.impl;

import fr.miage.orleans.isi.projet.paiementws.dao.MoyenPaiementDAO;
import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TypeCarteEntity;
import fr.miage.orleans.isi.projet.paiementws.services.IMoyenPaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class MoyenPaiementService implements IMoyenPaiementService {

    @Autowired
    MoyenPaiementDAO moyenPaiementDAO;
    @Override
    public MoyenPaiementEntity creerMP(TypeCarteEntity value, long numCompte, Date dateExpiration, int cryptogramme, String titulaire) {
        MoyenPaiementEntity moyenPaiementEntity = new MoyenPaiementEntity();
        moyenPaiementEntity.setTypeCarte(value);
        moyenPaiementEntity.setNumCompte(numCompte);
        moyenPaiementEntity.setDateExpiration(dateExpiration);
        moyenPaiementEntity.setCryptogramme(cryptogramme);
        moyenPaiementEntity.setTitulaire(titulaire);
        moyenPaiementDAO.save(moyenPaiementEntity);
        return moyenPaiementEntity;
    }

    @Override
    public MoyenPaiementEntity getById(int idMoyenPaiement) {
        return moyenPaiementDAO.findByIdMoyenpaiement(idMoyenPaiement);
    }

    @Override
    public void supprimerTP(MoyenPaiementEntity mp) {
        moyenPaiementDAO.delete(mp);
    }
}
