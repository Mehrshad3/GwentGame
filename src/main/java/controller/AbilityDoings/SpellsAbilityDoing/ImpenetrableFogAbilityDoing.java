package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class ImpenetrableFogAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public void DoAbilityOnACard(UnitCard card){
        card.setWeatherChanged(true);
    }
    public void DoAbilityOnARow(int row){
        Row[] rows=gameStatus.getTable().getRows();
        Row wantedrow=rows[row];
        for(UnitCard card : wantedrow.getCards()){
            DoAbilityOnACard(card);
        }
    }
    

    @Override
    public void DoCardAbility() {
        DoAbilityOnARow(2);
        DoAbilityOnARow(5);
        gameStatus.getHandleRounds().getNextDoingMethods().remove(this);
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
