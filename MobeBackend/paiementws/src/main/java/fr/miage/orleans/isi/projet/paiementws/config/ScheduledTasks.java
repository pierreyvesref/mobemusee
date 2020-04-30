package fr.miage.orleans.isi.projet.paiementws.config;

import fr.miage.orleans.isi.projet.paiementws.services.impl.FacadePaiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    FacadePaiement facadePaiement;

    //tous les jours à 1h du matin on test si les billets ont expiré
    @Scheduled(cron = "0 0 1 * * *")
    public void batchTest(){
        facadePaiement.majValiditeBillets();
    }

}
