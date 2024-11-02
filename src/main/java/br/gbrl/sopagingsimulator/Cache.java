package br.gbrl.sopagingsimulator;

import br.gbrl.sopagingsimulator.enums.Algorithms;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    public static List<Algorithms> algorithmSimulationRequest = new ArrayList<>();
    public static Integer qPags = 0;
    public static List<Integer> pages = new ArrayList<>();
    private static boolean App = false;

    public static boolean isApp() {
        return App;
    }

    public static void setApp(boolean app) {
        App = app;
    }
}
