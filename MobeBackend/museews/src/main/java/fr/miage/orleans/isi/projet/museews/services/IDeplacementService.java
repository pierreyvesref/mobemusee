package fr.miage.orleans.isi.projet.museews.services;

import fr.miage.orleans.isi.projet.museews.dto.DeplacementDTO;
import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import fr.miage.orleans.isi.projet.museews.entities.SallesEntity;

import java.util.Date;

public interface IDeplacementService {
    DeplacementDTO AjouterDeplacement(SallesEntity sallesEntity, OeuvreEntity oeuvreEntity);
    DeplacementDTO getLastDeplacementByIdOeuvre(Integer id);
}
