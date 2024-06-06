package model;

import enums.Menu;
import javafx.stage.Stage;

public class App {
    private static Stage stage;
    private static App appObject = null;
    private static Menu currentMenu = Menu.LoginMenu;
    private GameStatus gaming;

    private App() {
    }

    public static App getAppObject() {
        if (appObject == null) appObject = new App();
        return appObject;
    }

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
        public GameStatus getGaming () {
            return gaming;
        }

        public void setGaming (GameStatus gaming){
            this.gaming = gaming;
        }
    }
