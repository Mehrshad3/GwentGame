package model;

import model.faction.Card;
import model.faction.Faction;

public class Deck {
    private Card[] discardCards;
    private Card[] inHandCards;
    private Card currentLeaderCard;
    private Card[] leaderCards;
    private Faction faction;

    public Card[] getDiscardCards() {
        return discardCards;
    }

    public Card[] getInHandCards() {
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

    public void setInHandCards(Card[] inHandCards) {
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
}
