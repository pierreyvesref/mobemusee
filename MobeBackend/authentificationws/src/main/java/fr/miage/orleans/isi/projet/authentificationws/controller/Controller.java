package fr.miage.orleans.isi.projet.authentificationws.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.orleans.isi.projet.authentificationws.dto.MoyenPaiementDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.RoleDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.TransactionDTO;
import fr.miage.orleans.isi.projet.authentificationws.dto.UserDTO;
import fr.miage.orleans.isi.projet.authentificationws.exceptions.*;
import fr.miage.orleans.isi.projet.authentificationws.security.JwtTokens;
import fr.miage.orleans.isi.projet.authentificationws.services.impl.FacadeAuthentification;
import objects.paiement.BilletMusee;
import objects.paiement.MoyenPaiement;
import objects.paiement.TypeCarte;
import objects.paiement.TypeTarif;
import objects.users.Role;
import objects.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.management.relation.RoleNotFoundException;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static factory.ObjectsFactory.*;


@RestController
@RequestMapping(value = "/authentification")
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    JwtTokens jwtTokens;

    @Autowired
    FacadeAuthentification facadeAuthentification;
    
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> connexionUser(@RequestBody String loginMdp) throws JsonProcessingException {
        String login;
        String mdp;
        try {
            JsonNode jsonNode = objectMapper.readValue(loginMdp, JsonNode.class);
            JsonNode loginNode = jsonNode.get("login");
            JsonNode mdpNode = jsonNode.get("mdp");
            login = loginNode.asText();
            mdp= mdpNode.asText();
            facadeAuthentification.connexionUser(login, mdp);
        } catch (LoginInexistantException | MdpIncorrectException e) {
            return ResponseEntity.badRequest().body("Informations Incorrectes");
        } catch (LoginDejaConnecteException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login déjà connecté !");
        }
        UserDTO user = facadeAuthentification.getUserByLogin(login);
        LOGGER.info("User connecté : " + user);
        //URI CREATION
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{login}")
                .buildAndExpand(login).toUri();

        return ResponseEntity
                .created(location)
                .header(HttpHeaders.AUTHORIZATION, jwtTokens.generateToken(user.getLogin(), user.getRoles()))
                .body("Utilisateur connecté avec succés");
    }

    @DeleteMapping(value = "/user/{login}")
    public ResponseEntity<String> deconnexionUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                  @PathVariable("login") String login) {
        try {
            if(login.equals(jwtTokens.decodeToken(token))) {
                try {
                    facadeAuthentification.deconnexionUser(login);
                    LOGGER.info("User deconnecté : " + facadeAuthentification.getUserByLogin(login));
                } catch (LoginInexistantException e) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Informations Incorrects");
                } catch (LoginNonConnecteException e) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User non connecté !");
                }

                return ResponseEntity.ok().body("User Deconnecté");
            }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (TokenIncorrectException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping(value = "/user/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> obtenirUserByLogin(@PathVariable("login") String login, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        //On vérifie si les infos demandées concernent bien l'utilisatuer connecté
        try {
            if(login.equals(jwtTokens.decodeToken(token))){
                if(facadeAuthentification.getUserByLogin(login) != null){
                    return ResponseEntity.ok().body(facadeAuthentification.getUserByLogin(login));
                } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (TokenIncorrectException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(value = "/user/{login}/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> obtenirRolesByLogin(@PathVariable("login") String login){
        if(facadeAuthentification.getUserByLogin(login) != null){
            return ResponseEntity.ok().body(facadeAuthentification.getRoleByLogin(login));
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/checkToken")
    public ResponseEntity<String> checkToken(@RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
        try {
            String pseudo = jwtTokens.decodeToken(token);
            return ResponseEntity.ok(pseudo);
        } catch (TokenIncorrectException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping(value = "/userCreation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> creerUser(@RequestBody UserDTO user){
        UserDTO created;
        try {
            created = facadeAuthentification.creerUser(user.getLogin(), user.getMdp(), user.getRoles(), user.getNom(), user.getPrenom(), user.getMail());
        } catch (LoginDejaUtiliseException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login déjà utilisé !");
        } catch (LoginOuMdpNonConformeException e) {
            return ResponseEntity.badRequest().body("Login non conforme : Au moins 2 caractères");
        } catch (RoleNotFoundException e) {
            return ResponseEntity.badRequest().body("Role non conforme ou inexistant");
        }
        LOGGER.info("Le client est créé : " + user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getLogin()).toUri();
        return ResponseEntity.created(location).body("Utilisateur créé");
    }



    //// TODO: 20/03/2020 methode test donc pour des question de securité : à supprimer en production
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOS = facadeAuthentification.getAllUsers().stream()
                .map(UserDTO::createUserDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(userDTOS);
    }


}

