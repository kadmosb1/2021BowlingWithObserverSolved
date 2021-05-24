module BowlingWithObserverSolved {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens nl.hhs to javafx.fxml;

    exports nl.hhs;
}