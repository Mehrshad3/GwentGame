package model;

import model.faction.Card;
import model.faction.Faction;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {

    private ArrayList<Card> discardCards;
    private ArrayList<Card> inHandCards;
    private Card currentLeaderCard;
    private ArrayList<Card> leaderCards;
    private Faction faction;

    public ArrayList<Card> getDiscardCards() {
        return discardCards;
    }

    public void setDiscardCards(ArrayList<Card> discardCards) {
        this.discardCards = discardCards;
    }

    public ArrayList<Card> getInHandCards() {
        if(inHandCards == null){
            inHandCards = new ArrayList<>();
        }
        return inHandCards;
    }

    public void setInHandCards(ArrayList<Card> inHandCards) {
        this.inHandCards = inHandCards;
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

    public void addCardToHand(Card card) {
        if(inHandCards == null){
            inHandCards = new ArrayList<>();
        }
        inHandCards.add(card);
    }

    void moveToDiscardPile(Card card) {
        inHandCards.remove(card);
        discardCards.add(card);
    }
}
