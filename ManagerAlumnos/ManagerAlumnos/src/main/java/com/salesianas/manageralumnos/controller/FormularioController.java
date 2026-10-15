package com.salesianas.manageralumnos.controller;

import com.salesianas.manageralumnos.model.AlumnosRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCurso;

    @FXML
    private void handleGuardar() {
        if (txtNombre.getText().isBlank() || txtApellidos.getText().isBlank()) {
            return;
        }

        AlumnosRepository.agregar(
                txtNombre.getText(),
                txtApellidos.getText(),
                txtEmail.getText(),
                txtCurso.getText()
        );

        // Cerrar ventana actual
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}