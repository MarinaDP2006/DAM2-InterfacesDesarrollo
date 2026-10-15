package com.salesianas.manageralumnos.controller;

import com.salesianas.manageralumnos.model.Alumno;
import com.salesianas.manageralumnos.model.AlumnosRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ListadoController {

    @FXML
    private TableView<Alumno> tablaAlumnos;
    @FXML
    private TableColumn<Alumno, Number> colId;
    @FXML
    private TableColumn<Alumno, String> colNombre;
    @FXML
    private TableColumn<Alumno, String> colApellidos;
    @FXML
    private TableColumn<Alumno, String> colEmail;
    @FXML
    private TableColumn<Alumno, String> colCurso;

    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        colApellidos.setCellValueFactory(data -> data.getValue().apellidosProperty());
        colEmail.setCellValueFactory(data -> data.getValue().emailProperty());
        colCurso.setCellValueFactory(data -> data.getValue().cursoProperty());

        tablaAlumnos.setItems(AlumnosRepository.todos());
        tablaAlumnos.setPlaceholder(new Label("No hay alumnos registrados actualmente."));

        btnEditar.disableProperty().bind(
                tablaAlumnos.getSelectionModel().selectedItemProperty().isNull()
        );
        btnEliminar.disableProperty().bind(
                tablaAlumnos.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    @FXML
    private void handleNuevo() throws IOException {
        abrirFormulario(null);
    }

    @FXML
    private void handleEditar() throws IOException {
        Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            abrirFormulario(seleccionado);
        }
    }

    private void abrirFormulario(Alumno alumno) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                com.salesianas.manageralumnos.HelloApplication.class.getResource("formulario-view.fxml")
        );
        Stage stage = new Stage();
        stage.setTitle(alumno == null ? "Nuevo Alumno" : "Editar Alumno");
        stage.setScene(new Scene(loader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(tablaAlumnos.getScene().getWindow());

        if (alumno != null) {
            FormularioController controller = loader.getController();
            controller.setAlumno(alumno);
        }

        stage.showAndWait();
    }

    @FXML
    private void handleEliminar() {
        Alumno seleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("¿Deseas eliminar a " + seleccionado.getNombre() + "?");

            confirmacion.showAndWait().ifPresent(respuesta -> {
                if (respuesta == ButtonType.OK) {
                    AlumnosRepository.eliminar(seleccionado);
                }
            });
        }
    }
}
