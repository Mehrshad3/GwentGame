package controller.AbilityDoings.SpellsAbilityDoing;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class BitingFrostAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void DoAbilityOnACard(UnitCard card){
        card.setBeforeweatherchangepower(card.getPower());
        card.setPower(1);
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
        DoAbilityOnARow(3);
        DoAbilityOnARow(4);
        gameStatus.getHandleRounds().getNextDoingMethods().remove(this);
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
