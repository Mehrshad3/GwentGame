package controller.AbilityDoings.FactionAbilityDoings;

import controller.AbilityDoings.Ability;
import model.ObservableGameStatus;
import model.faction.Card;

public class EmpireNilfgaardianAbilityDoing extends Ability {
    public ObservableGameStatus  gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void DoCardAbility() {

    }

    @Override
    public Ability Copy(Card card) {
        EmpireNilfgaardianAbilityDoing abilityDoing=new EmpireNilfgaardianAbilityDoing();
        abilityDoing.setPlayer(player);
        abilityDoing.setStatus(status);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }

}
