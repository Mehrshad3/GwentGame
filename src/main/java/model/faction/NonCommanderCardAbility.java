package model.faction;

import java.util.Scanner;
import java.util.function.Consumer;

public enum NonCommanderCardAbility implements CardAbility {
    COMMANDER_HORN(null),
    MARDROEME(null),
    DECOY(null),
    MEDIC(null),
    MORAL_BOOST(null),
    MUSTER(null),
    SPY(null),
    TIGHT_BOND(null),
    ;
    private final Consumer<Scanner> action;

    NonCommanderCardAbility(Consumer<Scanner> action) {
        this.action = action;
    }

    @Override
    public void doAction(Scanner scanner) {
        this.action.accept(scanner);
    }
}