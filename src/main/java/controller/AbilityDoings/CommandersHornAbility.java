package controller.AbilityDoings;

import model.GameStatus;
import model.Row;
import model.Table;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.List;

public class CommandersHornAbility {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void DoAbilityOnRow(int row){
        Table table=gameStatus.getTable();
        Row[] rows=table.getRows();
        Row wantedrow=rows[row];
        List<UnitCard> cards = wantedrow.getCards();
        for (UnitCard card0 : cards) {
            DoAbilityOnCard(card0);
        }
    }

    public void DoAbilityOnCardArbitrary(Card card, Boolean isHero) {
        if (isHero) {

        } else if (card instanceof UnitCard) {
            int power = ((UnitCard) card).getPower();
            ((UnitCard) card).setPower(2 * power);
        }
    }

    public void DoAbilityOnCard(Card card) {
        if (card instanceof UnitCard && ((UnitCard) card).isHero) { // Checks if the card is a Hero or not.

        } else {
//            int power = card.getPower();
//            card.setPower(2 * power);
        }
    }
}
