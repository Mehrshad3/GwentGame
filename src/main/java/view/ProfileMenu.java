package view;

import enums.ProfileMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends AppMenu{
    @Override
    public void check(Scanner scanner) {
    String input;
    input=scanner.nextLine();
        Matcher matcher;


    if(ProfileMenuCommands.Username.getMatcher(input) != null){

    }
    else if(ProfileMenuCommands.Nickname.getMatcher(input) != null) {

    }
    else if(ProfileMenuCommands.Email.getMatcher(input) != null){

    }
    else if(ProfileMenuCommands.Password.getMatcher(input) != null){

    }
    else{
        System.out.println("Invalid Command!");
    }

    }
}
