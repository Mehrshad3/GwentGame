package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.GetRowNumberFromRowName;
import enums.EnumAbilities.Abilities;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class BringerofDeathAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public void DoCardAbility() {
        if (gameStatus.getHandleRounds().getLeaderdidfromplayer(player)) {

        } else {
            ObservableRow[] rows = gameStatus.getTable().getRows();
            int close10= GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus.getGameStatus(),"close combat");
            int close20=GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus.getGameStatus(),"opp close combat");
            boolean close1 = false;
            boolean close2 = false;
            if (!rows[3].getSpcialpot().equals(null)) {
                if (Abilities.map.get(rows[close10].getSpcialpot()).Abilityname.getClass() == CommanderoftheRedRidersAbilityDoing.class) {
                    close1 = true;
                } else {
                }
            }
            if (!rows[4].getSpcialpot().equals(null)) {
                if (Abilities.map.get(rows[close20].getSpcialpot()).Abilityname.getClass() == CommanderoftheRedRidersAbilityDoing.class) {
                    close2 = true;
                } else {
                }
            }
            if (close1) {
            } else {
                for (Card card : rows[close10].getCards()) {
                    if (card instanceof UnitCard unitCard) unitCard.boostpower = true;
                }
            }
            if (close2) {
            } else {
                for (Card card : rows[close20].getCards()) {
                    if (card instanceof UnitCard unitCard) unitCard.boostpower = true;
                }
            }
            gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
        }
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
