package fr.miage.orleans.isi.projet.eurekadiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryApplication {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Waiting 15 seconds for SpringConfig Server to be up...");
        Thread.sleep(15000L);
        SpringApplication.run(EurekaDiscoveryApplication.class, args);
    }

}
