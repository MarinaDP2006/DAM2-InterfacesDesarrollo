package com.salesianas.manageralumnos;

import atlantafx.base.theme.NordDark; // <- TEMA OSCURO (antes PrimerDark)
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // 1. Activar el tema globalmente en AtlantaFX 2.0.1
        Application.setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());

        // 2. Cargar la vista principal
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listado-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 560);

        stage.setTitle("Salesianas · Gestor de Alumnos DAM");
        stage.setMinWidth(760);
        stage.setMinHeight(460);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
