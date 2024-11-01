package br.gbrl.sopagingsimulator;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends javafx.application.Application {
    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    private static final String TITLE_BASE = "Unifor - Sistemas Operacionais - ";
    public static Views currentView =null;
    private static Stage stage;

    public static void showErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void changeViews(Views view) {
        currentView = view;
        stage.setScene(currentView.getScene());
        stage.setTitle(TITLE_BASE + currentView.getTitle());
        stage.setX((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - currentView.getWidth()) / 4);
        stage.setY((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - currentView.getHeight()) / 2);
        notfifyListeners(currentView);
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
        stage = stageS;

        Cache.setApp(true);
        stage.setOnCloseRequest(windowEvent -> {
            Cache.setApp(false);
            stage.close();
        });


        changeViews(Views.INITIAL_VIEW);
        stageS.show();
    }

    public interface OnChangeScreen {
        void onScreenChanged(Views newScreen);
    }
}