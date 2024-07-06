package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.faction.Card;

public class LordCommanderOfTheNorthAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        //TODO:scorch ability on seige
    }

    @Override
    public Ability Copy(Card card) {
        LordCommanderOfTheNorthAbilityDoing sunOfMedellAbilityDoing=new LordCommanderOfTheNorthAbilityDoing();
        sunOfMedellAbilityDoing.setGameStatus(gameStatus);
        sunOfMedellAbilityDoing.setmaincard(maincard);
        sunOfMedellAbilityDoing.setStatus(status);
        sunOfMedellAbilityDoing.setPlayer(player);
        return sunOfMedellAbilityDoing;
    }
}
