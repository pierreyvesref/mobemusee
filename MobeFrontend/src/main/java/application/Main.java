package application;

import controller.JFXController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.jfx.FabriqueVue;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        FabriqueVue fabriqueVues = new FabriqueVue(primaryStage);
        JFXController monControleur = new JFXController(fabriqueVues);

    }


    public static void main(String[] args) {
        launch(args);
    }
}