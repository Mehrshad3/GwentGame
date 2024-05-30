package enums.regexes;

import java.util.regex.Matcher;

interface Regex {
}

public interface MatcherSupplier extends Regex {
    Matcher getMatcher(String input);
}