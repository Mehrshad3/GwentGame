package controller;

import enums.SemiRegexes;
import model.Deck;

import java.io.File;

public class Validator {
    private static Validator validator;

    private Validator() {
    }

    public static Validator getValidator() {
        if (validator == null) validator = new Validator();
        return validator;
    }

    public boolean validateUsername(String username) {
        return username.matches(SemiRegexes.VALID_USERNAME.regex);
    }

    public boolean validateNickname(String nickname) {
        return true; // TODO
    }

    public boolean validatePassword(String password) {
        return password.matches(SemiRegexes.VALID_PASSWORD.regex);
    }

    public boolean validateEmail(String email) {
        return email.matches(SemiRegexes.VALID_EMAIL_ADDRESS.regex);
    }

    public boolean validateFileName(String fileName) {
        return false; // TODO
    }

    public boolean validateFileAddress(String fileAddress) {
        return false; // TODO
    }

    public boolean isPasswordWeak(String password) {
        return !password.matches(SemiRegexes.STRONG_PASSWORD_REGEX.regex);
    }

    public boolean isUsernameDuplicate(String username) {
        return false; // TODO
    }

    public boolean isFilePathDuplicate(String filePath) {
        return true; // TODO
    }

    public boolean isFileNameDuplicate(String fileName) {
        return true; // TODO
    }

    public boolean isPasswordCorrect(String password) {
        return true; // TODO
    }

    public boolean checkNumberOfGameHistory(String number) {
        return true; // TODO
    }

    public boolean checkIfHistoryIsEmpty() {
        return false; // TODO
    }

    /**
     * Reads the file ,and it must check everything such as number of cards, name of cards, etc. but we mayn't implement
     * that due to the complexity of the whole project.
     *
     * @param file The file object which is opened and checked whether its content is valid.
     */
    public boolean isDeckFileContentValid(File file) {
        return true; // TODO
    }

    public boolean checkLeaderNumber(Deck deck, int leaderNumber) {
        return true; // TODO
    }
}
