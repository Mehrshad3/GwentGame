package controller;

import model.GameStatus;
import model.Row;
import model.Table;
import model.faction.Card;

public class CommandersHornAbility {
    public GameStatus gameStatus;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public void DoAbility(int row){
        Table table=gameStatus.getTable();
        Row[] rows=table.getRows();
        Row wantedrow=rows[row];
        Card[] cards=wantedrow.getCards();
        for(Card card0:cards){
            if(false/*Checking card is hero*/){

            }else{
                int power=card0.getpower();
                card0.setpower(2*power);
            }
        }
    }
}
