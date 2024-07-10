package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.ScorchAbilityDoing;
import model.ObservableGameStatus;
import model.faction.Card;

public class QueenofDolBalsannaAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        ScorchAbilityDoing scorchAbilityDoing = new ScorchAbilityDoing("Close combat:oponnent");
        scorchAbilityDoing.setGame(gameStatus);
        scorchAbilityDoing.setPlayer(player);
        scorchAbilityDoing.DoCardAbility();
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        BringerofDeathAbilityDoing abilityDoing=new BringerofDeathAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setStatus(status);
        abilityDoing.setPlayer(player);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
