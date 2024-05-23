package view;

import enums.RegisterMenuCommands;
import enums.SemiRegexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu extends AppMenu {
    public void check(Scanner scanner) {
        String input;
        input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = RegisterMenuCommands.REGISTER_USER.getMatcher(input)) != null) {
            createUser(matcher, false);
        } else if ((matcher = RegisterMenuCommands.REGISTER_USER_WITH_RANDOM_PASSWORD.getMatcher(input)) != null) {
            createUser(matcher, true);
        } else System.out.println("Invalid Command");
    }

    private void createUser(Matcher matcher, boolean randomPassword) {
        boolean invalidInputs = true;
        String username = matcher.group("username");
        String email = matcher.group("email");
        String password;
        if (!username.matches(SemiRegexes.VALID_USERNAME.regex)) {
            System.out.println("Username entered is not valid.");
        } else if (!email.matches(SemiRegexes.VALID_EMAIL_ADDRESS.regex)) {
            System.out.println("Email entered is not valid.");
        } else invalidInputs = false;
        if (invalidInputs) return;
        if (randomPassword) {
            // TODO: generate a random password
        } else {
            invalidInputs = true;
            if (!(password = matcher.group("password")).matches(SemiRegexes.VALID_PASSWORD.regex)) {
                System.out.println("Password entered is not valid");
            } else if (!password.matches(SemiRegexes.STRONG_PASSWORD_REGEX.regex)) {
                System.out.println("Password entered is not strong enough.");
            } else if (!password.equals(matcher.group("passwordConfirm"))) {
                System.out.println("Password entered doesn't match password confirm.");
            } else invalidInputs = false;
        }
        if (invalidInputs) return;
        if (false /* TODO: User.getUserByName(username) != null */) askUserForRandomUsername();
        // TODO: User.createUser(username, email, password);
    }

    private void askUserForRandomUsername() {
        // TODO: implement this.
    }
}