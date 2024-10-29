package br.gbrl.sopagingsimulator;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends javafx.application.Application {
    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    private static final String TITLE_BASE = "Unifor - Sistemas Operacionais - ";
    private static Stage stage;

    public static void showErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void changeViews(Views view) {
        stage.setScene(view.getScene());
        stage.setTitle(TITLE_BASE + view.getTitle());
        stage.setX((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - view.getWidth()) / 4);
        stage.setY((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - view.getHeight()) / 2);
        notfifyListeners(view);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void notfifyListeners(Views newScreen) {
        for (OnChangeScreen l : listeners) l.onScreenChanged(newScreen);
    }

    @Override
    public void start(Stage stageS) {
        stage = stageS;

        Cache.setApp(true);
        stage.setOnCloseRequest(windowEvent -> {
            Cache.setApp(false);
            stage.close();
        });

        stageS.setTitle(TITLE_BASE + Views.INITIAL_VIEW.getTitle());
        stageS.setScene(Views.INITIAL_VIEW.getScene());
        stageS.show();
    }

    public interface OnChangeScreen {
        void onScreenChanged(Views newScreen);
    }
}