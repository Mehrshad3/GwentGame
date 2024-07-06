package enums.card.ability;

import controller.GameController;
import model.faction.Card;

public interface CardAbility {
    void doAction(GameController gaming, Card card);
}