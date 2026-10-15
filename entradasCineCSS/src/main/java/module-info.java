module com.salesianas.entradascinecss {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.salesianas.entradascinecss to javafx.fxml;
    exports com.salesianas.entradascinecss;
}