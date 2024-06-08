package model;

import model.faction.Faction;

public class Player {
    protected String name;
    protected String nickname;
    protected transient Deck deck;
    private Faction faction;

    public Player(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
        this.deck = new Deck(Faction.MONSTERS);
    }

    public final String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Deck getDeck() {
        return deck;
    }

    void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getNumberOfSoldiers() {
        return deck.getNumberOfSoldiers();
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        Faction newFaction = switch (faction) {
            case "monsters" -> Faction.MONSTERS;
            case "nilfgaard" -> Faction.EMPIRE_NILFGAARDIAN;
            case "skellige" -> Faction.SKELLIGE;
            case "scoiatael" -> Faction.SCOIA_TAELL;
            case "realms" -> Faction.NORTHERN_REALMS;
            default -> throw new RuntimeException("Faction name is not in hard-coded strings.");
        };
        setFaction(newFaction);
    }

    private void setFaction(Faction faction) {
        if (this.deck.getFaction() != faction) this.deck = new Deck(faction);
        this.faction = faction;
    }
}
