package controller;

import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.ObservableTable;
import model.faction.Card;
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
        ObservableList<Card> cards = wantedRow.getCards();
        for (Card card0 : cards) {
            if (!(card0 instanceof UnitCard unitCard) || unitCard.isHero()) {

            } else {
                int power = unitCard.getPower();
                unitCard.setPower(2 * power);
            }
        }
    }
}
