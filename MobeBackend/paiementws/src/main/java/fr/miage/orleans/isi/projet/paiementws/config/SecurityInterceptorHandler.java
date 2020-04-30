package fr.miage.orleans.isi.projet.paiementws.config;

import fr.miage.orleans.isi.projet.paiementws.exceptions.JwtTokenMissinException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SecurityInterceptorHandler implements HandlerInterceptor {

    private URI connexionUri;
    private URI checktokenUri;
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptorHandler.class);

    private HttpClient httpClient = HttpClient.newHttpClient();

    SecurityInterceptorHandler(String connexionUri, String checkTokenUri){
        this.connexionUri = URI.create(connexionUri);
        this.checktokenUri = URI.create(checkTokenUri);
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOGGER.info("Request : " + httpServletRequest.getRequestURI());

        /*httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "*");
        httpServletResponse.addHeader("Access-Control-Expose-Headers", "*");*/

        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String login = httpServletRequest.getHeader("login");

        if(token == null){
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            LOGGER.error("No JWT token provided");
            return false;
        }

        if(login == null){
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            LOGGER.error("Can't indentify the login");
            return false;
        }

         HttpRequest request = HttpRequest.newBuilder()
        .header(HttpHeaders.AUTHORIZATION, token)
        .uri(URI.create(checktokenUri.toString()))
        .GET()
        .build();

        HttpResponse response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            LOGGER.info("Check Token result : " + response.statusCode());

            if(response.statusCode() == HttpServletResponse.SC_UNAUTHORIZED){
                throw new JwtTokenMissinException();
            }

            if(login != null && login.equals(response.body())) {
                LOGGER.info("Request Authorized : Jwt valid ");
                return true;
            }else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                httpServletResponse.addHeader("location", connexionUri.toString());
                LOGGER.info("Request Unauthorized : Wrong JWT for login: " + login + " - token: " + token);
                return false;
            }

        } catch (IOException | InterruptedException e) {
            //ressource introuvable ou HS
            httpServletResponse.setStatus(response.statusCode());
            LOGGER.error(httpServletResponse.getStatus() + " " + e);
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        int responseCodeStatus = httpServletResponse.getStatus();
        LOGGER.info("Reponse : " + HttpStatus.valueOf(responseCodeStatus));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //LOGGER.info("Exception ? : " + e);
    }

}
