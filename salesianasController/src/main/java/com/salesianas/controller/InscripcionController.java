package com.salesianas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class InscripcionController {

    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cmbActividad;
    @FXML
    private RadioButton rbManana;
    @FXML
    private RadioButton rbTarde;
    @FXML
    private ToggleGroup grupoTurno;
    @FXML
    private CheckBox chkEquipo;
    @FXML
    private CheckBox chkCondiciones;
    @FXML
    private Button btnInscribirme;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Label lblResultado;

    @FXML
    public void initialize() {
        // Cargar las opciones del desplegable de actividades
        cmbActividad.getItems().addAll("Taller JavaFX", "1º DAM", "2º DAM", "Torneo de Ajedrez", "Hackathon DAM");

        // El botón INSCRIBIRME inicia desactivado hasta aceptar condiciones
        btnInscribirme.setDisable(true);
    }

    @FXML
    private void cambiarCondiciones() {
        // Activa o desactiva el botón de inscripción según la casilla de condiciones
        btnInscribirme.setDisable(!chkCondiciones.isSelected());
    }

    @FXML
    private void inscribir() {
        String nombre = txtNombre.getText() != null ? txtNombre.getText().trim() : "";
        String actividad = cmbActividad.getValue();

        // 1. Validar nombre
        if (nombre.isEmpty()) {
            lblResultado.setText("Resultado: ⚠️ Debes introducir un nombre.");
            return;
        }

        // 2. Validar actividad
        if (actividad == null) {
            lblResultado.setText("Resultado: ⚠️️ Debes seleccionar una actividad.");
            return;
        }

        // 3. Validar turno
        String turno = "";
        if (rbManana.isSelected()) {
            turno = "Mañana";
        } else if (rbTarde.isSelected()) {
            turno = "Tarde";
        } else {
            lblResultado.setText("Resultado: ⚠️ Debes seleccionar un turno.");
            return;
        }

        // 4. Validar condiciones
        if (!chkCondiciones.isSelected()) {
            lblResultado.setText("Resultado: ⚠️ Debes aceptar las condiciones.");
            return;
        }

        String necesitaEquipo = chkEquipo.isSelected() ? "Sí" : "No";

        // Actualiza la etiqueta del resultado
        lblResultado.setText("¡Inscrito!\n"
                + "• Nombre: " + nombre + "\n"
                + "• Actividad: " + actividad + "\n"
                + "• Turno: " + turno + "\n"
                + "• Equipo: Ordenador con complementos." );
    }

    @FXML
    private void limpiar() {
        // Restablecer el campo de texto
        txtNombre.clear();

        // Desseleccionar ComboBox
        cmbActividad.getSelectionModel().clearSelection();
        cmbActividad.setValue(null);

        // Desseleccionar RadioButtons del turno
        if (grupoTurno.getSelectedToggle() != null) {
            grupoTurno.getSelectedToggle().setSelected(false);
        }

        // Desmarcar CheckBoxes
        chkEquipo.setSelected(false);
        chkCondiciones.setSelected(false);

        // Deshabilitar botón de inscripción y limpiar el mensaje
        btnInscribirme.setDisable(true);
        lblResultado.setText("Resultado:");
    }
}