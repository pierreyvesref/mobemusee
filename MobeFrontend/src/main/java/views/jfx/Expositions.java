package views.jfx;

import controller.JFXController;
import dto.ExpositionDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Expositions {

    private Stage primaryStage;

    private JFXController controleur;

    private int idExpoEnCours;

    @FXML
    AnchorPane topNiveau;
    @FXML
    Button buttonMenu;
    @FXML
    Button buttonSupprimer;
    @FXML
    Button buttonFormExposition;
    @FXML
    ListView listeExpositions;
    @FXML
    Button buttonRetourExpositions;
    @FXML
    Label labelNom;
    @FXML
    TextField textFieldNom;
    @FXML
    Label labelDebut;
    @FXML
    DatePicker datePickerDateDebut;
    @FXML
    Label labelFin;
    @FXML
    DatePicker datePickerDateFin;
    @FXML
    Label labelErreurExpo;
    @FXML
    Button buttonAjouter;


    public void setMonControleur(JFXController monControleur) {
        this.controleur = monControleur;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static Expositions creerInstance(JFXController c, Stage primaryStage) {
        URL location = Expositions.class.getResource("/expositions.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Expositions vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMonControleur(c);
        return vue;
    }

    public void show() {
        afficherListeExpositions();
        primaryStage.setTitle("Expositions");
        primaryStage.setScene(new Scene(topNiveau, 650, 650));
        primaryStage.show();
    }

    public void ajouterExposition() {
        String nom = this.textFieldNom.getText();

        if(this.textFieldNom.getText().equals("") || this.datePickerDateDebut.getValue()==null || this.datePickerDateFin.getValue()==null) {
            labelErreurExpo.setText("Veuillez renseigner tous les champs !");
        }
        else {
            LocalDate dateD = this.datePickerDateDebut.getValue();
            Date dateDebut = Date.from(dateD.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());

            LocalDate dateF = this.datePickerDateFin.getValue();
            Date dateFin = Date.from(dateF.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());

            if (dateF.isBefore(dateD)) {
                String passageLigne = System.getProperty("line.separator");
                labelErreurExpo.setText("La date de fin ne peut pas être antérieure" + passageLigne + "à la date de début !");
            } else {
                try {
                    controleur.ajouterExposition(nom, dateDebut, dateFin);
                    controleur.goToExpositions();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public void supprimerExposition() {

        String idExpoString = listeExpositions.getSelectionModel().getSelectedItem().toString().substring(0,listeExpositions.getSelectionModel().getSelectedItem().toString().indexOf(" "));
        idExpoEnCours = Integer.parseInt(idExpoString);
        try {
            this.listeExpositions.getSelectionModel().getSelectedItem();
            controleur.supprimerExposition(idExpoEnCours);
            this.afficherListeExpositions();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        afficherListeExpositions();
    }

    public void goToMenuPrincipal() {
        controleur.goToMenuPrincipal();
    }

    public void afficherListeExpositions() {

        try {
            List<ExpositionDTO> expositions = controleur.getExpositions();

            ObservableList<String> listeLabelsExpo = FXCollections.observableArrayList();

            for(ExpositionDTO expo : expositions)
            {
                listeLabelsExpo.add(expo.getId()+ " / Nom : " + expo.getNom() + " | Date début : " +expo.getDateDebutExpo() + " | Date fin : " + expo.getDateFinExpo());
            }

            listeExpositions.setItems(listeLabelsExpo);
            listeExpositions.getSelectionModel().select(0);
            labelErreurExpo.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        buttonSupprimer.setVisible(true);
        buttonFormExposition.setVisible(true);
        listeExpositions.setVisible(true);
        buttonRetourExpositions.setVisible(false);
        labelNom.setVisible(false);
        textFieldNom.setVisible(false);
        labelDebut.setVisible(false);
        datePickerDateDebut.setVisible(false);
        labelFin.setVisible(false);
        datePickerDateFin.setVisible(false);
        buttonAjouter.setVisible(false);
    }

    public void afficherFormExposition() {
        buttonSupprimer.setVisible(false);
        buttonFormExposition.setVisible(false);
        listeExpositions.setVisible(false);
        buttonRetourExpositions.setVisible(true);
        labelNom.setVisible(true);
        textFieldNom.setVisible(true);
        labelDebut.setVisible(true);
        datePickerDateDebut.setVisible(true);
        labelFin.setVisible(true);
        datePickerDateFin.setVisible(true);
        buttonAjouter.setVisible(true);
    }

}
