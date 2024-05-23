package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    ChangeUsername("\\s*change\\s+username\\s+-u\\s+(?<NewUsername>[a-zA-Z0-9-]+)\\s*"),

    ChangeNickname("\\s*change\\s+nickname\\s+-u\\s+(?<NewNickname>[a-zA-Z0-9-]+)\\s*"),

    ChangeEmail("\\s*change\\s+email\\s+-e\\s+(?<NewEmail>[a-zA-Z0-9-.]+@[a-zA-Z0-9-]+.com)\\s*"),

    ChangePassword("\\s*change\\s+password\\s+-p\\s+(?<NewPassword>[0-9a-zA-Z]+)\\s+(?<OldPassword>[a-zA-Z0-9])\\s*");
    private String pattern;
    ProfileMenuCommands(String pattern){this.pattern=pattern;}
    public Matcher getMatcher(String input){
        Matcher matcher= Pattern.compile(this.pattern).matcher(input);
        if(matcher.matches()){
            return matcher;
        }
        return null;
    }
}
