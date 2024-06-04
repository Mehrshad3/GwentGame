package controller;

import enums.regexes.SemiRegexes;
import model.Deck;
import model.GsonReaderWriter;
import model.User;

import java.io.File;
import java.nio.file.Path;

public class Validator {
    private static Validator validator;

    private Validator() {
    }

    public static Validator getValidator() {
        if (validator == null) validator = new Validator();
        return validator;
    }

    public boolean validateUsername(String username) {
        System.out.println(username);
        return SemiRegexes.VALID_USERNAME.matches(username);
    }

    public boolean validateNickname(String nickname) {
        return true; // TODO
    }

    public boolean validatePassword(String password) {
        return SemiRegexes.VALID_PASSWORD.matches(password);
    }

    public boolean validateEmail(String email) {
        return SemiRegexes.VALID_EMAIL_ADDRESS.matches(email);
    }

    public boolean validateFileName(String fileName) {
        return false; // TODO
    }

    public boolean validateFileAddress(String fileAddress) {
        return false; // TODO
    }

    public boolean validateDeckName(String deckName) {
        return SemiRegexes.VALID_DECK_NAME.matches(deckName);
    }

    public boolean isPasswordWeak(String password) {
        return !SemiRegexes.STRONG_PASSWORD_REGEX.matches(password);
    }

    public boolean isUsernameDuplicate(String username) {
        return GsonReaderWriter.getGsonReaderWriter().doesUserExist(username);
    }

    public boolean isFilePathDuplicate(String filePath) {
        Path path = Path.of(filePath);
        return path.toFile().exists();
    }

    public boolean isFileNameDuplicate(String fileName) {
        return true; // TODO
    }

    public boolean isPasswordCorrect(User user, String password) {
        return user.getPassword().equals(password);
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
