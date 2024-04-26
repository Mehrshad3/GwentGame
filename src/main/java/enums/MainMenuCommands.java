package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    Logout("\\s*user\\s+logout\\s*"),
    GoToProfileMenu("\\s*go\\s+to\\s+profile\\s+menu\\s*"),
    GoToShopMenu("\\s*go\\s+to\\s+game\\s+menu\\s*"),
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*");
    private String pattern;

    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }

        return null;

    }
}
