package controller;

import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.ObservableTable;
import model.faction.UnitCard;

public class CommandersHornAbility {
    public ObservableGameStatus gameStatus;

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void DoAbility(int row) {
        ObservableTable table = gameStatus.getTable();
        ObservableRow[] rows = table.getRows();
        ObservableRow wantedRow = rows[row];
        ObservableList<UnitCard> cards = wantedRow.getUnitCards();
        for (UnitCard card0 : cards) {
            if (card0.isHero()) {

            } else {
                int power = card0.getPower();
                card0.setPower(2 * power);
            }
        }
    }
}
