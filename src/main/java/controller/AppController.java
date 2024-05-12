package controller;

import enums.Menu;
import model.App;
import model.User;
import view.AppView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AppController {
    private static final HashMap<Menu, HashSet<Menu>> possibleDownwardMenuChanges = new HashMap<>();
    private static AppController appControllerObject = null;

    static {
        possibleDownwardMenuChanges.put(Menu.Exit, new HashSet<>(List.of(Menu.LoginMenu, Menu.RegisterMenu)));
        possibleDownwardMenuChanges.put(Menu.LoginMenu, new HashSet<>(List.of(Menu.RegisterMenu, Menu.MainMenu)));
        possibleDownwardMenuChanges.put(Menu.RegisterMenu, new HashSet<>(List.of(Menu.LoginMenu, Menu.MainMenu)));
        possibleDownwardMenuChanges.put(Menu.MainMenu, new HashSet<>(List.of(Menu.ProfileMenu, Menu.PreGameMenu)));
        possibleDownwardMenuChanges.put(Menu.ProfileMenu, new HashSet<>(0));
        possibleDownwardMenuChanges.put(Menu.PreGameMenu, new HashSet<>(List.of(Menu.GameMenu)));
        possibleDownwardMenuChanges.put(Menu.GameMenu, new HashSet<>(0));
    }

    private AppController() {
    }

    public static AppController getAppController() {
        if (appControllerObject == null) appControllerObject = new AppController();
        return appControllerObject;
    }

    public void enterMenu(Menu menuToEnter) {
        Menu currentMenu = App.getCurrentMenu();
        if (menuToEnter == Menu.GameMenu) menuToEnter = Menu.PreGameMenu; // We go to that menu before GameMenu.

        if (menuToEnter == currentMenu) {
            AppView.getAppViewObject().alertNoMenuChange();
        } else if (menuToEnter == currentMenu.parentMenu) {
            AppView.getAppViewObject().alertDownwardMenuChange();
        } else if (!isGoingDownwardPossible(currentMenu, menuToEnter))
            AppView.getAppViewObject().alertInvalidMenuChange();
        else if (User.getCurrentUser() == null && menuToEnter == Menu.MainMenu) {
            AppView.getAppViewObject().alertUserNotLoggedIn();
        } // TODO: check 22 soldiers and 10 special cards...
        else {
            App.setCurrentMenu(menuToEnter);
        }
    }

    public void exitMenu() {
        App.setCurrentMenu(App.getCurrentMenu().parentMenu);
    }

    private boolean isGoingDownwardPossible(Menu currentMenu, Menu menuToEnter) {
        return possibleDownwardMenuChanges.get(currentMenu).contains(menuToEnter);
    }
}