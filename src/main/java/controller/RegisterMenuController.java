package controller;

import model.Email;

import java.util.regex.Matcher;

public class RegisterMenuController extends LoginOrRegisterMenuController {

    public void createNewUser(String username, String password, String passwordConfirm, String nickname, Email email) {
        // TODO
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
}
