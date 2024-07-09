package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import controller.GetRowNumberFromRowName;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class TorrentialRainAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void DoAbilityOnACard(UnitCard card){
        card.setWeatherChanged(true);
    }

    public void DoAbilityOnARow(int row){
        ObservableRow[] rows=gameStatus.getTable().getRows();
        ObservableRow wantedrow=rows[row];
        for (Card card : wantedrow.getCards()) {
            if (card instanceof UnitCard unitCard) DoAbilityOnACard(unitCard);
        }
    }

    @Override
    public void DoCardAbility() {
        int siege1= GetRowNumberFromRowName.getrownumber(1,"siege");
        int siege2=GetRowNumberFromRowName.getrownumber(2,"siege");
        DoAbilityOnARow(siege1);
        DoAbilityOnARow(siege2);
    }

    @Override
    public Ability Copy(Card card) {
        TorrentialRainAbilityDoing torrentialRainAbilityDoing=new TorrentialRainAbilityDoing();
        torrentialRainAbilityDoing.setmaincard(maincard);
        torrentialRainAbilityDoing.setStatus(status);
        torrentialRainAbilityDoing.setGameStatus(gameStatus);
        return torrentialRainAbilityDoing;
    }
}
