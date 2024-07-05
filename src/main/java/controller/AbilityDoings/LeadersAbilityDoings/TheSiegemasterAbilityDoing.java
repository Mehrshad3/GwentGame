package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.faction.Card;

public class TheSiegemasterAbilityDoing extends Ability {
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
        TheSiegemasterAbilityDoing theSiegemasterAbilityDoing=new TheSiegemasterAbilityDoing();
        theSiegemasterAbilityDoing.setmaincard(maincard);
        theSiegemasterAbilityDoing.setGameStatus(gameStatus);
        theSiegemasterAbilityDoing.setStatus(status);
        theSiegemasterAbilityDoing.setPlayer(player);
        return theSiegemasterAbilityDoing;
    }
}
