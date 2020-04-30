package fr.miage.orleans.isi.projet.museews.controller;

import fr.miage.orleans.isi.projet.museews.dto.DeplacementDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.orleans.isi.projet.museews.dto.ExpositionDTO;
import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import fr.miage.orleans.isi.projet.museews.entities.SallesEntity;
import fr.miage.orleans.isi.projet.museews.exceptions.*;
import fr.miage.orleans.isi.projet.museews.services.impl.FacadeMusee;
import fr.miage.orleans.isi.projet.museews.services.impl.ExpositionService;
import fr.miage.orleans.isi.projet.museews.services.impl.OeuvresService;
import fr.miage.orleans.isi.projet.museews.services.impl.SalleService;
import objects.musee.Exposition;
import objects.musee.Oeuvre;
import objects.musee.Salle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/musee")
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    FacadeMusee facadeMusee;
    @Autowired
    SalleService salleService;
    @Autowired
    OeuvresService oeuvresService;
    @Autowired
    ExpositionService expositionService;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok().body("Hello from museews !");
    }

    /* * * * * * * * * * */
    /*     OEUVRES       */
    /* * * * * * * * * * */

    /**
     * Créer une oeuvre
     * @param oeuvre
     * @return
     */
    @PostMapping(value = "/oeuvre",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOeuvre(@RequestBody OeuvreDTO oeuvre){
        try {
            OeuvreDTO oeuvreNew = facadeMusee.createOeuvre(oeuvre.getNomOeuvre(), oeuvre.getDateAchat(), oeuvre.getPrixAchat());
            LOGGER.info("Oeuvre crée : " + oeuvre);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(oeuvreNew).toUri();
            return ResponseEntity.created(location).body("Oeuvre créée");
        } catch (OeuvreDejaPresenteException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Oeuvre déjà présente !");
        }
    }

    /**
     * Récupérer la liste des oeuvres
     * @return
     */
    @GetMapping(value = "/oeuvre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OeuvreDTO>> getAllOeuvres() {
            List <OeuvreDTO> oeuvreDTOS = facadeMusee.getAllOeuvres();
            return ResponseEntity.ok().body(oeuvreDTOS);
    }

    /**
     * Récupérer une oeuvre par son identifiant
     * @param oeuvreId
     * @return
     */
    @GetMapping(value = "/oeuvre/{oeuvreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OeuvreDTO> getOeuvreById(@PathVariable int oeuvreId) {

        try {
            return ResponseEntity.ok(facadeMusee.getOeuvreById(oeuvreId));
        } catch (OeuvreNonExistanteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Récupérer une oeuvre par son nom
     * @param nomOeuvre
     * @return
     */
    @GetMapping(value = "/oeuvre/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OeuvreDTO> getOeuvreByNom(@RequestBody String nomOeuvre) {

        String nom;
        try {
            JsonNode jsonNode = objectMapper.readValue(nomOeuvre, JsonNode.class);
            JsonNode nomNode = jsonNode.get("nomOeuvre");
            nom = nomNode.asText();

            return ResponseEntity.ok(facadeMusee.getOeuvreByNom(nom));
        } catch (OeuvreNonExistanteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (JsonMappingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * Modifier une oeuvre par son identifiant
     * @param oeuvreId
     * @return
     */
    @PatchMapping(value = "/oeuvre/{oeuvreId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OeuvreDTO> modifierOeuvreById(@PathVariable Integer oeuvreId, @RequestBody OeuvreDTO oeuvreDTO) {
        try {
            return ResponseEntity.ok(facadeMusee.modifierOeuvreById(oeuvreId, oeuvreDTO));
        } catch (OeuvreNonExistanteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * Supprimer une oeuvre par son identifiant
     * @param oeuvreId
     * @return
     */
    @DeleteMapping(value = "/oeuvre/{oeuvreId}")
    public ResponseEntity<String> deleteOeuvre(@PathVariable int oeuvreId) {
        try {
            return ResponseEntity.ok().body(facadeMusee.deleteOeuvre(oeuvreId));
        } catch (OeuvreNonExistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Déplacer une oeuvre dans une autre salle par son identifiant
     * @param oeuvreId
     * @param salle
     * @return
     */
    @PostMapping(value = "/oeuvre/{oeuvreId}/deplacement", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeplacementDTO> moveOeuvre(@PathVariable int oeuvreId, @RequestBody SalleDTO salle) {
        DeplacementDTO deplacementDTO = null;
        try {
            deplacementDTO = facadeMusee.moveOeuvre(oeuvreId, salle);
        } catch (OeuvreNonExistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SalleInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DeplacementImpossibleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOGGER.info("Oeuvre déplacée : " + oeuvreId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(deplacementDTO).toUri();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/oeuvre/{oeuvreId}/deplacement", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeplacementDTO> moveOeuvre(@PathVariable int oeuvreId) {
        DeplacementDTO deplacementDTO = null;
        try {
            deplacementDTO = facadeMusee.getLastDeplacementOeuvre(oeuvreId);
        } catch (OeuvreNonExistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOGGER.info("Oeuvre déplacée : " + oeuvreId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(deplacementDTO).toUri();
        return ResponseEntity.ok().build();
    }

    /**
     * Mettre à jour le prix d'une oeuvre
     * @param id
     * @param prixOeuvre
     * @return
     */
    @PatchMapping(value = "/oeuvre/prix/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setPrixOeuvre(@PathVariable int id, @RequestBody String prixOeuvre) {

        double nouveauPrix;
        try {
            JsonNode jsonNode = objectMapper.readValue(prixOeuvre, JsonNode.class);
            JsonNode prixNode = jsonNode.get("prixOeuvre");
            nouveauPrix = Double.parseDouble(prixNode.asText());

            facadeMusee.setPrixOeuvre(id, nouveauPrix);
            return ResponseEntity.ok().body("Prix de l oeuvre numéro " + id + " bien mis à jour");
        } catch (OeuvreNonExistanteException e) {
            return ResponseEntity.ok("Oeuvre inexistante");
        } catch (JsonMappingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /* * * * * * * * * * */
    /*      SALLES       */
    /* * * * * * * * * * */

    /**
     * Récupérer la liste des salles
     * @return
     */
    @GetMapping(value = "/salle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalleDTO>> getSalles() {
        List<SalleDTO> salles = facadeMusee.getSalles();
        return ResponseEntity.ok().body(salles);
    }

    /**
     * Récupérer une salle par son id
     * @param id
     * @return
     */
    @GetMapping(value ="/salle/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalleDTO> getSalleById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(facadeMusee.getSalleById(id));
        } catch (SalleInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Créer une salle
     * @param salleDTO
     * @return
     */
    @PostMapping(value = "/salle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createSalle(@RequestBody SalleDTO salleDTO){
        try{
            SalleDTO sallesDTO = facadeMusee.createSalle(salleDTO.getValue());
            LOGGER.info("Salle crée : " + sallesDTO);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(sallesDTO).toUri();
            return ResponseEntity.created(location).body("Salle crée");
        }catch(SalleDejaExistanteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    /**
     * Supprime une salle par son id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/salle/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSalleById(@PathVariable int id) {
        try{
            return ResponseEntity.ok().body(facadeMusee.deleteSalle(id));
        }catch (SalleInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salle non trouvée");
        }
    }

    /**
     * Ajoute une oeuvre à une salle
     * @param id
     * @param oeuvre
     * @return
     */
    @PostMapping(value="/salle/{id}/{oeuvre}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> ajouterOeuvreSalle(@PathVariable int id,@PathVariable int oeuvre ){
        try{
            facadeMusee.ajouterOeuvreSalle(id, oeuvre);
        }catch(SalleInexistanteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch(OeuvreNonExistanteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (OeuvreDejaPresenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body("Oeuvre bien ajoutée");
    }

    /**
     * Supprimer une oeuvre d'une salle
     * @param id
     * @param oeuvre
     * @return
     */
    @DeleteMapping(value="/salle/{id}/{oeuvre}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> supprimerOeuvreSalle(@PathVariable int id,@PathVariable int oeuvre ){
        try{
            facadeMusee.supprimerOeuvreSalle(id, oeuvre);
        }catch(SalleInexistanteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch(OeuvreNonExistanteException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body("Oeuvre bien supprimée");
    }

    /**
     * Récupérer les oeuvres d'une salle
     * @param id
     * @return
     */
    @GetMapping(value ="/salle/oeuvre/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OeuvreDTO>> getOeuvreBySalle(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(facadeMusee.getOeuvresSalle(id));
        } catch (SalleInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /* * * * * * * * * * */
    /*    EXPOSITIONS    */
    /* * * * * * * * * * */

    /**
     * Créer une exposition
     * @param exposition
     * @return
     */
    @PostMapping(value = "/exposition", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createExposition(@RequestBody ExpositionDTO exposition) {

        try {
            ExpositionDTO expositionDTO = facadeMusee.createExposition(exposition.getNom(), exposition.getDateDebutExpo(), exposition.getDateFinExpo());
            LOGGER.info("Expo crée : " + expositionDTO);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(expositionDTO).toUri();
            return ResponseEntity.created(location).body("Exposition crée");
        } catch (ExpositionDejaExistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Exposition déjà existante !");
        }

    }

    /**
     * Récupérer la liste des expositions
     * @return
     */
    @GetMapping(value = "/exposition", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExpositionDTO>> getAllExpositions() {
            List<ExpositionDTO> expositionDTOS = facadeMusee.getAllExpositions();
            return ResponseEntity.ok().body(expositionDTOS);
    }

    /**
     * Récupérer une exposition par son id
     * @param id
     * @return
     */
    @GetMapping(value = "/exposition/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpositionDTO> getExpositionsById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(facadeMusee.getExpositionById(id));
        } catch (ExpositionNonExistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Supprimer une exposition
     * @param id
     * @return
     */
    @DeleteMapping(value = "/exposition/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteExposition(@PathVariable int id) {

        try {
            return ResponseEntity.ok().body(facadeMusee.deleteExposition(id));
        } catch (ExpositionNonExistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exposition Inexistante");
        }
    }

}
