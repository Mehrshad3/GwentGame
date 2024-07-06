package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class TorrentialRainAbilityDoing extends Ability {
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
        DoAbilityOnARow(1);
        DoAbilityOnARow(6);
        gameStatus.getHandleRounds().getNextDoingMethods().remove(this);
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
