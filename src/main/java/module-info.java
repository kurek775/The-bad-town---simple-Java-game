module cz.vse.kurp03.adventura {
    requires javafx.controls;
    requires javafx.fxml;

    opens cz.vse.kurp03.adventura.main to javafx.fxml;
    exports cz.vse.kurp03.adventura.main;
}