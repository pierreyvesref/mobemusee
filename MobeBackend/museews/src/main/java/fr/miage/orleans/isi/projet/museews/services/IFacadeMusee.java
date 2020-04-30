package fr.miage.orleans.isi.projet.museews.services;


import fr.miage.orleans.isi.projet.museews.dto.DeplacementDTO;
import fr.miage.orleans.isi.projet.museews.dto.ExpositionDTO;
import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import fr.miage.orleans.isi.projet.museews.exceptions.*;

import java.util.Date;
import java.util.List;

public interface IFacadeMusee {
    public OeuvreDTO createOeuvre(String nom, Date dateAchat, double prixAchat) throws OeuvreDejaPresenteException;
    public List<OeuvreDTO> getAllOeuvres();
    public OeuvreDTO getOeuvreById(int oeuvreId) throws OeuvreNonExistanteException;
    public String deleteOeuvre(int oeuvreId) throws OeuvreNonExistanteException;
    public OeuvreDTO modifierOeuvreById(Integer oeuvreId, OeuvreDTO oeuvreDTO) throws OeuvreNonExistanteException;
    public DeplacementDTO moveOeuvre(int oeuvreId, SalleDTO salle) throws OeuvreNonExistanteException, SalleInexistanteException, DeplacementImpossibleException;
    void setPrixOeuvre(int id, double prix) throws OeuvreNonExistanteException;
    public OeuvreDTO getOeuvreByNom(String nomOeuvre) throws OeuvreNonExistanteException;

    public List<SalleDTO> getSalles();
    SalleDTO getSalleById(int id) throws SalleInexistanteException;
    String deleteSalle(int id) throws SalleInexistanteException;
    SalleDTO createSalle(String value) throws SalleDejaExistanteException;
    void ajouterOeuvreSalle(int id, int oeuvre) throws SalleInexistanteException, OeuvreNonExistanteException, OeuvreDejaPresenteException;
    void supprimerOeuvreSalle(int id, int oeuvre) throws SalleInexistanteException, OeuvreNonExistanteException;
    public List<OeuvreDTO> getOeuvresSalle(int idSalle) throws SalleInexistanteException;

    public ExpositionDTO createExposition(String nom, Date dateDebut, Date dateFin) throws ExpositionDejaExistanteException;
    public List<ExpositionDTO> getAllExpositions();
    public ExpositionDTO getExpositionById(int expositionId) throws ExpositionNonExistanteException;
    public String deleteExposition(int expositionId) throws ExpositionNonExistanteException;


    DeplacementDTO getLastDeplacementOeuvre(int oeuvreId) throws OeuvreNonExistanteException;

    void supprimerOeuvreTouteSalle(int oeuvreId) throws OeuvreNonExistanteException;
}
