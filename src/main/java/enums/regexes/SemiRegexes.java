package enums.regexes;

import java.util.regex.Pattern;

public enum SemiRegexes implements Regex {
    SPECIAL_CHARACTERS_RAW_FORMAT("!@#$.%^&*()[]{}<>?,_\"`~-", false),
    SPECIAL_CHARACTERS_REGEX("[!@#$.%^&*()\\[\\]{}<>?,_\\\"`~-]", true),
    VALID_USERNAME("[a-zA-Z0-9-]+"),
    VALID_PASSWORD("(" + SPECIAL_CHARACTERS_REGEX.regex + "|[a-zA-Z0-9])+"),
    VALID_EMAIL_ADDRESS("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)" +
            "*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")" +
            "@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[" +
            "(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.)" +
            "{3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-zA-Z0-9-]*[a-zA-Z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"),
    STRONG_PASSWORD_REGEX("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*" + SPECIAL_CHARACTERS_REGEX.regex + ").{8,}"),
    VALID_DECK_NAME("[^<>:\"/\\\\|?*\\x00-\\x1F]+"),
    VALID_WINDOWS_FILENAME("(?!(?:CON|PRN|AUX|NUL|COM[1-9]|LPT[1-9])(?:\\.[^.]*)?$)" +
            "[^<>:\\\"\\/\\\\|?*\\x00-\\x1F]*[^<>:\\\"\\/\\\\|?*\\x00-\\x1F\\ .]\n"),
    ;
    final public String regex;
    private final Pattern pattern;

    SemiRegexes(String regex) {
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }

    SemiRegexes(String regex, boolean isCompilable) {
        this.regex = regex;
        this.pattern = isCompilable ? Pattern.compile(regex) : null;
    }

    public boolean matches(String input) {
        return pattern.matcher(input).matches();
    }
}
