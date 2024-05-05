package controller;

import model.Deck;

import java.io.File;

class Validator {

    boolean validateUsername(String username) {
        return true; // TODO
    }

    boolean validateNickname(String nickname) {
        return true; // TODO
    }

    boolean validatePassword(String password) {
        return true; // TODO
    }

    boolean validateEmail(String email) {
        return true; // TODO
    }

    boolean validateFileName(String fileName) {
        return false; // TODO
    }

    boolean validateFileAddress(String fileAddress) {
        return false; // TODO
    }

    boolean isPasswordWeak(String password) {
        return false; // TODO
    }

    boolean isUsernameDuplicate(String username) {
        return false; // TODO
    }

    boolean isFilePathDuplicate(String filePath) {
        return true; // TODO
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
