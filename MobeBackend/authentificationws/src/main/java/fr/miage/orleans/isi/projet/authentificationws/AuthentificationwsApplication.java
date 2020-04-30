package fr.miage.orleans.isi.projet.authentificationws;

import fr.miage.orleans.isi.projet.authentificationws.services.IFacadeAuthentification;
import fr.miage.orleans.isi.projet.authentificationws.services.IRoleService;
import fr.miage.orleans.isi.projet.authentificationws.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class AuthentificationwsApplication {
    @Autowired
    private static IUserService userService;
    @Autowired
    private static IFacadeAuthentification facadeAuthentification;
    @Autowired
    private static IRoleService roleService;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Waiting 30 seconds for Eureka to be up...");
        Thread.sleep(30000L);
        ApplicationContext ctx = SpringApplication.run(AuthentificationwsApplication.class, args);
        userService = (IUserService) ctx.getBean("userService");
        facadeAuthentification = (IFacadeAuthentification) ctx.getBean("facadeAuthentification");
        roleService = (IRoleService) ctx.getBean("roleService");
    }

}
