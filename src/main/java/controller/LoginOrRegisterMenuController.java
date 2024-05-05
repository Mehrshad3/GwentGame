package controller;

import enums.SecurityQuestion;

import java.io.PrintStream;
import java.util.regex.Matcher;

abstract class LoginOrRegisterMenuController extends MenuController {
    void showListOfSecurityQuestions(PrintStream printStream) {
        for (SecurityQuestion securityQuestion : SecurityQuestion.values()) {
            printStream.println(securityQuestion);
        }
    }

    protected abstract boolean getSecurityQuestionAnswer(Matcher matcher);

    protected String generateRandomPassword() {
        return null; //TODO
    }
}
