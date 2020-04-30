package views;

import controller.JFXController;
import views.jfx.Authentification;
import views.jfx.Expositions;
import views.jfx.MenuPrincipal;
import views.jfx.Oeuvres;

public interface FabriqueVueInterface {

    Authentification buildAuthentificationView(JFXController c);
    MenuPrincipal buildMenuPrincipalView(JFXController c);
    Expositions buildExpositionsView(JFXController c);
    Oeuvres buildOeuvresView(JFXController c);

}
