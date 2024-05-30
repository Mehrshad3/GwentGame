package view;

import controller.LoginMenuController;
import enums.Menu;

import java.util.Scanner;

public class LoginMenu extends LoginOrRegisterMenu {
    @Override
    public boolean check(String input, Scanner scanner) {
        LoginMenuController loginMenuController = (LoginMenuController) Menu.LoginMenu.getMenuController();
        return false; // TODO
    }
}