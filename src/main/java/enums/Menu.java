package enums;

import controller.*;
import model.App;
import view.*;

import java.util.HashSet;
import java.util.Scanner;

public enum Menu {
    Exit(new ExitMenu(), null, null, "Exit Menu"),
    LoginMenu(new LoginMenu(), new LoginMenuController(), Exit, "Login Menu"),
    RegisterMenu(new RegisterMenu(), new RegisterMenuController(), Exit, "Register Menu"),
    MainMenu(new MainMenu(), new MainMenuController(), Menu.LoginMenu, "view.Main Menu"),
    ProfileMenu(new ProfileMenu(), new ProfileMenuController(), Menu.MainMenu, "Profile Menu"),
    PreGameMenu(new PreGameMenu(), new PreGameMenuController(), Menu.MainMenu, "pre-Game Menu"),
    GameMenu(new GameMenu(), new GameController(), Menu.MainMenu, "Game Menu"),
    ;

    private final MenuController menuController;
    public final Menu parentMenu;
    private final String menuTitle;
//    private HashSet<Menu> childMenus = new HashSet<>(1);
    private AppMenu menu;

    Menu(AppMenu menu, MenuController menuController, Menu parentMenu, String menuTitle) {
        this.menu = menu;
        this.menuController = menuController;
        this.parentMenu = parentMenu;
        this.menuTitle = menuTitle;
    }

    public static Menu getMenuByName(String name) {
        switch (name.toLowerCase()) {
            case "exit" -> {
                return Menu.Exit;
            }
            case "login" -> {
                return Menu.LoginMenu;
            }
            case "register" -> {
                return Menu.RegisterMenu;
            }
            case "main" -> {
                return Menu.MainMenu;
            }
            case "profile" -> {
                return Menu.ProfileMenu;
            }
            case "game" -> {
                return Menu.GameMenu;
            }
            case "pre-game", "pregame", "pre game" -> {
                return Menu.PreGameMenu;
            }
            default -> throw new IllegalArgumentException("Menu name given, \"" + name + "\" is invalid.");
        }
    }

    public String getMenuTitle() {
        return this.menuTitle;
    }

    public boolean checkCommand(String input, Scanner scanner) {
        return this.menu.check(input, scanner);
    }

    public AppMenu getAppMenu() {
        return this.menu;
    }

    public MenuController getMenuController() {
        return menuController;
    }
}
