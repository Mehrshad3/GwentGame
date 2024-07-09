package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.GetRowNumberFromRowName;
import enums.EnumAbilities.Abilities;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class BringerofDeathAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public void DoCardAbility() {
        if (gameStatus.getHandleRounds().getLeaderdidfromplayer(player)) {

        } else {
            Row[] rows = gameStatus.getTable().getRows();
            int close10= GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus,"close combat");
            int close20=GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus,"opp close combat");
            boolean close1 = false;
            boolean close2 = false;
            if (!rows[close10].getSpcialpot().equals(null)) {
                if (Abilities.map.get(rows[close10].getSpcialpot()).Abilityname.getClass() == CommanderoftheRedRidersAbilityDoing.class) {
                    close1 = true;
                } else {
                }
            }
            if (!rows[close20].getSpcialpot().equals(null)) {
                if (Abilities.map.get(rows[close20].getSpcialpot()).Abilityname.getClass() == CommanderoftheRedRidersAbilityDoing.class) {
                    close2 = true;
                } else {
                }
            }
            if (close1) {
            } else {
                for (UnitCard unitCard : rows[close10].getCards()) {
                    unitCard.boostpower = true;
                }
            }
            if (close2) {
            } else {
                for (UnitCard unitCard : rows[close20].getCards()) {
                    unitCard.boostpower = true;
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
