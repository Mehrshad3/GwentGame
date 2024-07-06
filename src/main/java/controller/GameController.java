package controller;

import controller.Checking.GetAbility;
import enums.card.CardName;
import enums.card.RowWeather;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.*;
import model.faction.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import static model.App.LOGGER;

public class GameController extends MenuController {
    public static final int numberOfInitialCardsPerPlayer = 10;
    private final IntegerProperty[] sumOfRows = new IntegerProperty[6];
    private final BooleanProperty isMyTurn = new SimpleBooleanProperty();
    private final IntegerProperty numberOfCardsInMyHand = new SimpleIntegerProperty();
    private final IntegerProperty numberOfCardsInOpponentHand = new SimpleIntegerProperty(10);
    private final IntegerProperty player1Lives = new SimpleIntegerProperty(2);
    private final IntegerProperty player2Lives = new SimpleIntegerProperty(2);
    private final ObservableList<UnitCard>[] rows = new ObservableList[6];
    private final ObservableList<WeatherCard> weatherCards = FXCollections.observableArrayList();
    ObservableGameStatus gaming;
    HandleRounds handleRounds;
    private Player player1;
    private Player player2;

    public GameController() {
        for (int i = 0; i < rows.length; i++) {
            rows[i] = FXCollections.observableArrayList();
        }
    }

    public ObservableList<WeatherCard> getWeatherCards() {
        return weatherCards;
    }

    public ObservableList<UnitCard>[] getRows() {
        return rows;
    }

    public ObservableValue<SpellCard>[] getSpellCards() {
        ObservableValue<SpellCard>[] spellCards = new ObservableValue[6];
        for (int i = 0; i < 6; i++) {
            spellCards[i] = gaming.getTable().getRows()[i].getSpell();
        }
        return spellCards;
    }

    public ReadOnlyIntegerProperty player1LivesProperty() {
        return player1Lives;
    }

    public ReadOnlyIntegerProperty player2LivesProperty() {
        return player2Lives;
    }

    public ObservableGameStatus getGaming() {
        return this.gaming;
    }

    public void setGaming(ObservableGameStatus gaming) {
        this.gaming = gaming;
        App.getAppObject().setGaming(gaming);
    }

    public void setHandleRounds(HandleRounds handleRounds) {
        this.handleRounds = handleRounds;
    }

    public void setGamingAndUpdateScreen(ObservableGameStatus gaming) {
        setGaming(gaming);
        for (int i = 0; i < sumOfRows.length; i++) {
            ObservableRow row = gaming.getTable().getRows()[i];
            int rowIndex = i;
            sumOfRows[i] = new SimpleIntegerProperty();
            // Calculate sum of rows
            row.getUnitCards().addListener((ListChangeListener<? super UnitCard>) change ->
                    sumOfRows[rowIndex].set(row.getUnitCards().stream().mapToInt(UnitCard::getPower).sum())
            );
        }
        getPlayer1InHandCards().addListener((ListChangeListener<? super Card>) change -> {
            change.next();
            numberOfCardsInMyHand.set(numberOfCardsInMyHand.get() + change.getAddedSize() - change.getRemovedSize());
        });
        weatherCards.addListener((ListChangeListener<? super WeatherCard>) change -> {
            change.next();
            if (change.wasAdded()) {
                for (int i = change.getFrom(); i < change.getTo(); i++) {
                    gaming.getTable().addWeather(change.getList().get(i));
                }
            } else if (change.wasRemoved()) {
                int from = change.getFrom();
                assert from == change.getTo();
                for (int i = 0; i < change.getRemovedSize(); i++) {
                    gaming.getTable().getWeatherCards().remove(from);
                }
            }
        });
        // TODO
    }

    public ReadOnlyBooleanProperty isMyTurnProperty() {
        return isMyTurn;
    }

    public ReadOnlyIntegerProperty numberOfCardsInOpponentHandProperty() {
        return numberOfCardsInOpponentHand;
    }

    public ReadOnlyIntegerProperty numberOfCardsInMyHandProperty() {
        return numberOfCardsInMyHand;
    }

    /**
     * Initializes the game
     */
    public void setStartStatus(Player player1) {
        this.player1 = player1;
        player2 = new Player("guest", "placeholder");
        setGamingAndUpdateScreen(new ObservableGameStatus(new Table(player1, player2), player1, player2));
        player2.getDeck().setCurrentLeaderCard((LeaderCard) CardName.BRINGER_OF_DEATH.getNewCard());
        isMyTurn.setValue(true);
        dealCards();

        player1Lives.addListener((observableValue, number, t1) -> gaming.setPlayer2Wins(2 - (int) t1));
        player2Lives.addListener((observableValue, number, t2) -> gaming.setPlayer1Wins(2 - (int) t2));
        addListenerToRowsObservableLists();
    }

    private void addListenerToRowsObservableLists() {
        for (int i = 0; i < rows.length; i++) {
            int finalI = i;
            rows[i].addListener((ListChangeListener<UnitCard>) change -> {
                while (change.next()) { // I don't know why this method is used.
                    if (change.wasAdded()) addAddedCardsToRow(change, finalI);
                    else if (change.wasPermutated()) {
                        LOGGER.log(Level.SEVERE, "The program is not capable of showing permutated cards.");
                    } else if (change.wasRemoved()) removeRemovedCardsFromRow(change, finalI);
                    else if (change.wasReplaced()) replaceReplacedCardsInRow(change, finalI);
                }
            });
        }
    }

    private void addCardsToRow(int from, int to, List<? extends UnitCard> list, int rowIndex) {
        for (int i = from; i < to; i++) {
            UnitCard card = list.get(i);
            gaming.getTable().getRows()[rowIndex].placeCard(from, card);
        }
    }

    private void addAddedCardsToRow(ListChangeListener.Change<? extends UnitCard> change, int rowIndex) {
        try {
            addCardsToRow(change.getFrom(), change.getTo(), change.getList(), rowIndex);
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Exception / Error in updating cards in the view occurred.", e);
        }
    }

    private void removeRemovedCardsFromRow(ListChangeListener.Change<? extends UnitCard> change, int rowIndex) {
        assert change.getFrom() == change.getTo();
        rows[rowIndex].remove(change.getFrom(), change.getTo() + change.getRemovedSize());
    }

    private void replaceReplacedCardsInRow(ListChangeListener.Change<? extends UnitCard> change, int rowIndex) {
        removeRemovedCardsFromRow(change, rowIndex);
        addAddedCardsToRow(change, rowIndex);
    }

    private void dealCards() {
        Random random = new SecureRandom();
        // Deal first player cards
        ArrayList<Card> notChosenCards1 = player1.getDeck().getCardsInDeck();
        for (int j = 0; j < numberOfInitialCardsPerPlayer; j++) {
            if (notChosenCards1.isEmpty()) {
                LOGGER.log(Level.WARNING, "Player 1 doesn't have 10 cards in their deck!");
                break;
            }
            int randomIndex = random.nextInt(notChosenCards1.size());
            player1.getDeck().addCardToHand(notChosenCards1.get(randomIndex));
        }
        // Deals second player cards
        ArrayList<Card> notChosenCards2 = player2.getDeck().getCardsInDeck();
        for (int j = 0; j < numberOfInitialCardsPerPlayer; j++) {
            if (notChosenCards2.isEmpty()) {
                LOGGER.log(Level.WARNING, "Player 2 doesn't have 10 cards in their deck!");
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

    public void playLeaderCard(Card card) {
        // TODO
    }

    public void playCard(Card card, int rowToPlay) {
        if (card instanceof SpellCard) {
            playCommanderHorn(card, rowToPlay);
            // TODO: check decoy card
        } else if (card instanceof UnitCard unitCard) {
            if (card.getPossibleRowsToBePlayed().getPossibleRowNumbers().contains(rowToPlay)) {
                rows[rowToPlay - 1].add(unitCard);
                getPlayer1InHandCards().remove(unitCard);
                // TODO: do card ability
                // TODO: SpyChecking
            }
        }
    }

    /**
     * Plays {@link CardName#COMMANDER_HORN Commander's Horn} card or any other {@link SpellCard}.
     */
    public void playCommanderHorn(Card card, int rowToPlay) {
        if (!(card instanceof SpellCard spellCard)) return;
        CommandersHornAbility ability = new CommandersHornAbility();
        ability.setGameStatus(gaming);
        GetAbility.getAbility(card, gaming, player1, handleRounds);
        gaming.getTable().addSpell(spellCard, rowToPlay - 1);
        getPlayer1InHandCards().remove(spellCard);
    }

    /**
     * @param card The weather card that the user wants to play. If it isn't a weather card, the method doesn't do
     *             anything.
     */
    public void playWeatherCard(Card card) {
        if (!(card instanceof WeatherCard weatherCard)) return;
        // TODO
        gaming.getTable().addWeather(weatherCard);
        player1.getDeck().getInHandCards().remove(card);
        weatherCards.add(weatherCard);
        card.doAbility(this);
    }

    public void setRowWeather(int i, RowWeather weather) {
        gaming.getTable().getRows()[i].setWeather(weather);
        if (weather == RowWeather.CLEAR_WEATHER) weatherCards.clear();
    }

    private void empirePowerDoing() {
        // TODO
    }

    public void passRound() {
        isMyTurn.set(false);
        handleRounds.passround();
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

    public LeaderCard getPlayer1LeaderCard() {
        return player1.getDeck().getCurrentLeaderCard();
    }

    public LeaderCard getPlayer2LeaderCard() {
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
        player1Lives.set(2);
    }

    public void fillPlayer2LivesForDebug() {
        player2Lives.set(2);
    }

    public void decreasePlayer1LivesForDebug() {
        player1Lives.set(player1Lives.get() - 1);
    }

    public void decreasePlayer2LivesForDebug() {
        player2Lives.set(player2Lives.get() - 1);
    }

    public void addCardToHandForDebug(Card card) {
        player1.getDeck().getInHandCards().add(card);
    }
}
