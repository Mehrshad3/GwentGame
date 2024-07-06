package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.faction.Card;

public class TheWhiteFlameAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        ObservableList<Card> handscards=player.getDeck().getInHandCards();
        //TODO
    }

    @Override
    public Ability Copy(Card card) {
        TheWhiteFlameAbilityDoing theWhiteFlameAbilityDoing=new TheWhiteFlameAbilityDoing();
        theWhiteFlameAbilityDoing.setGameStatus(gameStatus);
        theWhiteFlameAbilityDoing.setmaincard(maincard);
        theWhiteFlameAbilityDoing.setPlayer(player);
        theWhiteFlameAbilityDoing.setStatus(status);
        return theWhiteFlameAbilityDoing;
    }
}
