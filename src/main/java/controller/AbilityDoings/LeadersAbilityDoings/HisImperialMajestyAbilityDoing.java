package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.Player;
import model.faction.Card;

import java.util.ArrayList;
import java.util.Random;

public class HisImperialMajestyAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }
    @Override
    public void DoCardAbility() {
        Player opponentplayer;
        if(gameStatus.getPlayer1()==player){
            opponentplayer=gameStatus.getPlayer2();
        }else{
            opponentplayer=gameStatus.getPlayer1();
        }
        ObservableList<Card> oponnethand=opponentplayer.getDeck().getInHandCards();
        ArrayList<Card>mustshowcards=new ArrayList<Card>();
        Random rand1 = new Random();
        if(oponnethand.size()>2){
            Card card1 = oponnethand.get(rand1.nextInt(oponnethand.size()));
            Card card2 = oponnethand.get(rand1.nextInt(oponnethand.size()));
            Card card3 = oponnethand.get(rand1.nextInt(oponnethand.size()));
            mustshowcards.add(card1);
            mustshowcards.add(card2);
            mustshowcards.add(card3);
        }else{
            mustshowcards.addAll(oponnethand);
        }
        //TODO:Show these cards!
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
    }

    @Override
    public Ability Copy(Card card) {
        HisImperialMajestyAbilityDoing hisImperialMajestyAbilityDoing=new HisImperialMajestyAbilityDoing();
        hisImperialMajestyAbilityDoing.setGameStatus(gameStatus);
        hisImperialMajestyAbilityDoing.setmaincard(maincard);
        hisImperialMajestyAbilityDoing.setPlayer(player);
        hisImperialMajestyAbilityDoing.setStatus(status);
        return hisImperialMajestyAbilityDoing;
    }
}
