package fr.miage.orleans.isi.projet.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GatewayApplication {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Waiting 30 seconds for Eureka to be up...");
        Thread.sleep(30000L);
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Value("${mobe.path}")
    private String mobePath;

    @Value("${authent.path}")
    private String authentPath;

    @Value("${authent.name}")
    private String authentServiceName;

    @Value("${musee.path}")
    private String museePath;

    @Value("${musee.name}")
    private String museeServiceName;

    @Value("${paiement.path}")
    private String paiementPath;

    @Value("${paiement.name}")
    private String paiementServiceName;



    //construction des routes pour accéder à chaque webservice de l'application mobe
    //toutes centralisées on y accède par le seul hote+port : http://localhost:8088/
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                //authentificationws
                .route(r->r.path(mobePath + authentPath + "/**")
                        .filters(f-> f.rewritePath(mobePath + "/(?<remains>.*)", "/${remains}").preserveHostHeader())
                        .uri("lb://" + authentServiceName.toUpperCase() + "/")
                        .id(authentServiceName)
                )
                //museews
                .route(r->r.path(mobePath + museePath + "/**")
                        .filters(f->f.rewritePath(mobePath + "/(?<remains>.*)", "/${remains}").preserveHostHeader())
                        .uri("lb://" + museeServiceName.toUpperCase() + "/")
                        .id(museeServiceName)
                )
                //paiementws
                .route(r->r.path(mobePath + paiementPath + "/**")
                        .filters(f->f.rewritePath(mobePath+ "/(?<remains>.*)", "/${remains}").preserveHostHeader())
                        .uri("lb://" + paiementServiceName.toUpperCase() + "/")
                        .id(paiementServiceName)
                )
                .build();
    }

}
