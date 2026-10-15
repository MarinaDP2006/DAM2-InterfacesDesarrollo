package com.salesianas.entradascinecss;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EntradasCineController {
    // Controles FXML
    @FXML private ComboBox<String> cbPelicula;
    @FXML private Button btnMenos;
    @FXML private Button btnMas;
    @FXML private Label lblEntradas;

    @FXML private ToggleGroup tgSesion;
    @FXML private RadioButton rbSesion1;
    @FXML private RadioButton rbSesion2;
    @FXML private RadioButton rbSesion3;

    @FXML private CheckBox chkPalomitas;
    @FXML private CheckBox chkPremium;

    @FXML private Button btnCalcular;
    @FXML private Button btnLimpiar;
    @FXML private Button btnConfirmar;

    @FXML private TextArea txtResumen;
    @FXML private Label lblTotal;
    @FXML private Label lblEstado;

    // Variables de los precios
    private static final double PRECIO_BASE_ENTRADA = 7.50;
    private static final double PRECIO_PALOMITAS = 5.50;
    private static final double PRECIO_ASIENTO_PREMIUM = 2.00;

    // Estado inicial entradas
    private int numEntradas = 1;

    @FXML
    public void initialize() {
        // Cargar catálogo de películas
        cbPelicula.getItems().addAll(
                "Avengers: Doomsday",
                "Dune: Parte Tres - Messiah",
                "Terrifier 4",
                "Los Juegos del Hambre: Amanecer en la cosecha",
                "Noche Violenta 2",
                "Narnia",
                "La Leyenda de Aang: el último Airbender",
                "Tenzing: Carrera al Everest"
        );

        // Control de número de entradas (+/-) para no bajar de 1
        btnMenos.setOnAction(e -> {
            if (numEntradas > 1) {
                numEntradas--;
                lblEntradas.setText(String.valueOf(numEntradas));
                validarFormulario();
            }
        });

        btnMas.setOnAction(e -> {
            numEntradas++;
            lblEntradas.setText(String.valueOf(numEntradas));
            validarFormulario();
        });

        // LISTENERS: Detectar cuando cambia la película o la sesión para validar
        cbPelicula.valueProperty().addListener((obs, oldVal, newVal) -> validarFormulario());
        tgSesion.selectedToggleProperty().addListener((obs, oldVal, newVal) -> validarFormulario());

        // EVENT HANDLERS: Conectar acciones de los botones principales
        btnCalcular.setOnAction(e -> calcularPrecio());
        btnLimpiar.setOnAction(e -> limpiarFormulario());
        btnConfirmar.setOnAction(e -> confirmarReserva());

        // Validar el estado inicial al arrancar
        validarFormulario();
    }

    // Valida si la reserva cumple los requisitos mínimos para habilitar CONFIRMAR
    private void validarFormulario() {
        boolean peliculaSeleccionada = cbPelicula.getValue() != null;
        boolean sesionSeleccionada = tgSesion.getSelectedToggle() != null;
        boolean valido = peliculaSeleccionada && sesionSeleccionada && numEntradas >= 1;

        btnConfirmar.setDisable(!valido);

        if (valido) {
            lblEstado.setText("¡Datos completos! Puedes calcular el precio o confirmar.");
            lblEstado.getStyleClass().setAll("alert", "alert-success");
        } else {
            lblEstado.setText("¡SELECCIONA PELICULA Y HORA PARA SEGUIR!");
            lblEstado.getStyleClass().setAll("alert", "alert-warning");
        }
    }

    // Calcula el total de la reserva y da un resumen
    private void calcularPrecio() {
        if (cbPelicula.getValue() == null || tgSesion.getSelectedToggle() == null) {
            lblEstado.setText("Rellena los datos obligatorios antes de calcular.");
            lblEstado.getStyleClass().setAll("alert", "alert-danger");
            return;
        }

        RadioButton sesionSeleccionada = (RadioButton) tgSesion.getSelectedToggle();
        String horaSesion = sesionSeleccionada.getText();
        String pelicula = cbPelicula.getValue();

        // Cálculo de costes
        double costeEntradas = numEntradas * PRECIO_BASE_ENTRADA;
        double costePremium = chkPremium.isSelected() ? (numEntradas * PRECIO_ASIENTO_PREMIUM) : 0.0;
        double costePalomitas = chkPalomitas.isSelected() ? PRECIO_PALOMITAS : 0.0;
        double total = costeEntradas + costePremium + costePalomitas;

        // Datos para la info después de reservar
        StringBuilder resumen = new StringBuilder();
        resumen.append("Película: ").append(pelicula).append("\n");
        resumen.append("Hora: ").append(horaSesion).append("\n");
        resumen.append("Entradas: ").append(numEntradas).append(" x ").append(String.format("%.2f €", PRECIO_BASE_ENTRADA)).append("\n");

        if (chkPremium.isSelected()) {
            resumen.append("Extra Asiento Premium: +").append(String.format("%.2f €", costePremium)).append("\n");
        }
        if (chkPalomitas.isSelected()) {
            resumen.append("Extra Palomitas + Bebida: +").append(String.format("%.2f €", costePalomitas)).append("\n");
        }

        txtResumen.setText(resumen.toString());
        lblTotal.setText(String.format("TOTAL: %.2f €", total));
    }

    // Muestra una ventana para confirmar la reserva.
    private void confirmarReserva() {
        calcularPrecio(); // Asegurar actualización antes de confirmar
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reserva Confirmada");
        alert.setHeaderText("¡Reserva realizada con éxito!");
        alert.showAndWait();
    }

    private void limpiarFormulario() {
        cbPelicula.getSelectionModel().clearSelection();
        tgSesion.selectToggle(null);
        numEntradas = 1;
        lblEntradas.setText("1");
        chkPalomitas.setSelected(false);
        chkPremium.setSelected(false);
        txtResumen.clear();
        lblTotal.setText("TOTAL: 0,00 €");
        validarFormulario();
    }
}