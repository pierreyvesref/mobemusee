package fr.miage.orleans.isi.projet.museews.services;


import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import fr.miage.orleans.isi.projet.museews.entities.SallesEntity;
import fr.miage.orleans.isi.projet.museews.exceptions.OeuvreNonExistanteException;
import fr.miage.orleans.isi.projet.museews.exceptions.SalleDejaExistanteException;
import fr.miage.orleans.isi.projet.museews.exceptions.SalleInexistanteException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISalleService {

    SallesEntity getSallebyId(Integer id);
    public SalleDTO getSalleById(Integer id);
    List<SalleDTO> getAllSalles();
    List<OeuvreDTO>getOeuvresSalle(int id);
    SalleDTO changeNameSalle(int id, String name);
    public SalleDTO createSalle(String value) throws SalleDejaExistanteException;
    public SalleDTO ajouterOeuvreSalle(int id0, OeuvreEntity oeuvre) throws SalleInexistanteException;
    SalleDTO getSalleByValue(String value);
    void delete(int id);
    OeuvreEntity getOeuvreSalleById(int id, int oeuvre);
    void removeOeuvreSalle(int id, int oeuvre);

    SallesEntity getSalleEntityByValue(String value);

    void supprimerOeuvreTouteSalle(int id);
}
