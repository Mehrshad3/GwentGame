package model;

import enums.Menu;

public class App {
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

    public GameStatus getGaming() {
        return gaming;
    }

    public void setGaming(GameStatus gaming) {
        this.gaming = gaming;
    }
}
