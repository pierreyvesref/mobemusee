package fr.miage.orleans.isi.projet.paiementws;

import fr.miage.orleans.isi.projet.paiementws.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class PaiementwsApplication {
    @Autowired
    private static IUserService userService;
    @Autowired
    private static IFacadePaiement facadePaiement;
    @Autowired
    private static IRoleService roleService;
    @Autowired
    private static IBilletService billetService;
    @Autowired
    private static IMoyenPaiementService moyenPaiementService;
    @Autowired
    private static ITransactionService transactionService;
    @Autowired
    private static ITypeTarifService typeTarifService;
    @Autowired
    private static ITypeCarteService typeCarteService;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Waiting 30 seconds for Eureka to be up...");
        Thread.sleep(30000L);
        ApplicationContext ctx = SpringApplication.run(PaiementwsApplication.class, args);
        userService = (IUserService) ctx.getBean("userService");
        facadePaiement = (IFacadePaiement) ctx.getBean("facadePaiement");
        roleService = (IRoleService) ctx.getBean("roleService");
        billetService= (IBilletService) ctx.getBean("billetService");
        moyenPaiementService= (IMoyenPaiementService) ctx.getBean("moyenPaiementService");
        transactionService= (ITransactionService) ctx.getBean("transactionService");
        typeTarifService= (ITypeTarifService) ctx.getBean("typeTarifService");
        typeCarteService= (ITypeCarteService) ctx.getBean("typeCarteService");

    }

}
