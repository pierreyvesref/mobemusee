package controller;

import application.ProxyService;
import dto.ExpositionDTO;
import dto.OeuvreDTO;
import dto.RoleDTO;
import dto.SalleDTO;
import exceptions.CompteUtilisateurAdminInexistant;
import objects.musee.Exposition;
import objects.musee.Oeuvre;
import services.FacadeFront;
import views.jfx.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class JFXController {

    private String login;
    private String token;

    private FacadeFront facade;

    private FabriqueVue fabriqueVue;
    private Authentification vueAuthentification;
    private MenuPrincipal vueMenuPrincipal;
    private Oeuvres vueOeuvres;
    private Expositions vueExpositions;


    /* * * * * * * * * * * * */
    /*   AFFICHAGE DES VUES  */
    /* * * * * * * * * * * * */

    public JFXController(FabriqueVue fabriqueVue) {
        this.fabriqueVue = fabriqueVue;
        this.facade = FacadeFront.renvoyerInstance();
        this.vueAuthentification = this.fabriqueVue.buildAuthentificationView(this);
        this.vueAuthentification.show();
    }

    public void goToAuthentification() {
        this.vueAuthentification = this.fabriqueVue.buildAuthentificationView(this);
        this.vueAuthentification.show();
    }

    public void goToMenuPrincipal() {
        this.vueMenuPrincipal = this.fabriqueVue.buildMenuPrincipalView(this);
        this.vueMenuPrincipal.show();
    }

    public void goToOeuvres() {
        this.vueOeuvres = this.fabriqueVue.buildOeuvresView(this);
        this.vueOeuvres.show();
    }

    public void goToExpositions() {
        this.vueExpositions = this.fabriqueVue.buildExpositionsView(this);
        this.vueExpositions.show();
    }

    /* * * * * * * * * * * * */
    /*  APPELS PROXYSERVICE  */
    /* * * * * * * * * * * * */

    public void connexion(String login, String mdp) throws IOException, InterruptedException, CompteUtilisateurAdminInexistant {
        String token = ProxyService.connecter(login, mdp);
        this.token = token;
        this.login = login;
        System.out.println("Utilisateur " + this.login + " connecté");
    }

    public void deconnexion() {
        try {
            String body = ProxyService.deconnecter(this.login,this.token);
            System.out.println("Utilisateur " + this.login + " déconnecté");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.token = null;
        this.login = null;
        goToAuthentification();
    }

    public List<RoleDTO> getRoles(String pseudo) throws IOException, InterruptedException {
        return ProxyService.getRoles(pseudo);
    }

    public void ajouterOeuvre(String nom, int prixAchat, Date dateAchat) throws IOException, InterruptedException, ParseException {
        //Date vraieDate=new SimpleDateFormat("yyyy-MM-dd").parse(dateAchat);
        Oeuvre oeuvre = new Oeuvre(0, nom, dateAchat, prixAchat);
        //OeuvreDTO oeuvreDTO = OeuvreDTO.createOeuvreDTO(oeuvre);
        ProxyService.ajouterOeuvre(oeuvre, this.token, this.login);
    }

    public void supprimerOeuvre(int idOeuvre) throws IOException, InterruptedException {
        ProxyService.supprimerOeuvre(idOeuvre, this.token, this.login);
    }

    public void confirmerModifs(int idOeuvre) {
        ProxyService.modifierOeuvre(idOeuvre, this.token, this.login);
    }

    public void moveOeuvre(int idOeuvre, SalleDTO salle) throws IOException, InterruptedException {
        ProxyService.moveOeuvre(idOeuvre, salle, this.token, this.login);
    }

    public OeuvreDTO getOeuvreById(int idOeuvre) throws IOException, InterruptedException {
        return ProxyService.getOeuvreById(idOeuvre, this.token, this.login);
    }

    public void ajouterExposition(String nomExposition, Date dateDebut, Date dateFin) throws IOException, InterruptedException {
        Exposition exposition = new Exposition(0, nomExposition, dateDebut, dateFin);
        ProxyService.ajouterExposition(exposition, this.token, this.login);
    }

    public void supprimerExposition(int idExposition) throws IOException, InterruptedException {
        ProxyService.supprimerExposition(idExposition, this.token, this.login);
    }

    public SalleDTO getSalleById(int idSalle) throws IOException, InterruptedException {
        return ProxyService.getSalleById(idSalle, this.token, this.login);
    }

    public List<OeuvreDTO> getOeuvres() throws IOException, InterruptedException {
        return ProxyService.getOeuvres(token, login);
    }

    public List<SalleDTO> getSalles() throws IOException, InterruptedException {
        return ProxyService.getSalles(token, login);
    }

    public List<ExpositionDTO> getExpositions() throws IOException, InterruptedException {
        return ProxyService.getExpositions(token, login);
    }


}