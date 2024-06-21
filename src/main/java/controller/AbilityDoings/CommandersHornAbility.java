package controller.AbilityDoings;

import model.GameStatus;
import model.Row;
import model.Table;
import model.faction.Card;

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
        Card[] cards=wantedrow.getCards();
        for(Card card0:cards){
        DoAbilityOnCard(card0);
        }
    }
    public void DoAbilityOnCardArbitrary(Card card,Boolean ishero) {
        if (ishero) {

        } else {
            //TODO:Mehrshad must define seter geter for Card,s power
//            int power = card.getpower();
//            card.setpower(2 * power);
        }
    }
    public void DoAbilityOnCard(Card card) {
        if (false/*TODO:Checking card is a Hero or not*/) {

        } else {
//            int power = card.getpower();
//            card.setpower(2 * power);
        }
    }

}
