package Server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ServerCommands {
    sendFriendRequest("send friend request to (?<username>\\S+)\\s*"),
    watchGame("watch (?<username>.+) game\\s*"),//changed to .+
    startPrivateRandomGame("start private random game"),
    startPublicRandomGame("start public random game"),
    register("register\\s*"),
    sendMassageToOpponent("opponent say: (?<massage>.+)\\s*"),
    PlayCard("place card (?<cardName>\\S+)( on row (?<rowNumber>\\d+))?\\s*"),//TODO might not match
    LeaderBoard("show leaderBoard\\s*");
    private String pattern;

    ServerCommands(String pattern) {
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
