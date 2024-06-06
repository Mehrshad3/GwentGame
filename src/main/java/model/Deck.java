package model;

import model.faction.Card;
import model.faction.Faction;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
    private Card[] discardCards;
    private ArrayList<Card> inHandCards = new ArrayList<>();
    private Card currentLeaderCard;
    private Card[] leaderCards;
    private Faction faction;

    public Card[] getDiscardCards() {
        return discardCards;
    }

    public ArrayList<Card> getInHandCards() {
        return inHandCards;
    }

    public Card getCurrentLeaderCard() {
        return currentLeaderCard;
    }

    public Card[] getLeaderCards() {
        return leaderCards;
    }

    public void setDiscardCards(Card[] discardCards) {
        this.discardCards = discardCards;
    }

    public void setInHandCards(ArrayList<Card> inHandCards) {
        this.inHandCards = inHandCards;
    }

    public void setCurrentLeaderCard(Card currentLeaderCard) {
        this.currentLeaderCard = currentLeaderCard;
    }

    public void setLeaderCards(Card[] leaderCards) {
        this.leaderCards = leaderCards;
    }

    public int getNumberOfSoldiers() {
        return 0; //TODO
    }
    public void addCardToHand(Card card){
        inHandCards.add(card);
    }
}
