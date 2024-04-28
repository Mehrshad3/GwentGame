package controller;

import enums.Menu;
import model.App;

public class MainMenuController {

    public void goToProfileMenu(){
        App.setCurrentMenu(Menu.ProfileMenu);
    }
}
