package view;

import controller.MainMenuController;
import enums.regexes.MainMenuCommands;
import enums.Menu;
import model.App;
import model.User;

import java.util.Scanner;
import java.util.regex.Matcher;


public class MainMenu extends AppMenu {
    @Override
    public boolean check(String input, Scanner scanner) {
        Matcher matcher;

        MainMenuController controller = new MainMenuController();

        if ((matcher = MainMenuCommands.Logout.getMatcher(input)) != null) {
            User.setCurrentUser(null);
            App.setCurrentMenu(Menu.LoginMenu);
        } else if ((matcher = MainMenuCommands.GoToPreGameMenu.getMatcher(input)) != null) {
            App.setCurrentMenu(Menu.PreGameMenu);
        } else if ((matcher = MainMenuCommands.GoToProfileMenu.getMatcher(input)) != null) {
            App.setCurrentMenu(Menu.ProfileMenu);
        } else if ((matcher = MainMenuCommands.ShowCurrentMenu.getMatcher(input)) != null) {
            System.out.println("main menu");
        } else return false;
        return true;
    }
}