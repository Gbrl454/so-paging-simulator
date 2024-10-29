package br.gbrl.sopagingsimulator;

public class Cache {
    // --- Geral --- //
    private static boolean App = false;

    public static boolean isApp() {
        return App;
    }

    public static void setApp(boolean app) {
        App = app;
    }
}
