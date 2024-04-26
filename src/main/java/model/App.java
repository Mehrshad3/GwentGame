package model;

import enums.Menu;

public class App {
    private static Menu currentMenu = Menu.LoginMenu;


    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }
}
