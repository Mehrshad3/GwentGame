package model;

import model.faction.Card;

import java.util.ArrayList;

public class GameStatus {
    private final Player player1;
    private final Player player2;
    private int numberOfPassedRounds = 0;
    private Table table;
    private int numberOfTurns = 0;
    private int player1Wins = 0;
    private int player2Wins = 0;

    public GameStatus(Table table, Player player1, Player player2) {
        this.table = table;
        this.player1 = player1;
        this.player2 = player2;
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

    public int getPlayer2Wins() {
        return player2Wins;
    }

    public int getNumberOfRecentPastTurns() {
        return table.getNumberOfTurnsPassed();
    }

    public void changeTurn() {
        table.changeTurn();
    }

    public ArrayList<Card> getDiscardPile(Player player) {
        return player.getDeck().getDiscardCards();
    }

    public void removeFromDiscardPile(Card card, Player player) {
        player.getDeck().getDiscardCards().remove(card);
    }

    public void moveToDiscardPile(Card card, Player player) {
        player.getDeck().moveToDiscardPile(card);
    }

    public void emptyPlayer1WinsForDebug() {
        player1Wins = 0;
    }

    public void emptyPlayer2WinsForDebug() {
        player2Wins = 0;
    }
}