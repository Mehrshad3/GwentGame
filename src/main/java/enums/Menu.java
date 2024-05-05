package enums;

import controller.*;
import model.GameStatus;
import view.*;

import java.util.Scanner;

public enum Menu {
    LoginMenu(new LoginMenu(), new LoginMenuController()),
    RegisterMenu(new RegisterMenu(), new RegisterMenuController()),
    MainMenu(new MainMenu(), new MainMenuController()),
    PreGameMenu(new PreGameMenu(), new PreGameMenuController()),
    ProfileMenu(new ProfileMenu(), new ProfileMenuController()),
    GameMenu(new GameMenu(), new GameController()),
    Exit(new ExitMenu(), null);
    private final MenuController menuController;
    private AppMenu menu;

    Menu(AppMenu menu, MenuController menuController) {
        this.menu = menu;
        this.menuController = menuController;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
    }

    public AppMenu getMenu() {
        return this.menu;
    }

    public MenuController getMenuController() {
        return menuController;
    }
}
