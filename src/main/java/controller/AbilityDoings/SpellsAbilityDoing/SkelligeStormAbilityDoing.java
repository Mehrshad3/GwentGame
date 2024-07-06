package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class SkelligeStormAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
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
