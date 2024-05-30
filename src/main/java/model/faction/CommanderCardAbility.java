package model.faction;

import java.util.Scanner;
import java.util.function.Consumer;

public enum CommanderCardAbility implements CardAbility {
    // Northern Realms Faction
    THE_SIEGE_MASTER_ABILITY_PERFORMER(null),
    THE_STEEL_FORGED_ABILITY_PERFORMER(null),
    KING_OF_TEMERIA_ABILITY_PERFORMER(null),
    LORD_COMMANDER_OF_THE_NORTH_ABILITY_PERFORMER(null),
    SON_OF_MEDELL_ABILITY_PERFORMER(null),
    // Nilfgaardian Empire
    THE_WHITE_FLAME_ABILITY_PERFORMER(null),
    HIS_IMPERIAL_MAJESTY_ABILITY_PERFORMER(null),
    EMPEROR_OF_NILFGAARD_ABILITY_PERFORMER(null),
    THE_RELENTLESS_ABILITY_PERFORMER(null),
    INVADER_OF_THE_NORTH_ABILITY_PERFORMER(null),
    // Monsters Leader
    BRINGER_OF_DEATH_ABILITY_PERFORMER(null),
    KING_OF_THE_WILD_HUNT_ABILITY_PERFORMER(null),
    DESTROYER_OF_WORLDS_ABILITY_PERFORMER(null),
    COMMANDER_OF_THE_RED_RIDERS_ABILITY_PERFORMER(null),
    THE_TREACHEROUS_ABILITY_PERFORMER(null),
    // Scoia'Taell faction
    QUEEN_OF_DOL_BLATHANNA_ABILITY_PERFORMER(null),
    THE_BEAUTIFUL_ABILITY_PERFORMER(null),
    DAISY_OF_THE_VALLEY_ABILITY_PERFORMER(null),
    PURE_BLOOD_ELF_ABILITY_PERFORMER(null),
    HOPE_OF_THE_AEN_SEIDHE_ABILITY_PERFORMER(null),
    // Skellige
    CRACH_AN_CRAITE_ABILITY_PERFORMER(null),
    KING_BRAN_ABILITY_PERFORMER(null),

    ;
    private final Consumer<Scanner> action;

    CommanderCardAbility(Consumer<Scanner> action) {
        this.action = action;
    }

    @Override
    public void doAction(Scanner scanner) {
        this.action.accept(scanner);
    }
}