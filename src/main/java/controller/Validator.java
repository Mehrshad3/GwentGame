package controller;

import enums.SemiRegexes;
import model.Deck;
import model.GsonReaderWriter;
import model.User;

import java.io.File;
import java.nio.file.Path;

final class Validator {
    private static Validator validator;

    private Validator() {
    }

    public static Validator getValidator() {
        if (validator == null) validator = new Validator();
        return validator;
    }

    boolean validateUsername(String username) {
        return username.matches(SemiRegexes.VALID_USERNAME.regex);
    }

    boolean validateNickname(String nickname) {
        return true; // TODO
    }

    boolean validatePassword(String password) {
        return password.matches(SemiRegexes.VALID_PASSWORD.regex);
    }

    boolean validateEmail(String email) {
        return email.matches(SemiRegexes.VALID_EMAIL_ADDRESS.regex);
    }

    boolean validateFileName(String fileName) {
        return false; // TODO
    }

    boolean validateFileAddress(String fileAddress) {
        return false; // TODO
    }

    boolean isPasswordWeak(String password) {
        return !password.matches(SemiRegexes.STRONG_PASSWORD_REGEX.regex);
    }

    boolean isUsernameDuplicate(String username) {
        return GsonReaderWriter.fileOf(User.getRelativePathToFile(username)).exists();
    }

    boolean isFilePathDuplicate(String filePath) {
        Path path = Path.of(filePath);
        return path.toFile().exists();
    }

    boolean isFileNameDuplicate(String fileName) {
        return true; // TODO
    }

    boolean isPasswordCorrect(String password) {
        return true; // TODO
    }

    boolean checkNumberOfGameHistory(String number) {
        return true; // TODO
    }

    boolean checkIfHistoryIsEmpty() {
        return false; // TODO
    }

    /**
     * Reads the file ,and it must check everything such as number of cards, name of cards, etc. but we mayn't implement
     * that due to the complexity of the whole project.
     *
     * @param file The file object which is opened and checked whether its content is valid.
     */
    boolean isDeckFileContentValid(File file) {
        return true; // TODO
    }

    boolean checkLeaderNumber(Deck deck, int leaderNumber) {
        return true; // TODO
    }
}
