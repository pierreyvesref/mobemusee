package views.jfx;

import controller.JFXController;
import javafx.stage.Stage;
import views.FabriqueVueInterface;

public class FabriqueVue implements FabriqueVueInterface {

    Stage primaryStage;

    public FabriqueVue(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Authentification buildAuthentificationView(JFXController c) {
        return Authentification.creerInstance(c,primaryStage);
    }

    public MenuPrincipal buildMenuPrincipalView(JFXController c) {
        return MenuPrincipal.creerInstance(c,primaryStage);
    }

    public Oeuvres buildOeuvresView(JFXController c) {
        return Oeuvres.creerInstance(c,primaryStage);
    }

    public Expositions buildExpositionsView(JFXController c) {
        return Expositions.creerInstance(c,primaryStage);
    }

}
