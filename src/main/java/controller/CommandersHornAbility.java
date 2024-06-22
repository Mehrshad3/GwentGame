package controller;

import javafx.collections.ObservableList;
import model.GameStatus;
import model.Row;
import model.Table;
import model.faction.UnitCard;

public class CommandersHornAbility {
    public GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void DoAbility(int row) {
        Table table = gameStatus.getTable();
        Row[] rows = table.getRows();
        Row wantedRow = rows[row];
        ObservableList<UnitCard> cards = wantedRow.getCards();
        for (UnitCard card0 : cards) {
            if (card0.isHero()) {

            } else {
                int power = card0.getPower();
                card0.setPower(2 * power);
            }
        }
    }
}
