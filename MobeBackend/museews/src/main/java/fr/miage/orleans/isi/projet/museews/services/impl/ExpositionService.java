package fr.miage.orleans.isi.projet.museews.services.impl;

import fr.miage.orleans.isi.projet.museews.dao.ExpositionDAO;
import fr.miage.orleans.isi.projet.museews.dto.ExpositionDTO;
import fr.miage.orleans.isi.projet.museews.entities.ExpositionEntity;
import fr.miage.orleans.isi.projet.museews.services.IExpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ExpositionService implements IExpositionService {
    @Autowired
    private ExpositionDAO expositionDAO;

    @Override
    public ExpositionDTO getExpositionById(Integer id) {
            ExpositionEntity expositionEntity= expositionDAO.findExpositionEntityById(id);
            ExpositionDTO expositionDTO= new ExpositionDTO();
            expositionDTO.setId(expositionEntity.getId());
            expositionDTO.setNom(expositionEntity.getNom());
            expositionDTO.setDateDebutExpo(expositionEntity.getDateDebutExpo());
            expositionDTO.setDateFinExpo(expositionEntity.getDateFinExpo());
            return expositionDTO;
    }

    public ExpositionDTO getExpositionByNom(String nom) {
            ExpositionEntity expositionEntity= expositionDAO.findExpositionEntityByNom(nom);
            ExpositionDTO expositionDTO= new ExpositionDTO();
            expositionDTO.setId(expositionEntity.getId());
            expositionDTO.setNom(expositionEntity.getNom());
            expositionDTO.setDateDebutExpo(expositionEntity.getDateDebutExpo());
            expositionDTO.setDateFinExpo(expositionEntity.getDateFinExpo());
            return expositionDTO;
    }

    @Override
    public ExpositionDTO createExposition(String nom, Date dateDebut, Date dateFin) {
        ExpositionEntity expositionEntity = new ExpositionEntity();
        expositionEntity.setNom(nom);
        expositionEntity.setDateDebutExpo(dateDebut);
        expositionEntity.setDateFinExpo(dateFin);
        expositionDAO.save(expositionEntity);
        ExpositionDTO expositionDTO= new ExpositionDTO();
        expositionDTO.setId(expositionEntity.getId());
        expositionDTO.setNom(expositionEntity.getNom());
        expositionDTO.setDateDebutExpo(expositionEntity.getDateDebutExpo());
        expositionDTO.setDateFinExpo(expositionEntity.getDateFinExpo());
        return expositionDTO;
    }

    @Override
    public List<ExpositionDTO> getAllExpositions() {
        List<ExpositionEntity> expositionEntities = expositionDAO.findAll();
        List<ExpositionDTO> expositionDTOS = new ArrayList<>();
        for (ExpositionEntity expositionEntity : expositionEntities){
            ExpositionDTO expositionDTO= new ExpositionDTO();
            expositionDTO.setId(expositionEntity.getId());
            expositionDTO.setNom(expositionEntity.getNom());
            expositionDTO.setDateDebutExpo(expositionEntity.getDateDebutExpo());
            expositionDTO.setDateFinExpo(expositionEntity.getDateFinExpo());
            expositionDTOS.add(expositionDTO);
        }
        return expositionDTOS;
    }

    @Override
    public void delete(int id) {
        expositionDAO.removeById(id);
    }

    @Override
    public ExpositionEntity getExpositionEntityByNom(String nom) {
        return expositionDAO.findExpositionEntityByNom(nom);
    }

    @Override
    public ExpositionEntity getExpositionEntityById(int expositionId) {
        return expositionDAO.findExpositionEntityById(expositionId);
    }
}
