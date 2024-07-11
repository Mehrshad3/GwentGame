package controller;

import Server.ClientHandler;
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
    private final ObservableList<Card>[] rows = new ObservableList[6];
    private final ObservableList<WeatherCard> weatherCards = FXCollections.observableArrayList();
    private final StringProperty chatLabelText = new SimpleStringProperty();

    public StringProperty chatLabelTextProperty() {
        return chatLabelText;
    }

    public void setChatLabelText(String chatLabelText) {
        this.chatLabelText.set(chatLabelText);
    }

    ObservableGameStatus gaming;
    HandleRounds handleRounds;
    Client client;
    private Player player1;
    private Player player2;

    public GameController() {
        for (int i = 0; i < rows.length; i++) {
            rows[i] = FXCollections.observableArrayList();
        }
        ClientController clientController = App.getClientController();
        if (clientController != null) client = clientController.getClient();
    }

    public ObservableList<WeatherCard> getWeatherCards() {
        return weatherCards;
    }

    public ObservableList<Card>[] getRows() {
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
            // Show power of rows
            sumOfRows[i] = gaming.getTable().getRows()[i].powerProperty();
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
        vetoCards();

        player1Lives.addListener((observableValue, number, t1) -> gaming.setPlayer2Wins(2 - (int) t1));
        player2Lives.addListener((observableValue, number, t2) -> gaming.setPlayer1Wins(2 - (int) t2));
        addListenerToRowsObservableLists();
    }

    private void addListenerToRowsObservableLists() {
        for (int i = 0; i < rows.length; i++) {
            int finalI = i;
            rows[i].addListener((ListChangeListener<Card>) change -> {
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

    private void addCardsToRow(int from, int to, List<? extends Card> list, int rowIndex) {
        for (int i = from; i < to; i++) {
            Card card = list.get(i);
            gaming.getTable().getRows()[rowIndex].placeCard(from, card);
        }
    }

    private void addAddedCardsToRow(ListChangeListener.Change<? extends Card> change, int rowIndex) {
        try {
            addCardsToRow(change.getFrom(), change.getTo(), change.getList(), rowIndex);
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Exception / Error in updating cards in the view occurred.", e);
        }
    }

    private void removeRemovedCardsFromRow(ListChangeListener.Change<? extends Card> change, int rowIndex) {
        assert change.getFrom() == change.getTo();
        rows[rowIndex].remove(change.getFrom(), change.getTo() + change.getRemovedSize());
    }

    private void replaceReplacedCardsInRow(ListChangeListener.Change<? extends Card> change, int rowIndex) {
        removeRemovedCardsFromRow(change, rowIndex);
        addAddedCardsToRow(change, rowIndex);
    }

    private void vetoCards() {

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
            Card card = notChosenCards1.get(randomIndex);
            card.setGameStatus(gaming);
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

    public void setChatText(String text) {

    }

    public void playLeaderCard(Card card) {
        // TODO
    }

    public void addSpellCardToTableLocally(UnitCard unitCard, int rowToPlay) {
        rows[rowToPlay - 1].add(unitCard);
    }

    /**
     * Updates plays the card, i.e. changes {@link #gaming} so that the card is added. Because the game status is
     * observable, the played card is also shown to the user.
     */
    public void addCardToTableLocally(Card card, int rowToPlay) {
        if (card instanceof UnitCard || card.getCardName() == CardName.DECOY) {
            addUnitCardToTableLocally(card, rowToPlay);
        } else if (card instanceof SpellCard spellCard) {
            addSpellCardToTableLocally(spellCard, rowToPlay);
        } else {
            throw new RuntimeException("This method is only called when the card is either a unit card," +
                    " or a spell card.");
        }
    }

    private void addSpellCardToTableLocally(SpellCard spellCard, int rowToPlay) {
        gaming.getTable().addSpell(spellCard, rowToPlay - 1);
    }

    private void addUnitCardToTableLocally(Card card, int rowToPlay) {
        rows[rowToPlay - 1].add(card);
    }

    public void removeCardFromTableLocally(int rowNumber, int cardIndexInRow) {
        Card cardRemoved = rows[rowNumber - 1].remove(cardIndexInRow);
    }

    public void addWeatherToTableLocally(WeatherCard weatherCard) {
        gaming.getTable().addWeather(weatherCard);
        player1.getDeck().getInHandCards().remove(weatherCard);
        weatherCards.add(weatherCard);
        weatherCard.doAbility(this);
    }

    /**
     * This method is used only for spell cards and unit cards. use "TODO: find the method"
     * method to play a weather card or the leader card.
     */
    public void requestPLayingCard(Card card, int rowToPlay) {
        if (card instanceof SpellCard && card.getCardName() != CardName.DECOY) {
            requestPlayingCommanderHorn(card, rowToPlay);
        } else if (card instanceof UnitCard unitCard) {
            if (card.getPossibleRowsToBePlayed().getPossibleRowNumbers().contains(rowToPlay)) {
                addUnitCardToTableLocally(unitCard, rowToPlay);
                getPlayer1InHandCards().remove(unitCard);
                // TODO: do card ability
                // TODO: SpyChecking
                ClientController clientController = App.getClientController();
                Client client = clientController.getClient();
                client.sendMassage("place card " + card.getName().replaceAll(" ", "-") + " on row "
                        + rowToPlay);
            }
        }
    }

    /**
     * Plays {@link CardName#COMMANDER_HORN Commander's Horn} card or any other {@link SpellCard}.
     */
    public void requestPlayingCommanderHorn(Card card, int rowToPlay) {
        if (!(card instanceof SpellCard spellCard) || card.getCardName() == CardName.DECOY) return;
        addSpellCardToTableLocally(spellCard, rowToPlay);
        GetAbility.getAbility(spellCard, gaming, player1, handleRounds);
        getPlayer1InHandCards().remove(spellCard);
    }

    /**
     * @param card The weather card that the user wants to play. If it isn't a weather card, the method doesn't do
     *             anything.
     */
    public void requestPlayingWeatherCard(Card card) {
        if (!(card instanceof WeatherCard weatherCard)) return;
        addWeatherToTableLocally(weatherCard);
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
        ClientController clientController = App.getClientController();
        Client client = clientController.getClient();
        client.setTurn(false);
        handleRounds.passround();
//        ClientController clientController = App.getClientController();
//        client = clientController.getClient();
        client.sendMassage("pass");
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
        card.setGameStatus(gaming);
        player1.getDeck().getInHandCards().add(card);
    }

    public void setIsMyTurn(boolean isMyTurn) {
        this.isMyTurn.set(isMyTurn);
    }
}
