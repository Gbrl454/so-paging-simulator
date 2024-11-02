package br.gbrl.sopagingsimulator.controllers;

import br.gbrl.sopagingsimulator.Cache;
import br.gbrl.sopagingsimulator.Main;
import br.gbrl.sopagingsimulator.Views;
import br.gbrl.sopagingsimulator.algorithms.Aging;
import br.gbrl.sopagingsimulator.enums.Algorithms;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

public class InitialController {
    @FXML
    private CheckBox cbNru;

    @FXML
    private CheckBox  cbAging;

    @FXML
    private CheckBox cbFifo;

    @FXML
    private CheckBox cbClock;

    @FXML
    private TextArea txtPages;

    @FXML
    private TextField txtQPag;

    private static Integer getNumber(String texto) {
        if (texto == null || !texto.matches("\\d+"))
            Main.showErrorPopup("Texto não númerico!", "O texto informado não é numérico.");
        else return Integer.parseInt(texto);
        return null;
    }

    @FXML
    protected void initialize() {
        Main.addOnChageScreenListener((newScreen) -> {
            if (Main.CURRENT_VIEW.equals(Views.INITIAL_VIEW)) {
                Cache.algorithmSimulationRequest.clear();
                cbNru.setSelected(false);
                cbAging.setSelected(false);
                cbFifo.setSelected(false);
                cbClock.setSelected(false);
                txtPages.setText("5 1 5 9 5 5 9 9 5 3 9 5 7 7 3 6 9 4 7 9 3 7 4 8 5 3 4 6 2 7 8 2 1 4 1 4 3 3 7 7 3 7 2 6 7 4 4 6 2");
                txtQPag.setText("5");
                Cache.qPags = 0;
                Cache.pages = new ArrayList<>();
            }
        });
    }

    @FXML
    protected void onSimulateButtonClick() {
        if (cbNru.isSelected()) Cache.algorithmSimulationRequest.add(Algorithms.NRU);
        if (cbAging.isSelected()) Cache.algorithmSimulationRequest.add(Algorithms.AGING);
        if (cbFifo.isSelected()) Cache.algorithmSimulationRequest.add(Algorithms.FIFO);
        if (cbClock.isSelected()) Cache.algorithmSimulationRequest.add(Algorithms.CLOCK);
        if(Cache.algorithmSimulationRequest.isEmpty()){
            Main.showErrorPopup("Campo inválido!", "Informe os algoritimos.");
            return;
        }

        Cache.pages = Arrays.stream(txtPages.getText().split(" ")).map(it -> {
            Integer i = getNumber(it);
            if (i == null) {
                String msg = "A página deve ser númerica.";
                Main.showErrorPopup("Campo inválido!", msg);
                throw new RuntimeException(msg);
            }
            return i;
        }).toList();
        if (Cache.pages.isEmpty()) {
            Main.showErrorPopup("Campo inválido!", "Informe as páginas.");
            return;
        }

        Cache.qPags = getNumber(txtQPag.getText());
        if (Cache.qPags <= 0) {
            Main.showErrorPopup("Campo inválido!", "O número de páginas deve maior que 0.");
            return;
        }

        Main.changeViews(Views.SIMULATION_VIEW);
    }

}