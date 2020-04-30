package views.jfx;

import controller.JFXController;
import dto.OeuvreDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.ViewsInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal implements ViewsInterface {

    private Stage primaryStage;

    private JFXController controleur;
    @FXML
    AnchorPane topNiveau;
    @FXML
    Button buttonOeuvres;
    @FXML
    Button buttonExpositions;
    @FXML
    Label labelErreur;
    @FXML
    Button buttonDeconnexion;


    public void setMonControleur(JFXController monControleur) {
        this.controleur = monControleur;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static MenuPrincipal creerInstance(JFXController c, Stage primaryStage) {
        URL location = MenuPrincipal.class.getResource("/menuprincipal.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuPrincipal vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMonControleur(c);
        return vue;
    }

    public void show() {
        primaryStage.setTitle("Menu principal");
        primaryStage.setScene(new Scene(topNiveau, 650, 650));
        primaryStage.show();

    }


    public void goToOeuvres() {
        try {
            controleur.getOeuvres();
        } catch (IOException e) {
            labelErreur.setText("Voir oeuvres - IOException");
        } catch (InterruptedException e) {
            labelErreur.setText("Voir oeuvres - InterruptedException");
        }
        controleur.goToOeuvres();
        /*
        }
        catch(IOException e) {
            labelErreur.setText("IOException");
        }
        catch(InterruptedException e) {
            labelErreur.setText("InterruptedException");
        }
        */
    }

    public void goToExpositions() {
        try {
            controleur.getExpositions();
            controleur.goToExpositions();
        }
        catch(IOException e) {
            labelErreur.setText("IOException");
        }
        catch(InterruptedException e) {
            labelErreur.setText("InterruptedException");
        }

    }

    public void deconnexion() {
        controleur.deconnexion();
    }

}
