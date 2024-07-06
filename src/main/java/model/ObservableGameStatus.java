package model;

import controller.HandleRounds;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import model.faction.Card;

public class ObservableGameStatus {
    private final GameStatus gameStatus;
    private final IntegerProperty numberOfPassedRounds = new SimpleIntegerProperty();
    private final IntegerProperty numberOfTurns = new SimpleIntegerProperty();
    private final ObservableTable table;
    private final IntegerProperty player1Wins = new SimpleIntegerProperty();
    private final IntegerProperty player2Wins = new SimpleIntegerProperty();

    public ObservableGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.table = new ObservableTable(gameStatus.getTable());
        numberOfPassedRounds.addListener((observableValue, number, t1) -> gameStatus.numberOfPassedRounds = (int) t1);
        numberOfTurns.addListener((observableValue, number, t1) -> gameStatus.numberOfTurns = (int) t1);
        player1Wins.addListener((observableValue, number, t1) -> gameStatus.setPlayer1Wins((int) t1));
        player2Wins.addListener((observableValue, number, t1) -> gameStatus.setPlayer2Wins((int) t1));
    }

    public ObservableGameStatus(Table table, Player player1, Player player2) {
        this(new GameStatus(table, player1, player2));
    }

    public HandleRounds getHandleRounds() {
        return gameStatus.getHandleRounds();
    }

    public void setHandleRounds(HandleRounds handleRounds) {
        gameStatus.setHandleRounds(handleRounds);
    }

    public int getNumberOfPassedRounds() {
        return numberOfPassedRounds.get();
    }

    public ObservableTable getTable() {
        return table;
    }

    public Player getPlayer1() {
        return gameStatus.getPlayer1();
    }

    public Player getPlayer2() {
        return gameStatus.getPlayer2();
    }

    public int getNumberOfTurns() {
        return numberOfTurns.get();
    }

    public int getPlayer1Wins() {
        return player1Wins.get();
    }

    public void setPlayer1Wins(int newNumber) {
        player1Wins.set(newNumber);
    }

    public int getPlayer2Wins() {
        return player2Wins.get();
    }

    public void setPlayer2Wins(int newNumber) {
        player2Wins.set(newNumber);
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
