package controller.AbilityDoings.FactionAbilityDoings;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.faction.Card;

public class RealmsNorthernAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void DoCardAbility() {

    }

    @Override
    public Ability Copy(Card card) {
        RealmsNorthernAbilityDoing abilityDoing=new RealmsNorthernAbilityDoing();
        abilityDoing.setPlayer(player);
        abilityDoing.setStatus(status);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }

}
