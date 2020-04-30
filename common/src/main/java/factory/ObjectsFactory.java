package factory;

import objects.musee.Deplacement;
import objects.musee.Exposition;
import objects.musee.Oeuvre;
import objects.musee.Salle;
import objects.paiement.*;
import objects.users.Role;
import objects.users.User;

import java.util.Date;
import java.util.List;

public class ObjectsFactory {

    
    //// TODO: 20/03/2020 à modifier, cet id sera généré lorsqu'on implementera la persistance (@Id)

    public static User fabriquerUser(String pseudo, String mdp, List<Role> roles, String nom, String prenom, String mail) {
        User user = new User(pseudo, mdp, roles, nom, prenom, mail);
        return user;
    }

    public static Oeuvre fabriquerOeuvre(int id, String nom, Date dateAchat, double prixAchat){
        Oeuvre oeuvre = new Oeuvre(id, nom, dateAchat, prixAchat);
        return oeuvre;
    }

    public static Exposition fabriquerExposition(int id, String nom, Date dateDebut, Date dateFin){
        Exposition exposition = new Exposition(id, nom, dateDebut, dateFin);
        return exposition;
    }

    public static void deplacement(int id, Salle salle, Oeuvre oeuvre, Date dateDeplacement){
        Deplacement deplacement = new Deplacement(id, salle, oeuvre, dateDeplacement);
        salle.addOeuvre(oeuvre);
        // récupération de l'ancienne salle où se trouvait l'oeuvre -> retirer l'oeuvre de la liste
    }

    public static MoyenPaiement fabriquerMoyenPaiement(int id, TypeCarte typeCarte, long numCompte, Date dateExpiration, int cryptogramme, String titulaire){
        MoyenPaiement moyenPaiement = new MoyenPaiement(id, typeCarte, numCompte, dateExpiration, cryptogramme, titulaire);
        return moyenPaiement;
    }

    public static Transaction fabriquerTransaction(int id, MoyenPaiement moyenPaiement, List<BilletMusee> billets, double montant){
        Transaction transaction = new Transaction(id, moyenPaiement, billets, montant);
        return transaction;
    }

    public static BilletMusee fabriquerBilletMusee(int id, String keyQrCode, Date dateVisite, TypeTarif type){
        BilletMusee billetMusee = new BilletMusee(id, keyQrCode, dateVisite, type);
        return billetMusee;
    }

}
