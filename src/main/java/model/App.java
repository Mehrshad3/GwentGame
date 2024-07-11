package model;

import controller.ClientController;
import enums.Menu;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.game.graphics.LeaderBoardGraphic;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Stage stage;
    private static Stage waitStage;
    private static Scene preGameMenu;
    private static Scene mainMenu;
    private static Scene loginMenu;
    private static LeaderBoardGraphic leaderBoardGraphic;
    private static Scene gameMenu;
    private static App appObject = null;
    private static Menu currentMenu = Menu.LoginMenu;
    private static final Object lock = new Object();
    private static ClientController clientController;

    static {
        try {
            LOGGER.addHandler(new FileHandler(".GWENT/log.xml"));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Couldn't open the file to write logs to", e);
        }
    }

    private ObservableGameStatus gaming;

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

    public static ClientController getClientController() {
        return clientController;
    }

    public static void setClientController(ClientController clientController) {
        App.clientController = clientController;
    }

    public static Scene getGameMenu() {
        return gameMenu;
    }

    public static void setGameMenu(Scene gameMenu) {
        App.gameMenu = gameMenu;
    }

    public ObservableGameStatus getGaming() {
        return gaming;
    }

    public void setGaming(ObservableGameStatus gaming) {
        this.gaming = gaming;
    }

    public static Stage getWaitStage() {
        return waitStage;
    }

    public static void setWaitStage(Stage waitStage) {
        App.waitStage = waitStage;
    }
    public static Object getLock(){
        return lock;
    }

    public static LeaderBoardGraphic getLeaderBoardGraphic() {
        return leaderBoardGraphic;
    }

    public static void setLeaderBoardGraphic(LeaderBoardGraphic leaderBoardGraphic) {
        App.leaderBoardGraphic = leaderBoardGraphic;
    }
}
