package view;

import controller.AppController;
import enums.GeneralMenuCommands;
import enums.Menu;
import model.App;

import java.util.Scanner;
import java.util.regex.Matcher;

public class AppView {
    private static AppView appViewObject = null;
    public final Scanner scanner = new Scanner(System.in);

    private AppView() {
    }

    public static AppView getAppViewObject() {
        if (appViewObject == null) appViewObject = new AppView();
        return appViewObject;
    }

    public void run() {
        Matcher matcher;
        do {
            String input = scanner.nextLine().trim();
            if (!App.getCurrentMenu().checkCommand(input, scanner)) {
                if ((matcher = GeneralMenuCommands.MENU_ENTER.getMatcher(input)) != null) {
                    AppController.getAppController().enterMenu(Menu.getMenuByName(matcher.group("menuName")));
                } else if (GeneralMenuCommands.MENU_EXIT.getMatcher(input) != null) {
                    AppController.getAppController().exitMenu();
                } else if (GeneralMenuCommands.SHOW_MENU.getMatcher(input) != null) {
                    System.out.println(App.getCurrentMenu().getMenuTitle());
                } else System.err.println("Invalid command.");
            }
        } while (App.getCurrentMenu() != Menu.Exit);
    }

    public void alertNoMenuChange() {
        System.err.println("Destination menu is the same as current menu.");
    }

    public void alertDownwardMenuChange() {
        System.err.println("'menu enter <menu_name>' is only used to go to lower menus.");
        System.err.println("Hint: You can use 'menu exit' to go to upper menus.");
    }

    public void alertInvalidMenuChange() {
        System.err.println("You can only go to menus directly below the current menu in the graph not shown below.");
        System.err.println("For example, you can go from 'main' menu to 'Profile' and 'Game' menus, but you can't go" +
                "Directly from 'Profile' menu to 'game' menu.");
    }

    public void alertUserNotLoggedIn() {
        System.err.println("You have to login/register before you enter 'view.Main' menu.");
    }
}