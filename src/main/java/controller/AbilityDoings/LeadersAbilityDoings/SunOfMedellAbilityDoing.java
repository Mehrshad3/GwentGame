package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class SunOfMedellAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public void DoAbilityOnACard(UnitCard unitCard){
        unitCard.commanderboostpower=true;
    }
    public void DoAbilityOnARow(int row){
        Row[] rows=gameStatus.getTable().getRows();
        Row wantedrow=rows[row];
        for(UnitCard unitCard: wantedrow.getCards()){
            DoAbilityOnACard(unitCard);
        }
    }
    @Override
    public void DoCardAbility() {
        DoAbilityOnARow(5);
        DoAbilityOnARow(2);
    }

    @Override
    public Ability Copy(Card card) {
        SunOfMedellAbilityDoing sunOfMedellAbilityDoing=new SunOfMedellAbilityDoing();
        sunOfMedellAbilityDoing.setGameStatus(gameStatus);
        sunOfMedellAbilityDoing.setmaincard(maincard);
        sunOfMedellAbilityDoing.setStatus(status);
        sunOfMedellAbilityDoing.setPlayer(player);
        return sunOfMedellAbilityDoing;
    }
}
