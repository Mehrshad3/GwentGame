package model.faction;

import enums.card.CardName;

public enum Faction {
    MONSTERS(null, "monsters"),
    EMPIRE_NILFGAARDIAN(null, "nilfgaard"),
    SKELLIGE(null, "skellige"),
    SCOIA_TAELL(null, "scoiatael"),
    NORTHERN_REALMS(null, "realms"),
    NEUTRAL(null, "neutral"), // To store special cards and other neutral cards
    ;
    private final Runnable ability;
    private final String nameInResources;

    Faction(Runnable ability, String nameInResources) {
        this.ability = ability;
        this.nameInResources = nameInResources;
    }

    public CardName[] leaderCards() {
        return CardName.getLeadersOfFaction(this);
    }

    public String getName() {
        return nameInResources;
    }
}