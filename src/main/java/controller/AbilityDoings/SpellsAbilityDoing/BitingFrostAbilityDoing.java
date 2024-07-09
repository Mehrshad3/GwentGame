package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import controller.GetRowNumberFromRowName;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class BitingFrostAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void DoAbilityOnACard(UnitCard card){
        card.setWeatherChanged(true);
    }
    public void DoAbilityOnARow(int row){
        ObservableRow[] rows=gameStatus.getTable().getRows();
        ObservableRow wantedrow = rows[row];
        for(UnitCard card : wantedrow.getCards()){
            DoAbilityOnACard(card);
        }
    }
    @Override
    public void DoCardAbility() {
        int closecombat1= GetRowNumberFromRowName.getrownumber(1,"close combat");
        int closecombat2=GetRowNumberFromRowName.getrownumber(2,"close combat");
        DoAbilityOnARow(closecombat1);
        DoAbilityOnARow(closecombat2);
    }

    @Override
    public Ability Copy(Card card) {
        BitingFrostAbilityDoing bitingFrostAbilityDoing=new BitingFrostAbilityDoing();
        bitingFrostAbilityDoing.setmaincard(card);
        bitingFrostAbilityDoing.setStatus(status);
        bitingFrostAbilityDoing.setGameStatus(gameStatus);
        return bitingFrostAbilityDoing;
    }
}
