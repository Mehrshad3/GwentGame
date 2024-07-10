package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.Player;
import model.faction.Card;

public class CrachanCraiteAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void DoCardAbility() {
        Player player1=gameStatus.getTable().getPlayer1();
        Player player2=gameStatus.getTable().getPlayer2();
        ObservableList<Card> discard1=player1.getDeck().getDiscardCards();
        ObservableList<Card> discard2=player2.getDeck().getDiscardCards();
        for(Card card:discard1){
            discard1.remove(card);
            player1.getDeck().addCardToDeck(card);
        }
        for(Card card:discard2){
            discard2.remove(card);
            player2.getDeck().addCardToDeck(card);
        }
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        CrachanCraiteAbilityDoing abilityDoing=new CrachanCraiteAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setStatus(status);
        abilityDoing.setPlayer(player);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
