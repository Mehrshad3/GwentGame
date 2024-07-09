package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.SpellsAbilityDoing.ClearWeatherAbilityDoing;
import model.ObservableGameStatus;
import model.faction.Card;

public class TheSteelForgedAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        ClearWeatherAbilityDoing clearWeatherAbilityDoing=new ClearWeatherAbilityDoing();
        clearWeatherAbilityDoing.setGameStatus(gameStatus);
        clearWeatherAbilityDoing.DoCardAbility();
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        TheSteelForgedAbilityDoing theSteelForgedAbilityDoing=new TheSteelForgedAbilityDoing();
        theSteelForgedAbilityDoing.setmaincard(maincard);
        theSteelForgedAbilityDoing.setGameStatus(gameStatus);
        theSteelForgedAbilityDoing.setStatus(status);
        theSteelForgedAbilityDoing.setPlayer(player);
        return theSteelForgedAbilityDoing;
    }
}
