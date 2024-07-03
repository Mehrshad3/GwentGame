package model;

import enums.Menu;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Stage stage;
    private static Scene preGameMenu;
    private static Scene mainMenu;
    private static Scene loginMenu;
    private static App appObject = null;
    private static Menu currentMenu = Menu.LoginMenu;

    static {
        try {
            LOGGER.addHandler(new FileHandler(".GWENT/log.xml"));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Couldn't open the file to write logs to", e);
        }
    }

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

    public GameStatus getGaming() {
        return gaming;
    }

    public void setGaming(GameStatus gaming) {
        this.gaming = gaming;
    }

    public static Scene getPreGameMenu() {
        return preGameMenu;
    }

    public static void setPreGameMenu(Scene preGameMenu) {
        App.preGameMenu = preGameMenu;
    }

    public static Scene getMainMenu() {
        return mainMenu;
    }

    public static void setMainMenu(Scene mainMenu) {
        App.mainMenu = mainMenu;
    }

    public static Scene getLoginMenu() {
        return loginMenu;
    }

    public static void setLoginMenu(Scene loginMenu) {
        App.loginMenu = loginMenu;
    }
}
