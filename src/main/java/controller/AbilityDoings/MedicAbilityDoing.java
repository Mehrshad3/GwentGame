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

public class MedicAbilityDoing {
    public GameStatus game;

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
}
