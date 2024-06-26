package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.faction.Card;
import model.faction.Faction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Deck {
    private final RawDeck rawDeck;
    private final ObservableList<Card> inHandCards;
    private final ObservableList<Card> discardCards;

    Deck(Faction faction) {
        this(new RawDeck(faction));
    }

    Deck(RawDeck rawDeck) {
        this.rawDeck = rawDeck;
        this.inHandCards = FXCollections.observableArrayList();
        this.discardCards = FXCollections.observableArrayList();
    }

    public Faction getFaction() {
        return rawDeck.getFaction();
    }

    public ObservableList<Card> getDiscardCards() {
        return this.discardCards;
    }

    public void setDiscardCards(Collection<Card> newDiscardCards) {
        this.discardCards.setAll(newDiscardCards);
    }

    public ObservableList<Card> getInHandCards() {
        return inHandCards;
    }

    public void setInHandCards(Collection<? extends Card> newInHandCards) {
        this.inHandCards.setAll(newInHandCards);
    }

    public Card getCurrentLeaderCard() {
        return rawDeck.getCurrentLeaderCard();
    }

    public void setCurrentLeaderCard(Card currentLeaderCard) {
        this.rawDeck.setCurrentLeaderCard(currentLeaderCard);
    }

    public int getNumberOfSoldiers() {
        return 0; // TODO
    }

    public int getNumberOfCardsInDeck() {
        return rawDeck.getNumberOfCardsInDeck();
    }

    public ArrayList<Card> getCardsInDeck() {
        return rawDeck.getCardsInDeck();
    }

    public void setNotChosenCards(ArrayList<? extends Card> newDeck) {
        rawDeck.setNotChosenCards(newDeck);
    }

    public void addCardToHand(Card card) {
        rawDeck.removeCardFromDeck(card);
        inHandCards.add(card);
    }

    public void moveToDiscardPile(Card card) {
        inHandCards.remove(card);
        discardCards.add(card);
    }

    /**
     * Used to add cards to the deck in PreGameMenu.
     */
    public void addCardToDeck(Card card) {
        rawDeck.addCardToDeck(card);
    }

    RawDeck getDeckAsSerializable() {
        return rawDeck;
    }
}

class RawDeck implements Serializable {
    private final Faction faction;
    private final ArrayList<Card> notChosenCards = new ArrayList<>();
    private Card currentLeaderCard;

    RawDeck(Faction faction) {
        this.faction = faction;
//        this.currentLeaderCard = faction.leaderCards()[0].getNewCard();
    }

    public Card getCurrentLeaderCard() {
        return currentLeaderCard;
    }

    public void setCurrentLeaderCard(Card currentLeaderCard) {
        this.currentLeaderCard = currentLeaderCard;
    }

    public int getNumberOfCardsInDeck() {
        return notChosenCards.size();
    }

    public ArrayList<Card> getCardsInDeck() {
        return notChosenCards;
    }

    public void setNotChosenCards(ArrayList<? extends Card> newDeck) {
        // TODO
    }

    public void removeCardFromDeck(Card card) {
        this.notChosenCards.remove(card);
    }

    /**
     * Used to add cards to the deck in PreGameMenu.
     */
    public void addCardToDeck(Card card) {
        this.notChosenCards.add(card);
    }

    public Faction getFaction() {
        return this.faction;
    }
}
