package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import model.ObservableGameStatus;
import model.Player;
import model.faction.Card;
import model.faction.UnitCard;

public class DecoyAbilityDoing extends Ability{
    public ObservableGameStatus game;

    public ObservableGameStatus getGame() {
        return game;
    }

    public void setGame(ObservableGameStatus game) {
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
        DoAbility((UnitCard) maincard,player);
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
