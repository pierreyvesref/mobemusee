package fr.miage.orleans.isi.projet.paiementws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
public class WebConfig implements WebMvcConfigurer {

    @Value("${authent.connexion.uri}")
    private String connexionUri;

    @Value("${authent.checkToken.uri}")
    private String checkTokenUri;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptorHandler(connexionUri, checkTokenUri)).addPathPatterns("/**");
    }
}

