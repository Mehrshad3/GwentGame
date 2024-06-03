package enums.card.ability;

import model.GameStatus;
import model.faction.Card;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiConsumer;

public enum UnitOrSpellCardAbility implements CardAbility {
    COMMANDER_HORN(null),
    DECOY(null),
    MEDIC((gaming, card) -> {
        Scanner scanner = new Scanner(System.in); // Cheat code TODO: remove this
        ArrayList<Card> discardPile = gaming.getDiscardPile(gaming.getPlayer1());
        if (discardPile.isEmpty()) return;
        Card cardToPlay = discardPile.get(0); // TODO: ask user which card they want to change
        gaming.removeFromDiscardPile(cardToPlay, gaming.getPlayer1());
        // Menu.GameMenu.getMenuController()
    }),
    MORALE_BOOST(null),
    MUSTER(null),
    SPY(null),
    TIGHT_BOND(null),
    GLOBAL_SCORCH(null),
    OPPONENT_COMBAT_SCORCH(null),
    RANGED_SCORCH(null),
    SIEGE_SCORCH(null),
    BERSERKER(null),
    MARDROEME(null),
    TRANSFORMERS(null),
    ;
    private final BiConsumer<GameStatus, Card> action;

    UnitOrSpellCardAbility(BiConsumer<GameStatus, Card> action) {
        this.action = action;
    }

    @Override
    public void doAction(GameStatus gaming, Card card) {
        this.action.accept(gaming, card);
    }
}