package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    REGISTER_USER("register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s+(?<passwordConfirm>\\S+)" +
            "\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)"),
    REGISTER_USER_WITH_RANDOM_PASSWORD("register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>random)" +
            "\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)"),
    PICK_QUESTION("pick\\s+question\\s+-q(?<questionNumber>(?=[1-9])[0-9]+)\\s+-a\\s+(?<answer>.+)");
    private final Pattern pattern;

    RegisterMenuCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
