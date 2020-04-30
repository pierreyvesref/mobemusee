package fr.miage.orleans.isi.projet.paiementws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.orleans.isi.projet.paiementws.DTO.MoyenPaiementDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.TransactionDTO;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import fr.miage.orleans.isi.projet.paiementws.exceptions.*;
import fr.miage.orleans.isi.projet.paiementws.services.impl.FacadePaiement;
import objects.paiement.MoyenPaiement;
import objects.paiement.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static factory.ObjectsFactory.*;


@RestController
@RequestMapping(value = "/paiement")
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    FacadePaiement facadePaiement;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok().body("Hello from paiementws !");
    }

    @PostMapping(value = "/user/{userId}/moyenPaiement", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> creerMoyenPaiement(@PathVariable("userId") String login, @RequestBody MoyenPaiementDTO moyenPaiement,
                                                     @RequestHeader("login") String userAuthenticated){
        // vérification que l'utilisateur qui crée le moyen de paiement est bien celui authentifié
        if (userAuthenticated.equals(login)) {
            try {
                MoyenPaiementDTO mpAjoute = facadePaiement.ajouterMoyenPaiement(login, moyenPaiement.getTypeCarte(), moyenPaiement.getNumCompte()
                        , moyenPaiement.getDateExpiration(), moyenPaiement.getCryptogramme(), moyenPaiement.getTitulaire());

                LOGGER.info("Moyen de Paiement ajouté : " + mpAjoute);

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{idMoyenPaiement}")
                        .buildAndExpand(mpAjoute.getIdMoyenpaiement()).toUri();
                return ResponseEntity.created(location).body("Moyen de paiement ajouté");

            } catch (UtilisateurInexistantException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login inexistant");
            } catch (MoyenPaiementDejaAjouteException e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Moyen de paiement déjà existant");
            }
        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value = "/user/{userId}/moyenPaiement", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MoyenPaiementDTO>> getMoyensPaiementByLogin(@PathVariable("userId") String login,
                                                                           @RequestHeader("login") String userAuthenticated){
        if (userAuthenticated.equals(login)) {
            try {
                List<MoyenPaiementDTO> moyenPaiementDTOS = facadePaiement.getMoyensPaiementByLogin(login);
                return ResponseEntity.ok().body(moyenPaiementDTOS);

            } catch(UtilisateurInexistantException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping(value = "/user/{userId}/moyenPaiement/{moyenPaiementId}")
    public ResponseEntity<String> deleteMoyenPaiementByLogin(@PathVariable("userId") String login,
                                                             @PathVariable("moyenPaiementId") int moyenPaiementId,
                                                             @RequestHeader("login") String userAuthenticated){
        if (userAuthenticated.equals(login)) {
            try {

                facadePaiement.supprimerMoyenPaiement(login, moyenPaiementId);

                LOGGER.info("Moyen de paiement supprimé pour " + login);

                return ResponseEntity.ok().body("Moyen de paiement supprimé pour " + login);

            } catch (UtilisateurInexistantException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login inexistant");
            } catch (MoyenPaiementInexistantException e) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Moyen de paiement inexistant");
            }
        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping(value = "/user/{userId}/moyenPaiement/{moyenPaiementId}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> creerTransaction(@PathVariable("userId") String login,
                                                   @PathVariable("moyenPaiementId") int idMoyenPaiement,
                                                   @RequestBody TransactionDTO transaction,
                                                   @RequestHeader("login") String userAuthenticated){

        if (userAuthenticated.equals(login)) {
            try {

                TransactionEntity tAjoutee = facadePaiement.effectuerTransaction(login, idMoyenPaiement, transaction);

                LOGGER.info("Transaction effectuée : " + tAjoutee);

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{idMoyenPaiement}")
                        .buildAndExpand(tAjoutee.getIdTransaction()).toUri();

                return ResponseEntity.created(location).body("Transaction effectuée");

            } catch (UtilisateurInexistantException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login inexistant");
            } catch (MoyenPaiementInexistantException e) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Moyen de paiement inexistant");
            } catch (ErreurTransactionException e) {
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Erreur transaction");
            } catch (AucunBilletAcheteException e) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Aucun billet Acheté");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problème de génération de qrcode");
            }
        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping(value = "/user/{userId}/qrcode/{qrcodeKey}")
    public ResponseEntity<String> scannerQRCode(@PathVariable("userId") String login, @PathVariable("qrcodeKey") String qrcodeKey,
                                                @RequestHeader("login") String userAuthenticated){
        // TODO: 25/03/2020 vérifier que l'utilisateur est admin
        try {

            facadePaiement.scannerQRCode(qrcodeKey);

            return ResponseEntity.ok().body("Billet Valide");

        } catch (QrCodeNonReconnuException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("QRcode non reconnu");
        } catch (BilletInvalideException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Billet déjà validé ou expiré");
        }
    }

    @GetMapping(value = "/user/{userId}/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDTO>> obtenirTransactionsByLogin(@PathVariable("userId") String login,
                                                                           @RequestHeader("login") String userAuthenticated){
        if (userAuthenticated.equals(login)) {

            try {
                List<TransactionDTO> transactionDTOS = facadePaiement.getTransactionsByLogin(login);

                return ResponseEntity.ok().body(transactionDTOS);

            } catch (UtilisateurInexistantException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
