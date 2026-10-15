module com.salesianas.manageralumnos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.salesianas.manageralumnos to javafx.fxml;
    opens com.salesianas.manageralumnos.controller to javafx.fxml;
    exports com.salesianas.manageralumnos;
}