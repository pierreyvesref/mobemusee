package fr.miage.orleans.isi.projet.museews.services.impl;

import fr.miage.orleans.isi.projet.museews.dao.DeplacementDAO;
import fr.miage.orleans.isi.projet.museews.dto.DeplacementDTO;
import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.entities.DeplacementEntity;
import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import fr.miage.orleans.isi.projet.museews.entities.SallesEntity;
import fr.miage.orleans.isi.projet.museews.services.IDeplacementService;
import objects.musee.Salle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class DeplacementService implements IDeplacementService {

    @Autowired
    DeplacementDAO deplacementDAO;

    @Override
    public DeplacementDTO AjouterDeplacement(SallesEntity sallesEntity, OeuvreEntity oeuvreEntity) {
        DeplacementEntity deplacementEntity = new DeplacementEntity();
        deplacementEntity.setOeuvre(oeuvreEntity);
        deplacementEntity.setSalle(sallesEntity);
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        deplacementEntity.setDateDeplacement(date);
        deplacementDAO.save(deplacementEntity);
        return DeplacementDTO.createFromEntity(deplacementEntity);
    }

    @Override
    public DeplacementDTO getLastDeplacementByIdOeuvre(Integer id) {
        List<DeplacementEntity> deplacementEntities = deplacementDAO.findAllByOeuvre_Id(id);
        DeplacementEntity deplacementEntity = deplacementEntities.get(deplacementEntities.size()-1);
       return DeplacementDTO.createFromEntity(deplacementEntity);
    }
}
