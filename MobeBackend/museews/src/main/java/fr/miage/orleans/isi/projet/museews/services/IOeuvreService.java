package fr.miage.orleans.isi.projet.museews.services;

import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface IOeuvreService {
    public OeuvreEntity getOeuvreEntityById(Integer id);
    public OeuvreDTO getOeuvreById(Integer id);
    void deleteOeuvre(Integer id);
    List<OeuvreDTO> getAllOeuvres();
    OeuvreDTO createOeuvre(String nom, Date date, double prix);
    void MoveOeuvre(int idOeuvre, SalleDTO salleDTO);
    void setPrixOeuvre(int id, double prix);
    OeuvreDTO getOeuvreByNom(String nom);
    OeuvreEntity getOeuvreEntityByNom(String nom);

    OeuvreDTO modifierOeuvreById(Integer oeuvreId, OeuvreDTO oeuvreDTO);
}
