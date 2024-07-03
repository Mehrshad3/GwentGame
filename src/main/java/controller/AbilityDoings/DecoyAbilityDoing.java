package controller.AbilityDoings;

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

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DoAbility(UnitCard card, Player player){
        if(HeroChecking.HeroChecking(card)){
        }else{
            player.getDeck().addCardToHand(card);
        }
    }



    @Override
    public void DoCardAbility() {

    }

    @Override
    public Ability Copy(Card card) {
        DecoyAbilityDoing decoyAbilityDoing=new DecoyAbilityDoing();
        decoyAbilityDoing.setStatus(status);
        decoyAbilityDoing.setGame(game);
        return decoyAbilityDoing;
    }
}
