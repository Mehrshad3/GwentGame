package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.faction.Card;
import model.faction.Faction;
import model.faction.LeaderCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    public LeaderCard getCurrentLeaderCard() {
        return rawDeck.getCurrentLeaderCard();
    }

    public void setCurrentLeaderCard(LeaderCard currentLeaderCard) {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Deck deck)) return false;
        return Objects.equals(rawDeck, deck.rawDeck)
                && Objects.equals(inHandCards, deck.inHandCards)
                && Objects.equals(discardCards, deck.discardCards)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawDeck, inHandCards, discardCards);
    }
}

class RawDeck implements Serializable {
    private final Faction faction;
    private final ArrayList<Card> notChosenCards = new ArrayList<>();
    private LeaderCard currentLeaderCard;

    RawDeck(Faction faction) {
        this.faction = faction;
//        this.currentLeaderCard = faction.leaderCards()[0].getNewCard();
    }

    public LeaderCard getCurrentLeaderCard() {
        return currentLeaderCard;
    }

    public void setCurrentLeaderCard(LeaderCard currentLeaderCard) {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof RawDeck rawDeck)) return false;
        return faction == rawDeck.faction
                && Objects.equals(notChosenCards, rawDeck.notChosenCards)
                && Objects.equals(currentLeaderCard, rawDeck.currentLeaderCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faction, notChosenCards, currentLeaderCard);
    }
}
