package controller.AbilityDoings.LeadersAbilityDoings;

import controller.AbilityDoings.Ability;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.faction.Card;

public class PurebloodElfAbilityDoing extends Ability {
    public GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public void DoCardAbility() {
        ObservableList<Card>hand=player.getDeck().getInHandCards();
        Card wantedcard=null;
        for(Card card:hand){
            if(card.getName().toLowerCase().equals("bitingfrost")){
                wantedcard=card;
            }else {}
        }
        hand.remove(wantedcard);
        gameStatus.getHandleRounds().placeweathercard(wantedcard,player);

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
