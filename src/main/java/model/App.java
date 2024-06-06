package model;

import enums.Menu;
import javafx.stage.Stage;

public class App {
    private static Stage stage;
    private static Menu currentMenu = Menu.LoginMenu;


    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        App.stage = stage;
    }
}
