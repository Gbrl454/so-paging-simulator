package br.gbrl.sopagingsimulator;

public enum Views {
    HELLO_VIEW("hello-view");

    private final String name;

    Views(String name) {
        this.name = name;
    }

    public String getPath() {
        return "views/" + name + ".fxml";
    }
}
