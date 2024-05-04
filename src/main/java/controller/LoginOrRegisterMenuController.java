package controller;

import enums.SecurityQuestion;

import java.io.PrintStream;

abstract class LoginOrRegisterMenuController {
    void showListOfSecurityQuestions(PrintStream printStream) {
        for (SecurityQuestion securityQuestion : SecurityQuestion.values()) {
            printStream.println(securityQuestion);
        }
    }

    protected abstract boolean getSecurityQuestionAnswer();

    protected String generateRandomPassword() {
        return null; //TODO
    }
}
