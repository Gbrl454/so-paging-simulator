module br.gbrl.sopagingsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;

    opens br.gbrl.sopagingsimulator to javafx.fxml;
    exports br.gbrl.sopagingsimulator;
    exports br.gbrl.sopagingsimulator.controllers;
    opens br.gbrl.sopagingsimulator.controllers to javafx.fxml;
}