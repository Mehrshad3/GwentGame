package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.ScorchAbilityDoing;
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
        ScorchAbilityDoing scorchAbilityDoing=new ScorchAbilityDoing("siege combat:opponent");
        scorchAbilityDoing.setGame(gameStatus);
        scorchAbilityDoing.setPlayer(player);
        scorchAbilityDoing.DoCardAbility();
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
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
