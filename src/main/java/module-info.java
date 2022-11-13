module cz.vse.kurp03.adventura {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens cz.vse.kurp03.adventura.main to javafx.fxml, javafx.web;
    exports cz.vse.kurp03.adventura.main;
}