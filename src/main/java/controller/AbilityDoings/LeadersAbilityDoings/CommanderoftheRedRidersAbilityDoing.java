package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.CommandersHornAbility;
import model.GameStatus;
import model.faction.Card;

public class CommanderoftheRedRidersAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void DoCardAbility() {
        //TODO what to do what not to do
    }

    @Override
    public Ability Copy(Card card) {
        CommanderoftheRedRidersAbilityDoing abilityDoing=new CommanderoftheRedRidersAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setStatus(status);
        abilityDoing.setPlayer(player);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
