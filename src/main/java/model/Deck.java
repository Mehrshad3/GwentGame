package model;

public class Deck {
    private Card[] discardCards;
    private Card[] inHandCards;
    private Card player1CommanderCard;
    private Card player2CommanderCard;
    private Card currentLeaderCard;
    private Card[] leaderCards;

    public Card[] getDiscardCards() {
        return discardCards;
    }

    public Card[] getInHandCards() {
        return inHandCards;
    }

    public Card getPlayer1CommanderCard() {
        return player1CommanderCard;
    }

    public Card getPlayer2CommanderCard() {
        return player2CommanderCard;
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

    public void setPlayer1CommanderCard(Card player1CommanderCard) {
        this.player1CommanderCard = player1CommanderCard;
    }

    public void setPlayer2CommanderCard(Card player2CommanderCard) {
        this.player2CommanderCard = player2CommanderCard;
    }

    public void setCurrentLeaderCard(Card currentLeaderCard) {
        this.currentLeaderCard = currentLeaderCard;
    }

    public void setLeaderCards(Card[] leaderCards) {
        this.leaderCards = leaderCards;
    }
}
