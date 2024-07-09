package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class SkelligeStormAbilityDoing extends Ability {
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
        ObservableRow wantedrow=rows[row];
        for(UnitCard card : wantedrow.getCards()){
            DoAbilityOnACard(card);
        }
    }
    @Override
    public void DoCardAbility() {
        TorrentialRainAbilityDoing torrentialRainAbilityDoing=new TorrentialRainAbilityDoing();
        torrentialRainAbilityDoing.setmaincard(maincard);
        torrentialRainAbilityDoing.setGameStatus(gameStatus);
        ImpenetrableFogAbilityDoing impenetrableFogAbilityDoing=new ImpenetrableFogAbilityDoing();
        impenetrableFogAbilityDoing.setGameStatus(gameStatus);
        impenetrableFogAbilityDoing.setmaincard(maincard);
        gameStatus.getHandleRounds().getNextDoingMethods().add(torrentialRainAbilityDoing);
        gameStatus.getHandleRounds().getNextDoingMethods().add(impenetrableFogAbilityDoing);
        torrentialRainAbilityDoing.DoCardAbility();
        impenetrableFogAbilityDoing.DoCardAbility();

    }

    @Override
    public Ability Copy(Card card) {
        SkelligeStormAbilityDoing skelligeStormAbilityDoing=new SkelligeStormAbilityDoing();
        skelligeStormAbilityDoing.setmaincard(maincard);
        skelligeStormAbilityDoing.setStatus(status);
        skelligeStormAbilityDoing.setGameStatus(gameStatus);
        return skelligeStormAbilityDoing;
    }
}
