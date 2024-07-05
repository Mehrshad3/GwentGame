package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class ClearWeatherAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void DoAbilityOnACard(UnitCard card){
        card.setWeatherChanged(false);
    }
    public void DoAbilityOnARow(int row){
        Row[] rows=gameStatus.getTable().getRows();
        Row wantedrow=rows[row];
        for(UnitCard card : wantedrow.getCards()){
            DoAbilityOnACard(card);
        }
    }
    public void DoAbilityOnWholeTable(){
        Row[] rows=gameStatus.getTable().getRows();
        for(Row wantedrow:rows){
            for(UnitCard card : wantedrow.getCards()){
                DoAbilityOnACard(card);
            }
        }
    }
    @Override
    public void DoCardAbility() {
        DoAbilityOnWholeTable();
    }

    @Override
    public Ability Copy(Card card) {
        return null;
    }
}
