package controller;

import model.GameStatus;
import model.Player;
import model.Table;

public class GameInitializer {
    public static GameStatus InitializeAGame(Player player1,Player player2){
        Table table=new Table(player1,player2);
        GameStatus gameStatus=new GameStatus(table,player1,player2);
        HandleRounds handleRounds=new HandleRounds();
        gameStatus.setHandleRounds(handleRounds);
        return gameStatus;
        //todo:daisy of the valley !!
    }
}
