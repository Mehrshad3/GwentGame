package model;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.FactionAbilityDoings.FactionAbility;
import controller.HandleRounds;
import javafx.collections.ObservableList;
import model.faction.Card;

public class GameStatus {
    private final Player player1;
    private final Player player2;
    public transient HandleRounds handleRounds;
    private Ability Leader1ability;
    private Ability Leader2ability;
    private boolean KingBranAbility = false;
    private boolean TheTreacherousAbility = false;
    private boolean leader1Did = false;
    private boolean leader2Did = false;
    private FactionAbility faction1abilitydoing;
    private FactionAbility faction2abilitydoing;
    private int numberOfPassedRounds = 0;
    private int numberOfTurns = 0;
    private Table table;
    private int player1Wins = 0;
    private int player2Wins = 0;

    public GameStatus(Table table, Player player1, Player player2) {
        this.table = table;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Ability getLeader1ability() {
        return Leader1ability;
    }

    public void setLeader1ability(Ability leader1ability) {
        Leader1ability = leader1ability;
    }

    public Ability getLeader2ability() {
        return Leader2ability;
    }

    public void setLeader2ability(Ability leader2ability) {
        Leader2ability = leader2ability;
    }

    public boolean isKingBranAbility() {
        return KingBranAbility;
    }

    public void setKingBranAbility(boolean kingBranAbility) {
        KingBranAbility = kingBranAbility;
    }

    public boolean isTheTreacherousAbility() {
        return TheTreacherousAbility;
    }

    public void setTheTreacherousAbility(boolean theTreacherousAbility) {
        TheTreacherousAbility = theTreacherousAbility;
    }

    void setLeader1Did(boolean leader1Did) {
        this.leader1Did = leader1Did;
    }

    void setLeader2Did(boolean leader2Did) {
        this.leader2Did = leader2Did;
    }

    boolean didLeader1Do() {
        return leader1Did;
    }

    boolean didLeader2Do() {
        return leader2Did;
    }

    FactionAbility getFaction1abilitydoing() {
        return faction1abilitydoing;
    }

    void setFaction1abilitydoing(FactionAbility faction1abilitydoing) {
        this.faction1abilitydoing = faction1abilitydoing;
    }

    FactionAbility getFaction2abilitydoing() {
        return faction2abilitydoing;
    }

    void setFaction2abilitydoing(FactionAbility faction2abilitydoing) {
        this.faction2abilitydoing = faction2abilitydoing;
    }

    public HandleRounds getHandleRounds() {
        return handleRounds;
    }

    public void setHandleRounds(HandleRounds handleRounds) {
        this.handleRounds = handleRounds;
    }

    public int getNumberOfPassedRounds() {
        return numberOfPassedRounds;
    }

    public void setNumberOfPassedRounds(int numberOfPassedRounds) {
        this.numberOfPassedRounds = numberOfPassedRounds;
    }

    public Table getTable() {
        return table;
    }

    @Deprecated
    public void setTable(Table table) {
        this.table = table;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public void setPlayer1Wins(int newNumber) {
        player1Wins = newNumber;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }

    public void setPlayer2Wins(int newNumber) {
        player2Wins = newNumber;
    }

    public int getNumberOfRecentPastTurns() {
        return table.getNumberOfTurnsPassed();
    }

    public void changeTurn() {
        table.changeTurn();
    }

    public ObservableList<Card> getDiscardPile(Player player) {
        return player.getDeck().getDiscardCards();
    }

    public void removeFromDiscardPile(Card card, Player player) {
        player.getDeck().getDiscardCards().remove(card);
    }

    public void moveToDiscardPile(Card card, Player player) {
        player.getDeck().moveToDiscardPile(card);
    }
}