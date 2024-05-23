package model.faction;

public enum Faction {
    MONSTERS("", MonstersLeaderType.class),
    EMPIRE_NILFGAARDIAN("", NilfgaardianEmpireLeaderType.class),
    REALMS_NORTHERN("", NorthernRealmLeaderType.class),
    SCOIA_TAELL("", scoiaTaellLeaderType.class),
    SKELLIGE("", skelligeLeaderType.class),
    ;
    public final Card commander;
    public final Class<? extends leaderCardType> leaderCardTypes;
    private final String ability;

    Faction(String ability, Class<? extends leaderCardType> leaderCardTypes) {
        LeaderCard commander = null; // TODO
        this.ability = ability;
        this.commander = commander;
        this.leaderCardTypes = leaderCardTypes;
    }

    private enum NorthernRealmLeaderType implements leaderCardType {
        THE_SIEGE_MASTER,
        THE_STEEL_FORGED,
        KING_OF_TEMERIA,
        LORD_COMMANDER_OF_THE_NORTH,
        SON_OF_MEDELL,
    }

    private enum NilfgaardianEmpireLeaderType implements leaderCardType {
        THE_WHITE_FLAME,
        HIS_IMPERIAL_MAJESTY,
        EMPEROR_OF_NILFGAARD,
        THE_RELENTLESS,
        INVADER_OF_THE_NORTH,
    }

    private enum MonstersLeaderType implements leaderCardType {
        BRINGER_OF_DEATH,
        KING_OF_THE_WILD_HUNT,
        DESTROYER_OF_WORLDS,
        COMMANDER_OF_THE_RED_RIDERS,
        THE_TREACHEROUS,
    }

    private enum scoiaTaellLeaderType implements leaderCardType {
        QUEEN_OF_DOL_BLATHANNA,
        THE_BEAUTIFUL,
        DAISY_OF_THE_VALLEY,
        PURE_BLOOD_ELF,
        HOPE_OF_THE_AEN_SEIDHE,
    }

    private enum skelligeLeaderType implements leaderCardType {
        CRACH_AN_CRAITE,
        KING_BRAN,
    }

    private interface leaderCardType {
    }
}
