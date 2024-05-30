package model.faction;

public enum Faction {
    MONSTERS(null),
    EMPIRE_NILFGAARDIAN(null),
    REALMS_NORTHERN(null),
    SCOIA_TAELL(null),
    SKELLIGE(null),
    NEUTRAL(null), // To store special cards and other neutral cards
    ;
    private final Runnable ability;

    Faction(Runnable ability) {
        this.ability = ability;
    }
}