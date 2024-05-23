package controller;

import enums.Menu;
import model.App;
import model.User;

public class MainMenuController extends MenuController {

    public void goToProfileMenu(){
        App.setCurrentMenu(Menu.ProfileMenu);
    }

    public void logOut() {
        App.setCurrentMenu(Menu.LoginMenu);
        User.setCurrentUser(null);
    }

    public void goToGameMenu() {
        App.setCurrentMenu(Menu.PreGameMenu);
    }
}
