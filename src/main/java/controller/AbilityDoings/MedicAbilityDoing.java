package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.Player;
import model.User;
import model.faction.Card;
import model.faction.UnitCard;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.Random;

public class MedicAbilityDoing extends Ability{
    public GameStatus game;
    public Card MainCard;

    public Card getMainCard() {
        return MainCard;
    }

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DoAbility(Player player, Card card){
        if(HeroChecking.HeroChecking((UnitCard) card)) {
            ObservableList<Card> discardcards = player.getDeck().getDiscardCards();
            ObservableList<Card> inhandcards = player.getDeck().getInHandCards();
            discardcards.remove(card);
            inhandcards.add(card);
        }else{}
    }

    @Override
    public void DoCardAbility() {
        Random rand=new Random();
        Card card=player.getDeck().getDiscardCards().get(rand.nextInt(player.getDeck().getDiscardCards().size()));
        DoAbility(player,card);
        game.getHandleRounds().getNextDoingMethods().remove(this);
        //todo randomcard maybe should be selected
    }

    @Override
    public Ability Copy(Card card) {
        MedicAbilityDoing medicAbilityDoing=new MedicAbilityDoing();
        medicAbilityDoing.setmaincard(card);
        medicAbilityDoing.setStatus(status);
        medicAbilityDoing.setGame(game);
        return medicAbilityDoing;
    }
}
