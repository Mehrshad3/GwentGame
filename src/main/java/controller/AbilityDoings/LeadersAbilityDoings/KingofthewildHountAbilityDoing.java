package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import controller.Checking.HeroChecking;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.ArrayList;
import java.util.Random;

public class KingofthewildHountAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void DoCardAbility() {
        ObservableList<Card>discards=player.getDeck().getDiscardCards();
        ArrayList<Card> notherocrads=new ArrayList<Card>();
        for(Card card:discards){
            if(card.getClass().equals(UnitCard.class)){
            if(HeroChecking.HeroChecking((UnitCard) card)){
                notherocrads.add(card);
            }else{}
            }else{}
        }
        Random random=new Random();
        Card specialCard=notherocrads.get(random.nextInt(notherocrads.size()));
        discards.remove(specialCard);
        player.getDeck().getInHandCards().add(specialCard);
    }

    @Override
    public Ability Copy(Card card) {
        BringerofDeathAbilityDoing abilityDoing=new BringerofDeathAbilityDoing();
        abilityDoing.setmaincard(maincard);
        abilityDoing.setStatus(status);
        abilityDoing.setPlayer(player);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
