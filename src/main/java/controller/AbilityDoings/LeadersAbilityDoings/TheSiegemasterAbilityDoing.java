package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import enums.EnumAbilities.Abilities;
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
        ObservableList<Card> handscards = player.getDeck().getInHandCards();
        Card card = null;
        for (Card card0 : handscards) {
            if (card0.getName().toLowerCase().equals("Impenetrable Fog".toLowerCase())) {
                card = card0;
            } else {
            }
        }
        if (card != null) {

        } else {
            handscards.remove(card);
            gameStatus.getHandleRounds().placeweathercard(card,player);
        }
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
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
