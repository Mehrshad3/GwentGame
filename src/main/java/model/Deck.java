package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.faction.Card;
import model.faction.Faction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Deck implements Serializable {
    private final ArrayList<Card> notChosenCards = new ArrayList<>();
    private final Faction faction;
    private final ObservableList<Card> inHandCards = FXCollections.observableArrayList();
    private ArrayList<Card> discardCards;
    private Card currentLeaderCard;
    private ArrayList<Card> leaderCards;

    Deck(Faction faction) {
        this.faction = faction;
//        this.currentLeaderCard = faction.leaderCards()[0].getNewCard();
    }

    public ArrayList<Card> getDiscardCards() {
        return discardCards;
    }

    public void setDiscardCards(ArrayList<Card> discardCards) {
        this.discardCards = discardCards;
    }

    public ObservableList<Card> getInHandCards() {
        return inHandCards;
    }

    public void setInHandCards(Collection<? extends Card> newInHandCards) {
        this.inHandCards.setAll(newInHandCards);
    }

    public Card getCurrentLeaderCard() {
        return currentLeaderCard;
    }

    public void setCurrentLeaderCard(Card currentLeaderCard) {
        this.currentLeaderCard = currentLeaderCard;
    }

    public ArrayList<Card> getLeaderCards() {
        return leaderCards;
    }

    public void setLeaderCards(ArrayList<Card> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public int getNumberOfSoldiers() {
        return 0; //TODO
    }

    public int getNumberOfCardsInDeck() {
        return notChosenCards.size();
    }

    public ArrayList<Card> getCardsInDeck() {
        return notChosenCards;
    }

    public void setNotChosenCards(Collection<? extends Card> newDeck) {
        this.inHandCards.setAll(newDeck);
    }

    public void addCardToHand(Card card) {
        notChosenCards.remove(card);
        inHandCards.add(card);
    }

    void moveToDiscardPile(Card card) {
        inHandCards.remove(card);
        discardCards.add(card);
    }

    /**
     * Used to add cards to the deck in PreGameMenu.
     *
     * @return {@code true} if the card is added to deck successfully, otherwise, {@code false}.
     */
    public boolean addCardToDeck(Card card) {
        this.notChosenCards.add(card);
        return true;
    }

    public Faction getFaction() {
        return this.faction;
    }
}
