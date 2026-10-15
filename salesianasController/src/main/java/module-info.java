module com.salesianas.controller {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.salesianas.controller to javafx.fxml;
    exports com.salesianas.controller;
}