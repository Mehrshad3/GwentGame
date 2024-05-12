package controller;

import enums.SemiRegexes;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;

abstract class LoginOrRegisterMenuController extends MenuController {

    protected abstract boolean getSecurityQuestionAnswer(Matcher matcher);

    protected String generateRandomPassword() {
        int length = 14;
        StringBuilder stringBuilder = new StringBuilder(length);
        while (Validator.getValidator().isPasswordWeak(stringBuilder.toString())) {
            stringBuilder.setLength(0);
            for (int i = 0; i < length; i++) {
                stringBuilder.append(getRandomCharacterForPassword());
            }
        }
        return stringBuilder.toString();
    }

    private char getRandomCharacterForPassword() {
        final int numberOfUppercaseCharacters = 'Z' - 'A' + 1;
        final int numberOfLowercaseCharacters = 'z' - 'a' + 1;
        final int numberOfDigits = 10;
        final int numberOfSpecialCharacters = SemiRegexes.SPECIAL_CHARACTERS_RAW_FORMAT.regex.length();
        final int numberOfAllowedCharacters = numberOfUppercaseCharacters + numberOfLowercaseCharacters + numberOfDigits
                + numberOfSpecialCharacters;

        Random random = new SecureRandom();
        int randInt = random.nextInt(numberOfAllowedCharacters);
        if (randInt < numberOfUppercaseCharacters) return (char) (randInt + 'A');
        else if (randInt < numberOfUppercaseCharacters + numberOfLowercaseCharacters) {
            return (char) (randInt - numberOfUppercaseCharacters + 'a');
        } else if (randInt < numberOfUppercaseCharacters + numberOfLowercaseCharacters + numberOfDigits) {
            return (char) (randInt - numberOfUppercaseCharacters - numberOfLowercaseCharacters + '0');
        } else {
            int index = randInt - numberOfUppercaseCharacters - numberOfLowercaseCharacters - numberOfDigits;
            return SemiRegexes.SPECIAL_CHARACTERS_REGEX.regex.charAt(index);
        }
    }
}
