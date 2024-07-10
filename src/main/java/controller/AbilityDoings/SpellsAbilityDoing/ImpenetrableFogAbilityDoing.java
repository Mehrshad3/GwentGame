package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class ImpenetrableFogAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void DoAbilityOnACard(UnitCard card){
        card.setWeatherChanged(true);
    }

    public void DoAbilityOnARow(int row){
        ObservableRow[] rows = gameStatus.getTable().getRows();
        ObservableRow wantedrow = rows[row];
        for (Card card : wantedrow.getCards()) {
            if (card instanceof UnitCard unitCard) DoAbilityOnACard(unitCard);
        }
    }
    

    @Override
    public void DoCardAbility() {
        DoAbilityOnARow(2);
        DoAbilityOnARow(5);
    }

    @Override
    public Ability Copy(Card card) {
        ImpenetrableFogAbilityDoing impenetrableFogAbilityDoing=new ImpenetrableFogAbilityDoing();
        impenetrableFogAbilityDoing.setmaincard(maincard);
        impenetrableFogAbilityDoing.setStatus(status);
        impenetrableFogAbilityDoing.setGameStatus(gameStatus);
        return impenetrableFogAbilityDoing;
    }
}
