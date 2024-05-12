package view;

import enums.SecurityQuestion;

public abstract class LoginOrRegisterMenu extends AppMenu {
    public void showListOfSecurityQuestions() {
        for (SecurityQuestion securityQuestion : SecurityQuestion.values()) {
            System.out.println(securityQuestion);
        }
    }
}
