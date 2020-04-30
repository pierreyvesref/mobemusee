package views.jfx;

import controller.JFXController;
import dto.RoleDTO;
import exceptions.CompteUtilisateurAdminInexistant;
import exceptions.ConnexionImpossibleException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.ViewsInterface;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Authentification implements ViewsInterface {

    private Stage primaryStage;

    private JFXController controleur;
    @FXML
    AnchorPane topNiveau;
    @FXML
    TextField pseudo;
    @FXML
    PasswordField mdp;
    @FXML
    Label labelErreur;
    @FXML
    Button buttonConnexion;


    public void setMonControleur(JFXController monControleur) {
        this.controleur = monControleur;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static Authentification creerInstance(JFXController c, Stage primaryStage) {
        URL location = Authentification.class.getResource("/authentification.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Authentification vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMonControleur(c);
        return vue;
    }

    public void show() {
        primaryStage.setTitle("Authentification administrateur");
        primaryStage.setScene(new Scene(topNiveau, 650, 650));
        primaryStage.show();

    }


    public void goToMenuPrincipal() {
        try {
            /*
            List<RoleDTO> rolesUser = controleur.getRoles(pseudo.getText());

            for(RoleDTO role : rolesUser) {
                System.out.println(role.getValue());
            }
            */

            controleur.connexion(pseudo.getText(),mdp.getText());
            controleur.goToMenuPrincipal();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e) {
            labelErreur.setText("InterruptedException");
        }
        catch (CompteUtilisateurAdminInexistant e) {
            labelErreur.setText("Informations erronées !");
        }
    }

}
