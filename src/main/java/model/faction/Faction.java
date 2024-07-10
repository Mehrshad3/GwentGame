package model.faction;

import enums.card.CardName;

public enum Faction {
    MONSTERS(null, "monsters","monsters"),
    EMPIRE_NILFGAARDIAN(null, "nilfgaard","empire nilfgard"),
    SKELLIGE(null, "skellige","skellige"),
    SCOIA_TAELL(null, "scoiatael","sociataell"),
    NORTHERN_REALMS(null, "realms","northern realms"),
    NEUTRAL(null, "neutral", "neutral"), // To store special cards and other neutral cards
    ;
    private final Runnable ability;
    private final String nameInResources;
    public String name;

    Faction(Runnable ability, String nameInResources ,String name) {
        this.ability = ability;
        this.nameInResources = nameInResources;
        this.name=name;
    }

    public CardName[] leaderCards() {
        return CardName.getLeadersOfFaction(this);
    }

    public String getName() {
        return nameInResources;
    }
}