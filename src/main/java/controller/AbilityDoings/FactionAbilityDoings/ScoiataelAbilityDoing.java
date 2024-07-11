package controller.AbilityDoings.FactionAbilityDoings;

import model.ObservableGameStatus;

public class ScoiataelAbilityDoing extends FactionAbility {
    public ObservableGameStatus gameStatus;

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
    public FactionAbility Copy() {
        ScoiataelAbilityDoing abilityDoing=new ScoiataelAbilityDoing();
        abilityDoing.setGameStatus(gameStatus);
        abilityDoing.setPlayer(player);
        return  abilityDoing;
    }



}
