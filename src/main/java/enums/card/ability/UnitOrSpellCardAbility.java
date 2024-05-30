package enums.card.ability;

import javafx.scene.Scene;
import model.GameStatus;
import model.faction.Card;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public enum UnitOrSpellCardAbility implements CardAbility {
    COMMANDER_HORN(null),
    DECOY(null),
    MEDIC((scene) -> {
        Scanner scanner = new Scanner(System.in); // Cheat code TODO: remove this
        GameStatus gaming = CardAbility.getGaming();
        ArrayList<Card> discardPile = gaming.getDiscardPile(gaming.getPlayer1());
        if (discardPile.isEmpty()) return;
        Card cardToPlay = discardPile.get(0); // TODO: ask user which card they want to change
        gaming.removeFromDiscardPile(cardToPlay, gaming.getPlayer1());
        // Menu.GameMenu.getMenuController()
    }),
    MORAL_BOOST(null),
    MUSTER(null),
    SPY(null),
    TIGHT_BOND(null),
    SCORCH(null),
    BERSERKER(null),
    MARDROEME(null),
    TRANSFORMERS(null),
    ;
    private final Consumer<Scene> action;

    UnitOrSpellCardAbility(Consumer<Scene> action) {
        this.action = action;
    }

    @Override
    public void doAction(Scene scene) {
        this.action.accept(scene);
    }
}