package model;

public class Player {
    protected String name;
    protected String nickname;
    protected Deck deck = new Deck();

    public Player(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    public final String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getNumberOfSoldiers() {
        return deck.getNumberOfSoldiers();
    }
}
