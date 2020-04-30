package views.jfx;

import controller.JFXController;
import dto.OeuvreDTO;
import dto.SalleDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import views.ViewsInterface;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

public class Oeuvres implements ViewsInterface {

    private Stage primaryStage;

    private JFXController controleur;

    private int idOeuvreEnCours;

    @FXML
    AnchorPane topNiveau;
    @FXML
    ListView listeOeuvres;
    @FXML
    Button buttonSupprimer;
    @FXML
    Button buttonModifier;
    @FXML
    Button buttonMenu;
    @FXML
    Button buttonRetourListeOeuvres;
    @FXML
    Button buttonAjouter;
    @FXML
    Label labelNom;
    @FXML
    Label labelDateAchat;
    @FXML
    Label labelPrixAchat;
    @FXML
    TextField textFieldNom;
    @FXML
    TextField textFieldPrixAchat;
    @FXML
    DatePicker datePickerDateAchat;
    @FXML
    Label labelErreur;
    @FXML
    Label labelNomModif;
    @FXML
    Label labelDateAchatModif;
    @FXML
    Label labelPrixAchatModif;
    @FXML
    TextField textFieldNomModif;
    @FXML
    DatePicker datePickerDateAchatModif;
    @FXML
    TextField textFieldPrixAchatModif;
    @FXML
    Label labelSalle;
    @FXML
    ComboBox comboBoxSalle;
    @FXML
    Button buttonEnregistrerModifs;
    @FXML
    Button buttonNouvelleOeuvre;

    public void setMonControleur(JFXController monControleur) {
        this.controleur = monControleur;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static Oeuvres creerInstance(JFXController c, Stage primaryStage) {
        URL location = Oeuvres.class.getResource("/oeuvres.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Oeuvres vue = fxmlLoader.getController();
        vue.setPrimaryStage(primaryStage);
        vue.setMonControleur(c);
        return vue;
    }

    public void show() {
        afficherListeOeuvres();
        primaryStage.setTitle("Oeuvres");
        primaryStage.setScene(new Scene(topNiveau, 650, 650));
        primaryStage.show();
    }

    public void goToMenuPrincipal() {
        controleur.goToMenuPrincipal();
    }

    public void ajouterOeuvre() {
        System.out.println(this.datePickerDateAchat.getValue());
        if(this.textFieldNom.getText().equals("") || this.datePickerDateAchat.getValue()==null || this.textFieldPrixAchat.getText().equals("")) {
            labelErreur.setText("Veuillez renseigner tous les champs !");
        }
        else {
            String nom = this.textFieldNom.getText();
            int prixAchat = parseInt(this.textFieldPrixAchat.getText());
            LocalDate localDate = this.datePickerDateAchat.getValue();
            Date dateAchat = Date.from(localDate.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());

            try {
                controleur.ajouterOeuvre(nom, prixAchat, dateAchat);
                controleur.goToOeuvres();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                this.labelErreur.setText("Format de date incorrecte !");
            }

        }
    }

    public void supprimerOeuvre() {

        String idOeuvreString = listeOeuvres.getSelectionModel().getSelectedItem().toString().substring(0,listeOeuvres.getSelectionModel().getSelectedItem().toString().indexOf(" "));
        idOeuvreEnCours = Integer.parseInt(idOeuvreString);

        try {
            this.listeOeuvres.getSelectionModel().getSelectedItem();
            controleur.supprimerOeuvre(idOeuvreEnCours);
            this.afficherListeOeuvres();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void confirmerModifs() {
        String newNom = this.textFieldNomModif.getText();
        String newPrixAchat = this.textFieldPrixAchatModif.getText();
        double newPrixAchatInt = Double.parseDouble(newPrixAchat);
        LocalDate date = this.datePickerDateAchatModif.getValue();
        Date dateFormatee = null;
        if(!date.equals(null)) {
            dateFormatee = Date.from(date.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
        }

        if(!newNom.isEmpty() && !newPrixAchat.isEmpty() && !dateFormatee.equals(null)) {
            controleur.confirmerModifs(idOeuvreEnCours);
        }

        int idSalle = Integer.parseInt(this.comboBoxSalle.getSelectionModel().getSelectedItem().toString().substring(0,this.comboBoxSalle.getSelectionModel().getSelectedItem().toString().indexOf(" ")));
        SalleDTO salle = null;
        try {
            salle = controleur.getSalleById(idSalle);
            controleur.moveOeuvre(idOeuvreEnCours, salle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<SalleDTO> salles = null;
        try {
            salles = controleur.getSalles();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void modifierOeuvre() {

        String idOeuvreString = listeOeuvres.getSelectionModel().getSelectedItem().toString().substring(0,listeOeuvres.getSelectionModel().getSelectedItem().toString().indexOf(" "));
        idOeuvreEnCours = Integer.parseInt(idOeuvreString);
        OeuvreDTO oeuvre = null;
        try {
            oeuvre = controleur.getOeuvreById(idOeuvreEnCours);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textFieldNomModif.setText(oeuvre.getNomOeuvre());
        textFieldPrixAchatModif.setText(Double.toString(oeuvre.getPrixAchat()));

        List<SalleDTO> lesSalles = null;
        try {
            lesSalles = controleur.getSalles();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;
        int indexSalle = 0;

        ObservableList<String> listeLabelsSalles = FXCollections.observableArrayList();

        for(SalleDTO salle : lesSalles)
        {
            listeLabelsSalles.add(salle.getId()+ " / "+salle.getValue());
            for(OeuvreDTO e : salle.getOeuvres()) {
                if(e.getId() == oeuvre.getId()) {
                    indexSalle = i;
                }
            }

            i++;
        }

        comboBoxSalle.setItems(listeLabelsSalles);
        comboBoxSalle.getSelectionModel().select(indexSalle);

        listeOeuvres.setVisible(false);
        buttonSupprimer.setVisible(false);
        buttonModifier.setVisible(false);
        buttonRetourListeOeuvres.setVisible(true);
        buttonAjouter.setVisible(false);
        buttonNouvelleOeuvre.setVisible(false);
        labelNom.setVisible(false);
        labelDateAchat.setVisible(false);
        labelPrixAchat.setVisible(false);
        textFieldNom.setVisible(false);
        textFieldPrixAchat.setVisible(false);
        datePickerDateAchat.setVisible(false);
        labelNomModif.setVisible(true);
        labelDateAchatModif.setVisible(true);
        labelPrixAchatModif.setVisible(true);
        textFieldNomModif.setVisible(true);
        datePickerDateAchatModif.setVisible(true);
        textFieldPrixAchatModif.setVisible(true);
        buttonEnregistrerModifs.setVisible(true);
        labelSalle.setVisible(true);
        comboBoxSalle.setVisible(true);


        textFieldPrixAchatModif.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldPrixAchatModif.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void afficherFormOeuvre() {
        listeOeuvres.setVisible(false);
        buttonSupprimer.setVisible(false);
        buttonModifier.setVisible(false);
        buttonRetourListeOeuvres.setVisible(true);
        buttonAjouter.setVisible(true);
        buttonNouvelleOeuvre.setVisible(false);
        labelNom.setVisible(true);
        labelDateAchat.setVisible(true);
        labelPrixAchat.setVisible(true);
        textFieldNom.setVisible(true);
        textFieldPrixAchat.setVisible(true);
        datePickerDateAchat.setVisible(true);
        labelNomModif.setVisible(false);
        labelDateAchatModif.setVisible(false);
        labelPrixAchatModif.setVisible(false);
        textFieldNomModif.setVisible(false);
        datePickerDateAchatModif.setVisible(false);
        textFieldPrixAchatModif.setVisible(false);
        buttonEnregistrerModifs.setVisible(false);
        comboBoxSalle.setVisible(false);
        labelSalle.setVisible(false);

        textFieldPrixAchat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldPrixAchat.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void afficherListeOeuvres() {

        List<OeuvreDTO> lesOeuvres = null;
        try {
            lesOeuvres = controleur.getOeuvres();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ObservableList<String> listeLabelsOeuvres = FXCollections.observableArrayList();

        for(OeuvreDTO oeuvre : lesOeuvres)
        {
            listeLabelsOeuvres.add(oeuvre.getId()+ " / Nom : " + oeuvre.getNomOeuvre() + " | Date achat : " + oeuvre.getDateAchat() + " | Prix : " + oeuvre.getPrixAchat() + " euros");
        }

        listeOeuvres.setItems(listeLabelsOeuvres);
        listeOeuvres.getSelectionModel().select(0);
        labelErreur.setText("");

        listeOeuvres.setVisible(true);
        buttonSupprimer.setVisible(true);
        buttonModifier.setVisible(true);
        buttonRetourListeOeuvres.setVisible(false);
        buttonAjouter.setVisible(false);
        buttonNouvelleOeuvre.setVisible(true);
        labelNom.setVisible(false);
        labelDateAchat.setVisible(false);
        labelPrixAchat.setVisible(false);
        textFieldNom.setVisible(false);
        textFieldPrixAchat.setVisible(false);
        datePickerDateAchat.setVisible(false);
        labelNomModif.setVisible(false);
        labelDateAchatModif.setVisible(false);
        labelPrixAchatModif.setVisible(false);
        textFieldNomModif.setVisible(false);
        datePickerDateAchatModif.setVisible(false);
        textFieldPrixAchatModif.setVisible(false);
        buttonEnregistrerModifs.setVisible(false);
        comboBoxSalle.setVisible(false);
        labelSalle.setVisible(false);
    }

}
