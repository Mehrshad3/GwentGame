package controller;

import java.util.regex.Matcher;

public class LoginMenuController extends LoginOrRegisterMenuController {

    public void login(String username, String password, Boolean stayLoggedIn) {
        // TODO
    }

    public void handleForgottenPassword(String username) {
        // TODO
    }

    @Override
    protected boolean getSecurityQuestionAnswer(Matcher matcher) {
        return false; //TODO
    }

    public void changePassword(String newPassword) {
        // TODO
    }
}
