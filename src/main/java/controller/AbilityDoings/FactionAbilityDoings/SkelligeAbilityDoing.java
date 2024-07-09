package controller.AbilityDoings.FactionAbilityDoings;

import controller.AbilityDoings.Ability;
import model.ObservableGameStatus;
import model.faction.Card;

public class SkelligeAbilityDoing extends Ability {
    public ObservableGameStatus gameStatus;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void DoCardAbility() {
        if(gameStatus.getNumberOfTurns()==0){

        }else if(gameStatus.getNumberOfTurns()==1){

        }else if(gameStatus.getNumberOfTurns()==2){

        }else if(gameStatus.getNumberOfTurns()==3){
            //todo:play two ghabrestan cards :)
        }else{

        }
    }

    @Override
    public Ability Copy(Card card) {
        SkelligeAbilityDoing abilityDoing=new SkelligeAbilityDoing();
        abilityDoing.setPlayer(player);
        abilityDoing.setStatus(status);
        abilityDoing.setGameStatus(gameStatus);
        return abilityDoing;
    }
}
