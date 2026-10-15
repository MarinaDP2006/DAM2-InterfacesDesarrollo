package com.salesianas.manageralumnos.controller;

import com.salesianas.manageralumnos.model.Alumno;
import com.salesianas.manageralumnos.model.AlumnosRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioController {

    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> cmbCurso;

    private Alumno alumnoEditar;

    @FXML
    private void initialize() {
        cmbCurso.setItems(FXCollections.observableArrayList(
                "1.º DAM",
                "2.º DAM",
                "1.º DAW",
                "2.º DAW"
        ));
        cmbCurso.getSelectionModel().selectFirst();
    }

    /** Precarga el formulario para editar un alumno existente. */
    public void setAlumno(Alumno alumno) {
        this.alumnoEditar = alumno;
        lblTitulo.setText("Editar Alumno");
        txtNombre.setText(alumno.getNombre());
        txtApellidos.setText(alumno.getApellidos());
        txtEmail.setText(alumno.getEmail());
        cmbCurso.getSelectionModel().select(alumno.getCurso());
    }

    @FXML
    private void handleGuardar() {
        if (txtNombre.getText().isBlank() || txtApellidos.getText().isBlank()) {
            return;
        }

        String curso = cmbCurso.getSelectionModel().getSelectedItem();
        if (curso == null || curso.isBlank()) {
            return;
        }

        if (alumnoEditar == null) {
            AlumnosRepository.agregar(
                    txtNombre.getText(),
                    txtApellidos.getText(),
                    txtEmail.getText(),
                    curso
            );
        } else {
            AlumnosRepository.actualizar(
                    alumnoEditar,
                    txtNombre.getText(),
                    txtApellidos.getText(),
                    txtEmail.getText(),
                    curso
            );
        }

        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
