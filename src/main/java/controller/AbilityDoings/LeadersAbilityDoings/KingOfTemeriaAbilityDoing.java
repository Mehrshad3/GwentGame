package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.GetRowNumberFromRowName;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class KingOfTemeriaAbilityDoing extends Ability {
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
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        KingOfTemeriaAbilityDoing kingOfTemeriaAbilityDoing=new KingOfTemeriaAbilityDoing();
        kingOfTemeriaAbilityDoing.setGameStatus(gameStatus);
        kingOfTemeriaAbilityDoing.setmaincard(maincard);
        kingOfTemeriaAbilityDoing.setPlayer(player);
        kingOfTemeriaAbilityDoing.setStatus(status);
        return kingOfTemeriaAbilityDoing;
    }
}
