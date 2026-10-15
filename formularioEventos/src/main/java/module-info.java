module com.salesianas.formularioeventos {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.salesianas.formularioeventos to javafx.fxml, javafx.controls;
    exports com.salesianas.formularioeventos;
}