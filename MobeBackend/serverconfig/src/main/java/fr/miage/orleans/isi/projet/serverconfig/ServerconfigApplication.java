package fr.miage.orleans.isi.projet.serverconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigServer
public class ServerconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerconfigApplication.class, args);
    }

}
