module com.salesianas.calculadoracss {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.salesianas.calculadoracss to javafx.fxml;
    exports com.salesianas.calculadoracss;
}