package model.faction;

import enums.card.CardName;

public enum Faction {
    MONSTERS(null),
    EMPIRE_NILFGAARDIAN(null),
    SKELLIGE(null),
    SCOIA_TAELL(null),
    NORTHERN_REALMS(null),
    NEUTRAL(null), // To store special cards and other neutral cards
    ;
    private final Runnable ability;

    Faction(Runnable ability) {
        this.ability = ability;
    }

    public CardName[] leaderCards() {
        return CardName.getLeadersOfFaction(this);
    }
}