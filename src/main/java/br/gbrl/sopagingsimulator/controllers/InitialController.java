package br.gbrl.sopagingsimulator.controllers;

import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.Views;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class InitialController {
    @FXML
    private CheckBox cbFifo;

    @FXML
    private CheckBox cbNru;

    @FXML
    private CheckBox cbClock;

    @FXML
    protected void onSimulateButtonClick() {
        Main.changeViews(Views.SIMULATION_VIEW);
    }
}