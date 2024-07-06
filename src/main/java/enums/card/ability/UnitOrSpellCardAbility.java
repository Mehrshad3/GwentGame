package enums.card.ability;

import controller.GameController;
import model.faction.Card;

import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

@Deprecated
public enum UnitOrSpellCardAbility implements CardAbility {
    COMMANDER_HORN(null),
    DECOY(null),
    MEDIC((gaming, card) -> {
        Scanner scanner = new Scanner(System.in); // Cheat code TODO: remove this
        List<Card> discardPile = gaming.getPlayer1DiscardPile();
        if (discardPile.isEmpty()) return;
        Card cardToPlay = discardPile.get(0); // TODO: ask user which card they want to change
        discardPile.remove(cardToPlay);
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
    private final BiConsumer<GameController, Card> action;

    UnitOrSpellCardAbility(BiConsumer<GameController, Card> action) {
        this.action = action;
    }

    @Override
    public void doAction(GameController gaming, Card card) {
        this.action.accept(gaming, card);
    }
}