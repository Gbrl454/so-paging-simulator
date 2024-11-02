package br.gbrl.sopagingsimulator;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends javafx.application.Application {
    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    private static final String TITLE_BASE = "Unifor - Sistemas Operacionais - ";
    public static Views CURRENT_VIEW;
    private static Stage STAGE;

    public static void showErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void changeViews(Views view) {
        CURRENT_VIEW = view;
        STAGE.setScene(CURRENT_VIEW.getScene());
        STAGE.setTitle(TITLE_BASE + CURRENT_VIEW.getTitle());
        notfifyListeners(CURRENT_VIEW);
    }

    public static void addOnChageScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void notfifyListeners(Views newScreen) {
        for (OnChangeScreen l : listeners) l.onScreenChanged(newScreen);
    }

    @Override
    public void start(Stage stageS) {
        STAGE = stageS;
        stageS.setResizable(false);

        Cache.setApp(true);
        STAGE.setOnCloseRequest(windowEvent -> {
            Cache.setApp(false);
            STAGE.close();
        });

        changeViews(Views.INITIAL_VIEW);
        stageS.show();
    }

    public interface OnChangeScreen {
        void onScreenChanged(Views newScreen);
    }
}