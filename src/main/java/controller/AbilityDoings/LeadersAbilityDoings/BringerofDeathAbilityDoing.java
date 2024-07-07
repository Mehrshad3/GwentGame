package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
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
            boolean r3 = false;
            boolean r4 = false;
            if (rows[3].getSpcialpot().equals(null)) {
                if (Abilities.map.get(rows[3].getSpcialpot()).Abilityname.getClass() == CommanderoftheRedRidersAbilityDoing.class) {
                    r3 = true;
                } else {
                }
            }
            if (rows[4].getSpcialpot().equals(null)) {
                if (Abilities.map.get(rows[3].getSpcialpot()).Abilityname.getClass() == CommanderoftheRedRidersAbilityDoing.class) {
                    r4 = true;
                } else {
                }
            }
            if (r3) {
            } else {
                for (UnitCard unitCard : rows[3].getCards()) {
                    unitCard.boostpower = true;
                }
            }
            if (r4) {
            } else {
                for (UnitCard unitCard : rows[4].getCards()) {
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
