package controller;

import model.App;
import model.User;
import view.RegisterMenu;

import java.util.regex.Matcher;

public class RegisterMenuController extends LoginOrRegisterMenuController {

    public void createNewUser(String username, String password, String passwordConfirm, String nickname, String email) {
        boolean randomPassword = passwordConfirm == null;
        boolean invalidInputs = true;
        RegisterMenu registerMenu = (RegisterMenu) App.getCurrentMenu().getAppMenu();
        // Checks whether username and email are valid
        if (!Validator.getValidator().validateUsername(username)) registerMenu.alertInvalidUsername();
        else if (!Validator.getValidator().validateEmail(email)) registerMenu.alertInvalidEmail();
        else invalidInputs = false;
        if (invalidInputs) return;
        // Checks whether the username is duplicate
        if (Validator.getValidator().isUsernameDuplicate(username) && !askUserForRandomUsername()) return;
        // Checks password
        if (randomPassword) {
            if ((password = askUserForRandomPassword()) == null)
                return; // If user doesn't want to generate password, return.
        }
        // Checks whether password is Okay.
        else {
            invalidInputs = true;
            if (!Validator.getValidator().validatePassword(password)) registerMenu.alertInvalidPassword();
            else if (Validator.getValidator().isPasswordWeak(password)) registerMenu.alertWeakPassword();
            else if (!password.equals(passwordConfirm)) registerMenu.alertPasswordConfirmDoesNotMatch();
            else invalidInputs = false;
        }
        if (invalidInputs) return;
        User.setCurrentUser(new User(username, password, email, nickname,"",""));//TODO
        registerMenu.alertSuccessfulUserCreation(nickname, username);
    }

    private boolean checkAndHandleIfUserExists(String username) {
        return false; //TODO
    }

    private String randomlyGenerateSimilarUsername() {
        return null; //TODO
    }

    @Override
    protected boolean getSecurityQuestionAnswer(Matcher matcher) {
        // TODO
        return true;
    }

    private boolean askUserForRandomUsername() {
        System.out.println("Your username is duplicate.");
        // TODO: implement this.
        return false;
    }

    private String askUserForRandomPassword() {
        while (true) {
            String password = generateRandomPassword();
            int userConsent = ((RegisterMenu) App.getCurrentMenu().getAppMenu()).confirmRandomGeneratedPassword(password);
            switch (userConsent) {
                case 0:
                    return password;
                case 2:
                    return null;
                case 1:
                    break;
                default:
                    throw new RuntimeException("Code returned by confirmRandomGeneratedPassword must be 0, 1, or 2");
            }
        }
    }
}
