package model;

public class Row {
    private final Card[] cards = new Card[9];
    private Card specialCard = null;
    private boolean specialCardExists = false;

    public void placeCard(Card card) {
        // TODO
    }

    public void removeCard(Card card) {
        // TODO
    }

    public void setSpecialCard(Card specialCard) {
        this.specialCard = specialCard;
    }

    public void setSpecialCardExists(boolean specialCardExists) {
        this.specialCardExists = specialCardExists;
    }

    public Card[] getCards() {
        return cards;
    }

    public Card getSpecialCard() {
        return specialCard;
    }

    public boolean doesSpecialCardExist() {
        return specialCardExists;
    }
}
