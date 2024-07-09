package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.Checking.HeroChecking;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.Player;
import model.faction.Card;

import java.util.ArrayList;

public class TheRelentlessAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        //TODO
        Player oppplayer=null;
        if(gameStatus.getTable().getPlayer1().equals(player)){
            oppplayer=gameStatus.getTable().getPlayer2();
        }else{
            oppplayer=gameStatus.getTable().getPlayer1();
        }
        ObservableList<Card> oppdiscards=oppplayer.getDeck().getDiscardCards();
        ArrayList<Card> notherooppdiscards=new ArrayList<Card>();
        for(Card card:oppdiscards){
            if(HeroChecking.HeroChecking(card)){

            }else{
                notherooppdiscards.add(card);
            }
        }
        Card selectedcard=null;
        if(notherooppdiscards.isEmpty()){

        }else{
            //todo show notherocards and let user choose one
            oppdiscards.remove(selectedcard);
            player.getDeck().getInHandCards().add(selectedcard);
        }



        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        TheRelentlessAbilityDoing abilityDoing=new TheRelentlessAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setPlayer(player);
        abilityDoing.setStatus(status);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
