package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.faction.Card;

public class DestroyerofWorldsAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        Card card1=null;
        Card card2=null;
        Card newcard=null;
        //todo select two cards from Hand and a new card from Deck
        player.getDeck().getInHandCards().remove(card1);
        player.getDeck().getInHandCards().remove(card2);
        player.getDeck().getDiscardCards().remove(newcard);
        player.getDeck().addCardToHand(newcard);
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        DestroyerofWorldsAbilityDoing abilityDoing=new DestroyerofWorldsAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setStatus(status);
        abilityDoing.setPlayer(player);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
