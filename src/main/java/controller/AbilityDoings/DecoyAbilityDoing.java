package controller.AbilityDoings;

import controller.CardRemoverFromGame;
import controller.Checking.HeroChecking;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.Player;
import model.User;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.ArrayList;

public class DecoyAbilityDoing extends Ability{
    public GameStatus game;
    public UnitCard card;

    public UnitCard getCard() {
        return card;
    }

    public void setCard(UnitCard card) {
        this.card = card;
    }

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DoAbility(){
        if(HeroChecking.HeroChecking(card)){
        }else{
            player.getDeck().addCardToHand(card);
        }
    }



    @Override
    public void DoCardAbility() {
        DoAbility();
        player.getDeck().getInHandCards().remove(maincard);
        player.getDeck().getDiscardCards().add(maincard);
        game.getHandleRounds().getNextDoingMethods().remove(this);
    }

    @Override
    public Ability Copy(Card card) {
        DecoyAbilityDoing decoyAbilityDoing=new DecoyAbilityDoing();
        decoyAbilityDoing.setStatus(status);
        decoyAbilityDoing.setGame(game);
        return decoyAbilityDoing;
    }
}
