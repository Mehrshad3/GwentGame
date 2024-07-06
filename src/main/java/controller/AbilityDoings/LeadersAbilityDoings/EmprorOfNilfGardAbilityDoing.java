package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.faction.Card;

public class EmprorOfNilfGardAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        //TODO
    }

    @Override
    public Ability Copy(Card card) {
        return null;
    }
}
