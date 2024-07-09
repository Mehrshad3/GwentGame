package controller.AbilityDoings.FactionAbilityDoings;

import controller.AbilityDoings.Ability;
import model.GameStatus;
import model.faction.Card;

public class SkelligeAbilityDoing extends FactionAbility {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
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
    public FactionAbility Copy() {
        return null;
    }

}
