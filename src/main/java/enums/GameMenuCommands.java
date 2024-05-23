package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    CreateGame("\\s*create\\s+game\\s+-p2\\s+(?<secondPlayerUsername>\\S+)\\s*"),
    ShowFactions("\\s*show\\s+factions\\s*"),
    SelectFaction("\\s*select\\s+faction\\s+-f\\s+(?<factionName>\\S+)\\s*"),
    ShowCards("\\s*show\\s+cards\\s*"),
    ShowDeck("\\s*show\\s+deck\\s*"),
    ShowCurrentUserInfo("\\s*show\\s+information\\s+current\\s+user\\s*"),
    SaveDeckInfoToFile("\\s*save\\s+deck\\s+-f\\s+(?<fileAddress>\\S+)\\s*"),
    SaveDeck(""),
    LoadDeckFromFile("\\s*load\\s+deck\\s+-f\\s+(?<fileAddress>\\S+)\\s*"),
    LoadDeck(""),
    ShowLeaders("\\s*show\\s+leaders\\s*"),
    SelectLeader("\\s*select\\s+leader\\s+-l\\s+(?<leaderNumber>\\d+)\\s*"),
    AddCardToDeck("\\s*add\\s+to\\s+deck\\s+-c\\s+(?<cardName>\\S+)(\\s+(?<count>\\d+))?\\s*"),
    DeleteCardFromDeck("\\s*delete\\s+from\\s+deck\\s+-n(?<cardName>\\S+)(\\s+-c\\s+(?<count>\\d+)?\\s*)"),
    ChangeTurn("\\s*change\\s+turn\\s*"),
    StartGame("\\s*start\\s+game\\s*");


    private String pattern;

    GameMenuCommands(String pattern) {
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
