package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class ClearWeatherAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void DoAbilityOnACard(UnitCard card){
        card.setWeatherChanged(false);
    }
    public void DoAbilityOnARow(int row){
        ObservableRow[] rows=gameStatus.getTable().getRows();
        ObservableRow wantedrow=rows[row];
        for(UnitCard card : wantedrow.getCards()){
            DoAbilityOnACard(card);
        }
    }
    public void DoAbilityOnWholeTable(){
        ObservableRow[] rows = gameStatus.getTable().getRows();
        for (ObservableRow wantedrow : rows) {
            for(UnitCard card : wantedrow.getCards()){
                DoAbilityOnACard(card);
            }
        }
    }
    @Override
    public void DoCardAbility() {
        DoAbilityOnWholeTable();
        //TODO
    }

    @Override
    public Ability Copy(Card card) {
        return null;
    }
}
