package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import javafx.collections.ObservableList;
import model.ObservableGameStatus;
import model.faction.Card;

public class PurebloodElfAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public void DoCardAbility() {
        ObservableList<Card>hand=player.getDeck().getInHandCards();
        Card wantedcard=null;
        for(Card card:hand){
            if(card.getName().toLowerCase().equals("biting frost")){
                wantedcard=card;
            }else {}
        }
        hand.remove(wantedcard);
        gameStatus.getHandleRounds().placeweathercard(wantedcard,player);
        gameStatus.getHandleRounds().setLeaderdidfromplayer(player,true);
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
