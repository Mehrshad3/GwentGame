package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class SunOfMedellAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }
    public void DoAbilityOnACard(UnitCard unitCard){
        unitCard.commanderboostpower=true;
    }
    public void DoAbilityOnARow(int row){
        ObservableRow[] rows=gameStatus.getTable().getRows();
        ObservableRow wantedrow=rows[row];
        for(UnitCard unitCard: wantedrow.getCards()){
            DoAbilityOnACard(unitCard);
        }
    }
    @Override
    public void DoCardAbility() {
        DoAbilityOnARow(5);
        DoAbilityOnARow(2);
        //Ranged combat
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
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
