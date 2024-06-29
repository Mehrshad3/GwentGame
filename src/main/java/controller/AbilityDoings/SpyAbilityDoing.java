package controller.AbilityDoings;

import model.GameStatus;
import model.Player;
import model.faction.Card;

import java.util.ArrayList;
import java.util.Random;

public class SpyAbilityDoing {
    public GameStatus game;

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DoAbility(Player player){
        ArrayList<Card>deck=player.getDeck().getCardsInDeck();
        Card card1;
        Card card2;
        Random rand1=new Random();
        card1=deck.get(rand1.nextInt(deck.size()));
        player.getDeck().getInHandCards().add(card1);
        deck.remove(card1);
        Random rand2=new Random();
        card2=deck.get(rand2.nextInt(deck.size()));
        player.getDeck().getInHandCards().add(card2);
        deck.remove(card2);

    }

}
