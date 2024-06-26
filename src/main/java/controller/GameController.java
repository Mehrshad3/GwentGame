package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.*;
import model.faction.Card;
import model.faction.Faction;
import model.faction.UnitCard;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

public class GameController extends MenuController {
    public static final int numberOfInitialCardsPerPlayer = 10;
    private final IntegerProperty[] sumOfRows = new IntegerProperty[6];
    GameStatus gaming;
    private Player player1;
    private Player player2;

    public GameStatus getGaming() {
        return this.gaming;
    }

    public void setGaming(GameStatus gaming) {
        this.gaming = gaming;
    }

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
        dealCards();
    }

    private void dealCards() {
        Random random = new SecureRandom();
        // Deal first player cards
        ArrayList<Card> notChosenCards1 = player1.getDeck().getCardsInDeck();
        for (int j = 0; j < numberOfInitialCardsPerPlayer; j++) {
            if (notChosenCards1.isEmpty()) {
                App.LOGGER.log(Level.WARNING, "Player 1 doesn't have 10 cards in their deck!");
                break;
            }
            int randomIndex = random.nextInt(notChosenCards1.size());
            player1.getDeck().addCardToHand(notChosenCards1.get(randomIndex));
        }
        // Deals second player cards
        ArrayList<Card> notChosenCards2 = player2.getDeck().getCardsInDeck();
        for (int j = 0; j < numberOfInitialCardsPerPlayer; j++) {
            if (notChosenCards2.isEmpty()) {
                App.LOGGER.log(Level.WARNING, "Player 2 doesn't have 10 cards in their deck!");
                break;
            }
            int randomIndex = random.nextInt(notChosenCards2.size());
            player2.getDeck().addCardToHand(notChosenCards2.get(randomIndex));
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

    public ObservableList<Card> getPlayer1DiscardPile() {
        return player1.getDeck().getDiscardCards();
    }

    public ObservableList<Card> getPlayer2DiscardPile() {
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
