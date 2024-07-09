package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import model.ObservableGameStatus;
import model.faction.Card;

public class TheRelentlessAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        //TODO
    }

    @Override
    public Ability Copy(Card card) {
        TheRelentlessAbilityDoing abilityDoing=new TheRelentlessAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setPlayer(player);
        abilityDoing.setStatus(status);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
