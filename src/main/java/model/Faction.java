package model;

public enum Faction {
    MONSTERS(""),
    EMPIRE_NILFGAARDIAN(""),
    REALMS_NORTHERN(""),
    SCOIA_TAEL(""),
    SKELLIGE(""),
    ;
    private final String ability;

    Faction(String ability) {
        this.ability = ability;
    }
}
