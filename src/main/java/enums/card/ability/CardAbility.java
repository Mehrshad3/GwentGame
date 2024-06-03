package enums.card.ability;

import javafx.scene.Scene;
import model.App;
import model.GameStatus;
import model.Table;
import model.faction.Card;

public interface CardAbility {
    static GameStatus getGaming() {
        return App.getAppObject().getGaming();
    }

    static Table getTable() {
        return getGaming().getTable();
    }

    void doAction(GameStatus gaming, Card card);
}