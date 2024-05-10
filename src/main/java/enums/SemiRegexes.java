package enums;

public enum SemiRegexes {
    SPECIAL_CHARACTERS_RAW_FORMAT("!@#$.%^&*()[]{}<>?,_\"`~-"),
    SPECIAL_CHARACTERS_REGEX("[!@#$.%^&*()\\[\\]{}<>?,_\\\"`~-]"),
    VALID_USERNAME("[a-zA-Z0-9-]+"),
    VALID_PASSWORD("(" + SPECIAL_CHARACTERS_REGEX.regex + "|[a-zA-Z0-9])+"),
    VALID_EMAIL_ADDRESS("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)" +
            "*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.)" +
            "{3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"),
    STRONG_PASSWORD_REGEX("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*" + SPECIAL_CHARACTERS_REGEX.regex + ").{8,}"),
    ;
    final public String regex;

    SemiRegexes(String regex) {
        this.regex = regex;
    }
}
