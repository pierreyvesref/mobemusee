package fr.miage.orleans.isi.projet.paiementws.services.impl;

import fr.miage.orleans.isi.projet.paiementws.DTO.MoyenPaiementDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.TransactionDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.TypeCarteDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.UserDTO;
import fr.miage.orleans.isi.projet.paiementws.entities.BilletMuseeEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.MoyenPaiementEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import fr.miage.orleans.isi.projet.paiementws.exceptions.*;
import fr.miage.orleans.isi.projet.paiementws.qrcode.KeyGen;
import fr.miage.orleans.isi.projet.paiementws.qrcode.QRCodeGenerator;
import fr.miage.orleans.isi.projet.paiementws.services.IFacadePaiement;
import objects.paiement.*;
import objects.users.Role;
import objects.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class FacadePaiement implements IFacadePaiement {

    @Autowired
    KeyGen keyGen;
    @Autowired
    QRCodeGenerator qrCodeGenerator;
    @Autowired
    BilletService billetService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    TypeCarteService typeCarteService;
    @Autowired
    TypeTarifService typeTarifService;
    @Autowired
    UserService userService;
    @Autowired
    MoyenPaiementService moyenPaiementService;


    Map<String, UserDTO> users = new HashMap<>();

    private final AtomicInteger ID_TRANSACTION = new AtomicInteger(1);
    private final AtomicInteger ID_BILLET_MUSEE = new AtomicInteger(1);


    //A LANCER SI BASE VIDE
    /*@PostConstruct
    public void init(){
        typeCarteService.create("MASTERCARD");
        typeCarteService.create("VISA");
        typeCarteService.create("AUTRE");
        typeTarifService.create("Tarif normal", 10);
        typeTarifService.create("Tarif junior", 5);
        typeTarifService.create("Tarif senior", 8);
    }*/


    @Override
    public List<MoyenPaiementDTO> getMoyensPaiementByLogin(String login) throws UtilisateurInexistantException {
        UserDTO user = userService.getUserByLogin(login);
        if (user != null) {
            return user.getMoyenPaiements();
        } else throw new UtilisateurInexistantException();

    }

    @Override
    public MoyenPaiementDTO ajouterMoyenPaiement(String login, TypeCarteDTO typeCarte, long numCompte, Date dateExpiration, int cryptogramme, String titulaire) throws UtilisateurInexistantException, MoyenPaiementDejaAjouteException {
        UserDTO user = userService.getUserByLogin(login);
        if (user != null) {
            MoyenPaiementEntity moyenPaiement = moyenPaiementService.creerMP(typeCarteService.getByValue(typeCarte.getValue()), numCompte, dateExpiration, cryptogramme, titulaire);
            List<MoyenPaiementDTO> list = user.getMoyenPaiements();
            boolean dejaPresent = false ;
            for(MoyenPaiementDTO mp : list){
                if(mp.getNumCompte() == numCompte){
                    dejaPresent=true;
                }
            }
            if (!dejaPresent) {
                userService.addMoyenPaiement(login, moyenPaiement);
                return MoyenPaiementDTO.createFromEntity(moyenPaiement);
            } else throw new MoyenPaiementDejaAjouteException();
        } else throw new UtilisateurInexistantException();
    }

    @Override
    public void supprimerMoyenPaiement(String login, int idMoyenPaiement) throws MoyenPaiementInexistantException, UtilisateurInexistantException {
        UserDTO user = userService.getUserByLogin(login);
        if(user!=null) {
            List<MoyenPaiementDTO> moyenPaiementDTOS = user.getMoyenPaiements();
            boolean dejaPresent = false;
            for (MoyenPaiementDTO mp : moyenPaiementDTOS) {
                if (mp.getIdMoyenpaiement() == idMoyenPaiement) {
                    dejaPresent = true;
                }
            }
            if (dejaPresent) {
                userService.removeMoyenPaiement(login, moyenPaiementService.getById(idMoyenPaiement));
                moyenPaiementService.supprimerTP(moyenPaiementService.getById(idMoyenPaiement));
            } else throw new MoyenPaiementInexistantException();
        }else throw new UtilisateurInexistantException();
    }

    @Override
    public TransactionEntity effectuerTransaction(String login, int moyenPaiementId, TransactionDTO transaction) throws MoyenPaiementInexistantException, ErreurTransactionException, UtilisateurInexistantException, AucunBilletAcheteException {
        UserDTO user = userService.getUserByLogin(login);

        MoyenPaiementEntity moyenPaiement = moyenPaiementService.getById(moyenPaiementId);

        if (user != null) {
            if (moyenPaiement != null) {

                //adition de tous les pris des billets commandés ( une place junior + 2 places normales, etc...)
                transaction.getBilletsMusee().forEach(System.out::println);
                double montant = transaction.getBilletsMusee().stream()
                        .mapToDouble(b -> typeTarifService.getTypeTarif(b.getTypeTarif().getValue()).getMontantTarif())
                        .reduce((a, b) -> a + b).getAsDouble();

                if (montant > 0) {
                    if (verificationBanque(moyenPaiement, montant)) {
                        List<BilletMuseeEntity> billetsMusee = transaction.getBilletsMusee().stream()
                                .map(bm -> {
                                    try {
                                        billetService.createBilletVierge();
                                        BilletMuseeEntity billet = billetService.getBilletVierge();
                                        //on genere une clé pour chaque billet à partir de l'idBillet
                                        String key = keyGen.generateKey(String.valueOf(billet.getId()));
                                        qrCodeGenerator.generateQRCodePngIntoFile(key);
                                        billet.setValide(true);
                                        billet.setKeyQrCode(key);
                                        billet.setDateVisite(bm.getDateVisite());
                                        billet.setTypeTarif(typeTarifService.getTypeTarif(bm.getTypeTarif().getValue()));
                                        billetService.save(billet);
                                        return billet;

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return null;
                                    }
                                })
                                .collect(Collectors.toList());

                        TransactionEntity transactionEffectuee = transactionService.create(moyenPaiement, billetsMusee, montant);
                        userService.addTransaction(login, transactionEffectuee);

                        return transactionEffectuee;

                    } else throw new ErreurTransactionException();

                } else throw new AucunBilletAcheteException();

            } else throw new MoyenPaiementInexistantException();
        } else throw new UtilisateurInexistantException();

    }

    @Override
    public List<TransactionDTO> getTransactionsByLogin(String login) throws UtilisateurInexistantException {
        UserDTO user = userService.getUserByLogin(login);
        if (user != null) {
            return user.getTransactions();
        } else throw new UtilisateurInexistantException();
    }

    @Override
    public List<BilletMusee> getAllBilletsMusee() {
        //double flatmap : List<List<List<BilletMusee>>>  ---> List<BilletMusee>   pour obtenir tous les billets
        /*return users.values().stream()
                .map(User::getTransactions)
                .flatMap(Collection::stream)
                .map(Transaction::getBilletsMusee)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());*/
        return new ArrayList<>();
    }

    //Mettre à jour si la date du billet a expiré
    @Override
    public void majValiditeBillets() {

        List<BilletMuseeEntity> allBilletsMusee =billetService.getAll();
        Date dateDuJour = new Date();
        allBilletsMusee.stream()
                .filter(billetMusee -> dateDuJour.after(billetMusee.getDateVisite()))
                .forEach(billetMusee -> billetService.setInvalideValide(billetMusee.getId()));
    }


    @Override
    public void scannerQRCode(String qrcodeKey) throws QrCodeNonReconnuException, BilletInvalideException {
        BilletMuseeEntity billetMusee = billetService.getAll().stream()
                .filter(bm -> bm.getKeyQrCode().equals(qrcodeKey))
                .findFirst().get();
        if (billetMusee != null) {
            if (billetMusee.isValide()) {
                billetService.setInvalideValide(billetMusee.getId());
            } else throw new BilletInvalideException();
        } else throw new QrCodeNonReconnuException();
    }

/////////PARTIE USER

    @Override
    public Collection<UserDTO> getAllUsers() {
        return users.values();
    }


    @Override
    public Collection<UserDTO> getUsersConnectes() {
        return users.values();
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return users.get(login);
    }

}