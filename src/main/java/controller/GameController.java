package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.Player;
import model.Row;
import model.Table;
import model.faction.Card;
import model.faction.Faction;
import model.faction.UnitCard;

import java.util.ArrayList;

public class GameController extends MenuController {
    private final IntegerProperty[] sumOfRows = new IntegerProperty[6];
    GameStatus gaming;
    private Player player1;
    private Player player2;

    /**
     * Initializes the game
     */
    public void setStartStatus(Player player1) {
        this.player1 = player1;
        player2 = new Player("guest", "placeholder");
        gaming = new GameStatus(new Table(player1, player2), player1, player2);
        for (int i = 0; i < sumOfRows.length; i++) {
            Row row = gaming.getTable().getRows()[i];
            int rowIndex = i;
            sumOfRows[i] = new SimpleIntegerProperty();
            // Calculate sum of rows
            row.getCards().addListener((ListChangeListener<? super UnitCard>) change ->
                    sumOfRows[rowIndex].set(row.getCards().stream().mapToInt(UnitCard::getPower).sum())
            );
        }
    }

    public void runARound() {
        // TODO
    }

    private void runATurn() {
        // TODO
    }

    public void playCard(Card card) {
        // TODO
    }

    private void empirePowerDoing() {
        // TODO
    }

    public void passRound() {
        // TODO
    }

    public GameStatus getGaming() {
        return this.gaming;
    }

    public void setGaming(GameStatus gaming) {
        this.gaming = gaming;
    }

    public ObservableList<Card> getPlayer1InHandCards() {
        return player1.getDeck().getInHandCards();
    }

    public int getNumberOfPlayer1Cards() {
        return player1.getDeck().getInHandCards().size();
    }

    public int getNumberOfPlayer2Cards() {
        return player2.getDeck().getInHandCards().size();
    }

    public int getSizeOfPlayer1Deck() {
        return player1.getDeck().getNumberOfCardsInDeck();
    }

    public int getSizeOfPlayer2Deck() {
        return player2.getDeck().getNumberOfCardsInDeck();
    }

    public Faction getPlayer1Faction() {
        return player1.getDeck().getFaction();
    }

    public Faction getPlayer2Faction() {
        return player2.getDeck().getFaction();
    }

    public Card getPlayer1LeaderCard() {
        return player1.getDeck().getCurrentLeaderCard();
    }

    public Card getPlayer2LeaderCard() {
        return player2.getDeck().getCurrentLeaderCard();
    }

    public ArrayList<Card> getPlayer1DiscardPile() {
        return player1.getDeck().getDiscardCards();
    }

    public ArrayList<Card> getPlayer2DiscardPile() {
        return player2.getDeck().getDiscardCards();
    }

    public IntegerProperty getSumOfRowNumber(int rowNumber) {
        return sumOfRows[rowNumber - 1];
    }


    public void fillPlayer1LivesForDebug() {
        gaming.emptyPlayer2WinsForDebug();
    }

    public void fillPlayer2LivesForDebug() {
        gaming.emptyPlayer1WinsForDebug();
    }

    public void addCardToHandForDebug(Card card) {
        player1.getDeck().getInHandCards().add(card);
    }
}
