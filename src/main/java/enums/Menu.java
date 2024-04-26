package enums;

import view.*;

import java.util.Scanner;

public enum Menu {
    LoginMenu(new LoginMenu()),
    MainMenu(new MainMenu()),
    ShopMenu(new ShopMenu()),
    GameMenu(new GameMenu()),
    ProfileMenu(new ProfileMenu()),
    Exit(new ExitMenu());
    private AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
    }
}
