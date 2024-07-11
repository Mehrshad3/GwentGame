package controller.AbilityDoings.FactionAbilityDoings;

import model.ObservableGameStatus;

public class SkelligeAbilityDoing extends FactionAbility {
    public ObservableGameStatus gameStatus;

    @Override
    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
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
    public FactionAbility Copy() {
        SkelligeAbilityDoing abilityDoing=new SkelligeAbilityDoing();
        abilityDoing.setGameStatus(gameStatus);
        abilityDoing.setPlayer(player);
        return  abilityDoing;
    }

}
