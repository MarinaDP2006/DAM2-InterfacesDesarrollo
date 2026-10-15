package com.salesianas.entradascinecss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class CineApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CineApp.class.getResource("cine-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 540, 620);

        // Cargar BootstrapFX
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        // Cargar estilos personalizada (entradascine.css)
        String customCss = getClass().getResource("styles.css") != null ?
                getClass().getResource("styles.css").toExternalForm() : null;
        if (customCss != null) {
            scene.getStylesheets().add(customCss);
        }

        stage.setTitle("Reserva de Entradas de Cine");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}