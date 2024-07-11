package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ServerResponse {
    FriendRequest("friend request from (?<username>\\S+)\\s*"),
    ClientNotOnline("(?<username>\\S+) is not online\\s*"),
    ClientNotAvailable("(?<username>\\S+) is not available right now\\s*"),
    FriendRequestSent("friend request sent successfully\\s*"),
    UsernameDoesntExists("user doesn't exists\\s*"),
    Wait("wait\\s*"),
    GameStarted("game started\\s*"),
    MassageFromOpponent("opponent say: (?<massage>.+)\\s*"),
    PlayCardByOpponent("opponent placed card (?<cardName>\\S+)( on row (?<rowNumber>\\S+))?\\s*"),
    Passed("passed"),
    Pass("pass\\s*"),
    LeaderBoard("leaderBoard:"),
    UpdateLeaderBoard("update LeaderBoard:"),
    YourTurn("your turn");

    private String pattern;

    ServerResponse(String pattern) {
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
