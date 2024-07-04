package enums.card;

import java.util.HashSet;
import java.util.List;

public enum PossibleRowsToPlayCard {
    CLOSE_COMBAT(new HashSet<>(List.of(3))), // The same as melee row
    RANGED(new HashSet<>(List.of(2))),
    SIEGE(new HashSet<>(List.of(1))),
    AGILE(new HashSet<>(List.of(1, 3))),
    SPECIAL(null), // I don't specify the rows possible for special cards (i.e. weathers and spells) explicitly!
    LEADER_SPOT(new HashSet<>(List.of(0))),
    ;
    private final HashSet<Integer> possibleRowNumbers;

    PossibleRowsToPlayCard(HashSet<Integer> possibleRowNumbers) {
        this.possibleRowNumbers = possibleRowNumbers;
    }

    public HashSet<Integer> getPossibleRowNumbers() {
        return possibleRowNumbers;
    }
}
