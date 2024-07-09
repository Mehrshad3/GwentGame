package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.Player;
import model.faction.Card;

import java.util.Random;

public class InvaderoftheNorthAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void DoAbilityOnAPlayer(Player player){
        ObservableList<Card>discards=player.getDeck().getDiscardCards();
        if(discards.isEmpty()){

        }else{
            Random random=new Random();
            Card card=discards.get(random.nextInt(discards.size()));
            discards.remove(card);
            player.getDeck().getInHandCards().add(card);
        }
    }

    @Override
    public void DoCardAbility() {
        DoAbilityOnAPlayer(gameStatus.getPlayer2());
        DoAbilityOnAPlayer(gameStatus.getPlayer1());
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        InvaderoftheNorthAbilityDoing invaderoftheNorthAbilityDoing=new InvaderoftheNorthAbilityDoing();
        invaderoftheNorthAbilityDoing.setmaincard(maincard);
        invaderoftheNorthAbilityDoing.setStatus(status);
        invaderoftheNorthAbilityDoing.setPlayer(player);
        invaderoftheNorthAbilityDoing.setGameStatus(gameStatus);
        return invaderoftheNorthAbilityDoing;
    }
}
