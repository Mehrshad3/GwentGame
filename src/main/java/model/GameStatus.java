package model;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.FactionAbilityDoings.FactionAbility;
import controller.HandleRounds;
import javafx.collections.ObservableList;
import model.faction.Card;

public class GameStatus {
    private final Player player1;
    private final Player player2;
    public FactionAbility faction1abilitydoing;
    public FactionAbility faction2abilitydoing;
    public HandleRounds handleRounds;
    private int numberOfPassedRounds = 0;
    private Table table;
    private int numberOfTurns = 0;
    private int player1Wins = 0;
    private int player2Wins = 0;
    public boolean KingBranAbility=false;
    public boolean TheTreacherousAbility=false;
    public boolean Leader1did=false;
    public boolean Leader2did=false;
    public Ability Leader1ability;
    public Ability Leader2ability;

    public GameStatus(Table table, Player player1, Player player2) {
        this.table = table;
        this.player1 = player1;
        this.player2 = player2;
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

    public Table getTable() {
        return table;
    }

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

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public void setNumberOfPassedRounds(int numberOfPassedRounds) {
        this.numberOfPassedRounds = numberOfPassedRounds;
    }
}