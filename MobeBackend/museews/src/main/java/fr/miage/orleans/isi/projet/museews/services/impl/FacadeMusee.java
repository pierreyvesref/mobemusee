package fr.miage.orleans.isi.projet.museews.services.impl;

import fr.miage.orleans.isi.projet.museews.dto.DeplacementDTO;
import fr.miage.orleans.isi.projet.museews.dto.ExpositionDTO;
import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import fr.miage.orleans.isi.projet.museews.entities.ExpositionEntity;
import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import fr.miage.orleans.isi.projet.museews.entities.SallesEntity;
import fr.miage.orleans.isi.projet.museews.exceptions.*;
import fr.miage.orleans.isi.projet.museews.services.IFacadeMusee;
import objects.musee.Oeuvre;
import objects.musee.Salle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class FacadeMusee implements IFacadeMusee {
    @Autowired
    OeuvresService oeuvresService;
    @Autowired
    SalleService salleService;
    @Autowired
    ExpositionService expositionService;
    @Autowired
    DeplacementService deplacementService;

    //A LANCER SI BASE VIDE
    /*@PostConstruct
    public void init() {
        salleService.createSalle("Reserve");
        salleService.createSalle("Grande Salle");
    }*/

    /* * * * * * * * * * */
    /*     OEUVRES       */
    /* * * * * * * * * * */

    /**
     * Creation d'une oeuvre
     * @param nom
     * @param dateAchat
     * @param prixAchat
     * @return
     * @throws OeuvreDejaPresenteException
     */
    public OeuvreDTO createOeuvre(String nom, Date dateAchat, double prixAchat) throws OeuvreDejaPresenteException {
        OeuvreDTO oeuvreDTO = null;
        OeuvreEntity oeuvreEntity = oeuvresService.getOeuvreEntityByNom(nom);
        if(oeuvreEntity!=null) {
            throw new OeuvreDejaPresenteException();
        } else {
            oeuvreDTO= oeuvresService.createOeuvre(nom, dateAchat, prixAchat);
            salleService.ajouterOeuvreSalle(salleService.getSalleByValue("Reserve").getId(), oeuvresService.getOeuvreEntityById(oeuvreDTO.getId()));
            deplacementService.AjouterDeplacement(salleService.getSalleEntityByValue("Reserve"), oeuvresService.getOeuvreEntityByNom(nom));
            return oeuvreDTO;
        }
    }

    /**
     * Récupération de toute les oeuvres
     * @return
     */
    public List<OeuvreDTO> getAllOeuvres() {
        return oeuvresService.getAllOeuvres();
    }

    /**
     * Recup d'une oeuvre avec l'id
     * @param oeuvreId
     * @return
     * @throws OeuvreNonExistanteException
     */
    public OeuvreDTO getOeuvreById(int oeuvreId) throws OeuvreNonExistanteException {
        OeuvreEntity oeuvreEntity = oeuvresService.getOeuvreEntityById(oeuvreId);
        if(oeuvreEntity==null){
            throw new OeuvreNonExistanteException();
        }else {
            return oeuvresService.getOeuvreById(oeuvreId);
        }
    }

    public OeuvreDTO modifierOeuvreById(Integer oeuvreId, OeuvreDTO oeuvreDTO) throws OeuvreNonExistanteException {
        OeuvreEntity oeuvreEntity = oeuvresService.getOeuvreEntityById(oeuvreId);
        if(oeuvreEntity==null) {
            throw new OeuvreNonExistanteException();
        }else{
            return oeuvresService.modifierOeuvreById(oeuvreId, oeuvreDTO);
        }
    }

    /**
     * Suppression d'une oeuvre par son id
     * @param oeuvreId
     * @throws OeuvreNonExistanteException
     */
    public String deleteOeuvre(int oeuvreId) throws OeuvreNonExistanteException {
        OeuvreEntity oeuvreEntity = oeuvresService.getOeuvreEntityById(oeuvreId);
        if(oeuvreEntity==null){
            throw new OeuvreNonExistanteException();
        }else {
            oeuvresService.deleteOeuvre(oeuvreId);
            return "Oeuvre "+oeuvreId+" supprimée";
        }
    }

    /*
        Déplacement d'une oeuvre vers une nouvelle salle
        Création d'un objet Deplacement dans le modèle et modification des objets présents dans les salles (ancienne et nouvelle)
     */
    public DeplacementDTO moveOeuvre(int oeuvreId, SalleDTO salle) throws OeuvreNonExistanteException, SalleInexistanteException, DeplacementImpossibleException {
        OeuvreEntity oeuvreEntity = oeuvresService.getOeuvreEntityById(oeuvreId);
        DeplacementDTO deplacementDTO = null;
        if(oeuvreEntity==null) {
            throw new OeuvreNonExistanteException();
        }else{
            SalleDTO salleDTO = salleService.getSalleByValue(salle.getValue());
            if(salleDTO==null){
                throw new SalleInexistanteException();
            }else{
                deplacementDTO= deplacementService.AjouterDeplacement(salleService.getSallebyId(salleDTO.getId()), oeuvresService.getOeuvreEntityById(oeuvreId));
                try {
                    this.supprimerOeuvreTouteSalle(oeuvreId);
                    this.ajouterOeuvreSalle(salleDTO.getId(), oeuvreEntity.getId());
                } catch (OeuvreDejaPresenteException e) {
                    e.printStackTrace();
                }
            }
        }
        if(deplacementDTO!=null){
            return deplacementDTO;
        }else{
            throw new DeplacementImpossibleException();
        }
    }

    @Override
    public DeplacementDTO getLastDeplacementOeuvre(int oeuvreId) throws OeuvreNonExistanteException {
        OeuvreEntity oeuvreEntity = oeuvresService.getOeuvreEntityById(oeuvreId);
        DeplacementDTO deplacementDTO = null;
        if(oeuvreEntity==null) {
            throw new OeuvreNonExistanteException();
        }else{
            return deplacementService.getLastDeplacementByIdOeuvre(oeuvreId);
        }
    }

    @Override
    public void supprimerOeuvreTouteSalle(int oeuvreId) throws OeuvreNonExistanteException {
        OeuvreEntity oeuvreEntity = oeuvresService.getOeuvreEntityById(oeuvreId);
        DeplacementDTO deplacementDTO = null;
        if(oeuvreEntity==null) {
            throw new OeuvreNonExistanteException();
        }else{
            salleService.supprimerOeuvreTouteSalle(oeuvreEntity.getId());
        }
    }

    /**
     * Modifier le prix d'une oeuvre
     * @param id
     * @param prix
     * @throws OeuvreNonExistanteException
     */
    @Override
    public void setPrixOeuvre(int id, double prix) throws OeuvreNonExistanteException {
        OeuvreDTO oeuvreDTO = oeuvresService.getOeuvreById(id);
        if(oeuvreDTO==null) {
            throw new OeuvreNonExistanteException();
        } else {
            oeuvresService.setPrixOeuvre(id, prix);
        }
    }

    /**
     * Récupérer une oeuvre par son nom
     * @param nomOeuvre
     * @return
     * @throws OeuvreNonExistanteException
     */
    @Override
    public OeuvreDTO getOeuvreByNom(String nomOeuvre) throws OeuvreNonExistanteException {
        OeuvreDTO oeuvreDTO = oeuvresService.getOeuvreByNom(nomOeuvre);
        if (oeuvreDTO == null) {
            throw new OeuvreNonExistanteException();
        } else {
            return oeuvresService.getOeuvreByNom(nomOeuvre);
        }
    }


    /* * * * * * * * * * */
    /*      SALLES       */
    /* * * * * * * * * * */


    public List<SalleDTO> getSalles() {
        return salleService.getAllSalles();
    }

    @Override
    public SalleDTO getSalleById(int id) throws SalleInexistanteException {
        SallesEntity ss= salleService.getSallebyId(id);
        if(ss==null){
            throw new SalleInexistanteException();
        }else{
            return salleService.getSalleById(id);
        }
    }

    @Override
    public String deleteSalle(int id) throws SalleInexistanteException {
        SalleDTO salleDTO = salleService.getSalleById(id);
        if(salleDTO.equals(null)){
            throw new SalleInexistanteException();
        }else{
            salleService.delete(id);
            return "Salle " + id + "supprimée";
        }
    }

    @Override
    public SalleDTO createSalle(String value) throws SalleDejaExistanteException {
        SalleDTO salleDTO = salleService.getSalleByValue(value);
        if(salleDTO==null){
            return salleService.createSalle(value);
        }else{
            throw new SalleDejaExistanteException();
        }
    }

    @Override
    public List<OeuvreDTO> getOeuvresSalle(int idSalle) throws SalleInexistanteException {
        if(salleService.getOeuvresSalle(idSalle)==null) {
            throw new SalleInexistanteException();
        } else {
            return salleService.getOeuvresSalle(idSalle);
        }
    }

    @Override
    public void ajouterOeuvreSalle(int id, int oeuvre) throws OeuvreNonExistanteException, OeuvreDejaPresenteException, SalleInexistanteException {
        OeuvreEntity oeuvreEntity = oeuvresService.getOeuvreEntityById(oeuvre);
        if(oeuvreEntity!=null){
            SallesEntity sallesEntity = salleService.getSallebyId(id);
            if (sallesEntity!=null){
                oeuvreEntity = salleService.getOeuvreSalleById(id, oeuvre);
                if(oeuvreEntity!=null){
                    throw new OeuvreDejaPresenteException();
                }else{
                    salleService.ajouterOeuvreSalle(id, oeuvresService.getOeuvreEntityById(oeuvre));
                }
            }else{
                throw new SalleInexistanteException();
            }
        }else{
            throw new OeuvreNonExistanteException();
        }
    }

    @Override
    public void supprimerOeuvreSalle(int id, int oeuvre) throws SalleInexistanteException, OeuvreNonExistanteException {
        SallesEntity sallesEntity = salleService.getSallebyId(id);
        if(sallesEntity!= null){
            OeuvreEntity oeuvreEntity = salleService.getOeuvreSalleById(id, oeuvre);
            if(oeuvreEntity!=null){
                salleService.removeOeuvreSalle(id, oeuvre);
            }else{
                throw new OeuvreNonExistanteException();
            }
        }else{
            throw new SalleInexistanteException();
        }
    }


    /* * * * * * * * * * */
    /*    EXPOSITIONS    */
    /* * * * * * * * * * */

    public ExpositionDTO createExposition(String nom, Date dateDebut, Date dateFin) throws ExpositionDejaExistanteException {
        ExpositionEntity exposition = expositionService.getExpositionEntityByNom(nom) ;
        if(exposition==null) {
            return expositionService.createExposition(nom, dateDebut, dateFin);
        } else throw new ExpositionDejaExistanteException();
    }

    public List<ExpositionDTO> getAllExpositions() {
        return expositionService.getAllExpositions();
    }

    public ExpositionDTO getExpositionById(int expositionId) throws ExpositionNonExistanteException {
        ExpositionEntity exposition = expositionService.getExpositionEntityById(expositionId) ;
        if(exposition!=null) {
            return expositionService.getExpositionById(expositionId);
        } else throw new ExpositionNonExistanteException();
    }

    public String deleteExposition(int expositionId) throws ExpositionNonExistanteException {
        ExpositionDTO exposition = expositionService.getExpositionById(expositionId);
        if (exposition==null) {
            throw new ExpositionNonExistanteException();
        } else {
            expositionService.delete(expositionId);
            return "Exposition " + expositionId + " supprimée";
        }
    }

}
