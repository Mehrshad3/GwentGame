package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.faction.Card;

public class TheWhiteFlameAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        ObservableList<Card> handscards = player.getDeck().getInHandCards();
        Card card = null;
        for (Card card0 : handscards) {
            if (card0.getName().toLowerCase().equals("Torrential Rain".toLowerCase())) {
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
        TheWhiteFlameAbilityDoing theWhiteFlameAbilityDoing=new TheWhiteFlameAbilityDoing();
        theWhiteFlameAbilityDoing.setGameStatus(gameStatus);
        theWhiteFlameAbilityDoing.setmaincard(maincard);
        theWhiteFlameAbilityDoing.setPlayer(player);
        theWhiteFlameAbilityDoing.setStatus(status);
        return theWhiteFlameAbilityDoing;
    }
}
