package com.salesianas.calculadoracss;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CalculadorPedidosController {

    @FXML private ComboBox<String> cbProducto;
    @FXML private Spinner<Integer> spCantidad;
    @FXML private CheckBox chkEnvioUrgente;
    @FXML private CheckBox chkRecogidaTienda;
    @FXML private Button btnCalcular;
    @FXML private Button btnLimpiar;
    @FXML private Label lblResultado;

    private static final double PRECIO_ENVIO = 4.99;

    @FXML
    public void initialize() {
        // 1. Cargar productos en el ComboBox
        cbProducto.getItems().addAll(
                "Portátil - 800.00 €",
                "Ratón - 25.00 €",
                "Teclado - 45.00 €"
        );

        // 2. Configurar el Spinner de cantidad (min, max, valorInicial)
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 1);
        spCantidad.setValueFactory(valueFactory);

        // 3. Listener: Deshabilitar "Envío urgente" si se marca "Recogida en tienda"
        chkRecogidaTienda.selectedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                chkEnvioUrgente.setSelected(false);
                chkEnvioUrgente.setDisable(true);
            } else {
                chkEnvioUrgente.setDisable(false);
            }
        });

        // 4. Listener: Deshabilitar el botón CALCULAR si la cantidad no es válida (<= 0) o no hay producto
        spCantidad.valueProperty().addListener((obs, oldValue, newValue) -> validarFormulario());
        cbProducto.valueProperty().addListener((obs, oldValue, newValue) -> validarFormulario());

        // 5. Manejadores de eventos (Event Handlers) para los botones
        btnCalcular.setOnAction(event -> ejecutarCalculo());
        btnLimpiar.setOnAction(event -> limpiarInterfaz());

        // Estado inicial de validación
        validarFormulario();
    }

    private void validarFormulario() {
        boolean cantidadValida = spCantidad.getValue() != null && spCantidad.getValue() > 0;
        boolean productoSeleccionado = cbProducto.getValue() != null;

        // El botón solo estará activo si ambas condiciones se cumplen
        btnCalcular.setDisable(!(cantidadValida && productoSeleccionado));
    }

    private void ejecutarCalculo() {
        double precioBase = obtenerPrecioProducto(cbProducto.getValue());
        int cantidad = spCantidad.getValue();
        double total = precioBase * cantidad;

        if (chkEnvioUrgente.isSelected() && !chkEnvioUrgente.isDisable()) {
            total += PRECIO_ENVIO;
        }

        lblResultado.setText(String.format("TOTAL: %.2f €", total));
    }

    private double obtenerPrecioProducto(String producto) {
        if (producto == null) return 0.0;
        if (producto.contains("Portátil")) return 800.00;
        if (producto.contains("Ratón")) return 25.00;
        if (producto.contains("Teclado")) return 45.00;
        return 0.0;
    }

    private void limpiarInterfaz() {
        cbProducto.getSelectionModel().clearSelection();
        spCantidad.getValueFactory().setValue(1);
        chkEnvioUrgente.setSelected(false);
        chkEnvioUrgente.setDisable(false);
        chkRecogidaTienda.setSelected(false);
        lblResultado.setText("TOTAL: 0.00 €");
        validarFormulario();
    }
}