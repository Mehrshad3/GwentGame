package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.faction.Card;

public class KingOfTemeriaAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        //TODO:do commanderhorn Abilty on seige if Commander horn doesnt exists
    }

    @Override
    public Ability Copy(Card card) {
        KingOfTemeriaAbilityDoing kingOfTemeriaAbilityDoing=new KingOfTemeriaAbilityDoing();
        kingOfTemeriaAbilityDoing.setGameStatus(gameStatus);
        kingOfTemeriaAbilityDoing.setmaincard(maincard);
        kingOfTemeriaAbilityDoing.setPlayer(player);
        kingOfTemeriaAbilityDoing.setStatus(status);
        return kingOfTemeriaAbilityDoing;
    }
}
