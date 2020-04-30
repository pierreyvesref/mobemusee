package fr.miage.orleans.isi.projet.museews;

import fr.miage.orleans.isi.projet.museews.exceptions.*;
import fr.miage.orleans.isi.projet.museews.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
public class MuseewsApplication {
    @Autowired
    private static IExpositionService expositionService;
    @Autowired
    private static IOeuvreService oeuvresService;
    @Autowired
    private static ISalleService salleService;
    @Autowired
    private static IFacadeMusee facadeMusee;
    @Autowired
    private static IDeplacementService deplacementService;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Waiting 30 seconds for Eureka to be up...");
        Thread.sleep(30000L);
        ApplicationContext ctx = SpringApplication.run(MuseewsApplication.class, args);
        oeuvresService = (IOeuvreService) ctx.getBean("oeuvresService");
        expositionService = (IExpositionService) ctx.getBean("expositionService");
        salleService = (ISalleService) ctx.getBean("salleService");
        facadeMusee = (IFacadeMusee) ctx.getBean("facadeMusee");
        deplacementService = (IDeplacementService) ctx.getBean("deplacementService");
    }

}
