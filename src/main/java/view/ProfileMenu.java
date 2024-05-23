package view;

import enums.ProfileMenuCommands;
import model.User;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends AppMenu {
    @Override
    public boolean check(String input, Scanner scanner) {
        Matcher matcher;


    if((matcher = ProfileMenuCommands.ChangeUsername.getMatcher(input)) != null){
        // TODO: learn (File and Directory)working in Java.
        File userfile = new File("..\\model\\users\\" + User.getCurrentUser().getName() + "txt");

    }
    else if((matcher = ProfileMenuCommands.ChangeNickname.getMatcher(input)) != null) {

    }
    else if((matcher = ProfileMenuCommands.ChangeEmail.getMatcher(input)) != null){

    }
    else if((matcher = ProfileMenuCommands.ChangePassword.getMatcher(input)) != null){


        } else return false;
        return true;
    }
}