package fr.miage.orleans.isi.projet.museews.services;

import fr.miage.orleans.isi.projet.museews.dto.ExpositionDTO;
import fr.miage.orleans.isi.projet.museews.entities.ExpositionEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface IExpositionService {
    ExpositionDTO getExpositionById(Integer id);
    ExpositionDTO getExpositionByNom(String nom);
    ExpositionDTO createExposition(String nom, Date dateDebut, Date dateFin);
    List<ExpositionDTO> getAllExpositions();
    void delete(int id);
    ExpositionEntity getExpositionEntityByNom(String nom);

    ExpositionEntity getExpositionEntityById(int expositionId);
}
