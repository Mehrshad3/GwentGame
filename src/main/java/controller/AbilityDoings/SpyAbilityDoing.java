package controller.AbilityDoings;

import model.ObservableGameStatus;
import model.Player;
import model.faction.Card;

import java.util.ArrayList;
import java.util.Random;

public class SpyAbilityDoing extends Ability{
    public ObservableGameStatus  game;
    public Card MainCard;

    public Card getMainCard() {
        return MainCard;
    }

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public ObservableGameStatus getGame() {
        return game;
    }

    public void setGame(ObservableGameStatus game) {
        this.game = game;
    }

    public void DoAbility(Player player) {
        ArrayList<Card> deck = player.getDeck().getCardsInDeck();
        Card card1;
        Card card2;
        if (deck.size()<2) {
        if(deck.isEmpty()){

        }else{
            player.getDeck().getInHandCards().add(deck.get(0));
            deck.remove(deck.get(0));
        }
        } else {
            Random rand1 = new Random();
            card1 = deck.get(rand1.nextInt(deck.size()));
            player.getDeck().getInHandCards().add(card1);
            deck.remove(card1);
            Random rand2 = new Random();
            card2 = deck.get(rand2.nextInt(deck.size()));
            player.getDeck().getInHandCards().add(card2);
            deck.remove(card2);

        }
    }

    @Override
    public void DoCardAbility() {
        DoAbility(player);
        game.getHandleRounds().getNextDoingMethods().remove(this);
    }

    @Override
    public Ability Copy(Card card) {
        SpyAbilityDoing spyAbilityDoing=new SpyAbilityDoing();
        spyAbilityDoing.setmaincard(card);
        spyAbilityDoing.setGame(game);
        spyAbilityDoing.setStatus(status);
        return spyAbilityDoing;
    }
}
