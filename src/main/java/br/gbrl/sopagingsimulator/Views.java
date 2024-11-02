package br.gbrl.sopagingsimulator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public enum Views {
    INITIAL_VIEW("initial", "Inicio"), SIMULATION_VIEW("simulation", "Simulação");

    private final String name;
    private final String title;

    Views(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Scene getScene() {
        Scene scene = null;
        try {
            FXMLLoader fxml = new FXMLLoader(Main.class.getResource("views/" + name + "-view.fxml"));
            scene = new Scene(fxml.load());
        } catch (IOException e) {
            Main.showErrorPopup("Erro ao selecionar nova tela.", e.getMessage());
            Cache.setApp(false);

        }
        return scene;
    }
}
