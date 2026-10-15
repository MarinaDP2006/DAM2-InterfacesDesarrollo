package com.salesianas.formularioeventos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class formularioController {

    // VARIABLES
    @FXML private TextField txtNombre;
    @FXML private Slider sliderValor;
    @FXML private Label lblValorSlider;
    @FXML private Label lblResultado;
    @FXML private Button btnGuardar; // BOTÓN GUARDAR
    @FXML private Slider sliderExp;     // SEGUNDO SLIDER PARA EXPERIENCIA
    @FXML private Label lblExp;         // LABEL PARA MOSTRAR EXPERIENCIA
    @FXML private TextField txtApellidos; // APELLIDOS
    @FXML private Button btnLimpiar; // BOTÓN LIMPIAR

    // Iniciar
    @FXML
    public void initialize() {
        sliderValor.valueProperty().addListener((obs, oldV, newV) -> {
            lblValorSlider.setText("Valor: " + newV.intValue());
        });
        // BINDING: DESHABILITAR GUARDAR SI EL NOMBRE ESTÁ VACÍO
        btnGuardar.disableProperty().bind(
                txtNombre.textProperty().isEmpty()
        );
        // LISTENER DEL SEGUNDO SLIDER (EXPERIENCIA)
        sliderExp.valueProperty().addListener((obs, oldV, newV) -> {
            lblExp.setText("Exp: " + newV.intValue()); // ACTUALIZAR LABEL EN TIEMPO REAL
        });
        // BINDING: DESHABILITAR GUARDAR SI NOMBRE O APELLIDOS ESTÁN VACÍOS
        btnGuardar.disableProperty().bind(txtNombre.textProperty().isEmpty().or(txtApellidos.textProperty().isEmpty())
        );
    }

    // Metodo para guardar el valor y el nombre del formulario
    @FXML
    private void onGuardarClick() {
        String nombre = txtNombre.getText(); // se guarda el nombre
        int valor = (int) sliderValor.getValue(); // se guarda el valor al mover la rueda
        String apellidos = txtApellidos.getText(); // APELLIDOS
        int exp = (int) sliderExp.getValue(); // EXPERIENCIA
        lblResultado.setText("Nombre completo: " + nombre + " " + apellidos + " | Valor: " + valor + " | Exp: " + exp);
    }

    @FXML
    private void limpiar() {
        // LIMPIAR NOMBRE
        txtNombre.clear();

        // REINICIAR SLIDER PRINCIPAL
        sliderValor.setValue(5);
        lblValorSlider.setText("Valor: 5");

        // LIMPIAR RESULTADO
        lblResultado.setText("");

        // SI TIENE APELLIDOS
        txtApellidos.clear();

        // SI TIENE SEGUNDO SLIDER
        sliderExp.setValue(2);
        lblExp.setText("Exp: 2");
    }
}