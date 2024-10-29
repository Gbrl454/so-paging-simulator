package br.gbrl.sopagingsimulator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public enum Views {
    INITIAL_VIEW(25, "initial", "Inicio"),
    SIMULATION_VIEW(75, "simulation", "Simulação");

    private final int value;
    private final String name;
    private final String title;

    Views(int value, String name, String title) {
        this.value = value;
        this.name = name;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getWidth() {
        return 16.0 * value;
    }

    public double getHeight() {
        return 9.0 * value;
    }

    public Scene getScene() {
        Scene scene = null;
        try {
            FXMLLoader fxml = new FXMLLoader(Main.class.getResource("views/" + name + "-view.fxml"));
            scene = new Scene(fxml.load(), getWidth(), getHeight());
        } catch (IOException e) {
            Main.showErrorPopup("Erro ao selecionar nova tela.", e.getMessage());
            Cache.setApp(false);

        }
        return scene;
    }
}
