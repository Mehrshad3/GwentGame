package enums.regexes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GeneralMenuCommands implements MatcherSupplier {
    MENU_ENTER("menu\\s+enter\\s+(?<menuName>[Ll]ogin|[Rr]egister|[Mm]ain|[Pp]rofile|[Gg]ame)\\s*([Mm]enu)?"),
    MENU_EXIT("menu\\s+exit"),
    SHOW_MENU("show\\s+current\\s+menu"),
    ;
    private final Pattern pattern;

    GeneralMenuCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return matcher;
        }

        return null;

    }
}
