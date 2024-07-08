package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.GetRowNumberFromRowName;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class TheBeautifulAbilityDoing extends Ability {
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
        for(UnitCard unitCard:wantedrow.getCards()){
            DoAbilityOnACard(unitCard);
        }
    }
    @Override
    public void DoCardAbility() {
        int rangedcombat1= GetRowNumberFromRowName.getrownumber(1,"ranged combat");
        int rangedcombat2= GetRowNumberFromRowName.getrownumber(2,"ranged combat");
        DoAbilityOnARow(rangedcombat1);
        DoAbilityOnARow(rangedcombat2);
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        BringerofDeathAbilityDoing abilityDoing=new BringerofDeathAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setStatus(status);
        abilityDoing.setPlayer(player);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
