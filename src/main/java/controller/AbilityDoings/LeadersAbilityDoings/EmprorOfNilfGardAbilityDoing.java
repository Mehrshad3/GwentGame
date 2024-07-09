package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import model.ObservableGameStatus;
import model.faction.Card;

public class EmprorOfNilfGardAbilityDoing extends Ability {
    public ObservableGameStatus  gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void DoCardAbility() {
        //TODO:check
        gameStatus.setLeader2Did(true);
        gameStatus.setLeader2Did(true);
    }

    @Override
    public Ability Copy(Card card) {
        EmprorOfNilfGardAbilityDoing abilityDoing=new EmprorOfNilfGardAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setStatus(status);
        abilityDoing.setPlayer(player);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
