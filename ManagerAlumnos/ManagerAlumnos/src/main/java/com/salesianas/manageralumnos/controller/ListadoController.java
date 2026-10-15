package com.salesianas.manageralumnos.controller;

import com.salesianas.manageralumnos.model.Alumno;
import com.salesianas.manageralumnos.model.AlumnosRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

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
    private Button btnEliminar;

    @FXML
    public void initialize() {
        // 1. Enlazar columnas con las propiedades observables del modelo
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        colApellidos.setCellValueFactory(data -> data.getValue().apellidosProperty());
        colEmail.setCellValueFactory(data -> data.getValue().emailProperty());
        colCurso.setCellValueFactory(data -> data.getValue().cursoProperty());

        // 2. Asignar la lista observable de alumnos a la TableView
        tablaAlumnos.setItems(AlumnosRepository.todos());

        // 3. Mensaje visual para cuando la tabla no tenga registros
        tablaAlumnos.setPlaceholder(new Label("No hay alumnos registrados actualmente."));

        // 4. Deshabilitar botón Eliminar si no hay ninguna fila seleccionada
        btnEliminar.disableProperty().bind(
                tablaAlumnos.getSelectionModel().selectedItemProperty().isNull()
        );
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