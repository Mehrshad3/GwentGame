package enums.card.ability;

import javafx.scene.Scene;
import model.App;
import model.GameStatus;
import model.Table;

public interface CardAbility {
    static GameStatus getGaming() {
        return App.getAppObject().getGaming();
    }

    static Table getTable() {
        return getGaming().getTable();
    }

    void doAction(Scene scene);
}