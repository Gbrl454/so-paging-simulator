package br.gbrl.sopagingsimulator.controllers;

import br.gbrl.sopagingsimulator.Cache;
import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.Views;
import br.gbrl.sopagingsimulator.enums.Algorithms;
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
        if (cbFifo.isSelected()) Cache.algorithmSimulationRequest.add(Algorithms.FIFO);
        if (cbNru.isSelected()) Cache.algorithmSimulationRequest.add(Algorithms.NRU);
        if (cbClock.isSelected()) Cache.algorithmSimulationRequest.add(Algorithms.CLOCK);
        Main.changeViews(Views.SIMULATION_VIEW);
    }
}