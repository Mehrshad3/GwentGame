package controller;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.FactionAbilityDoings.FactionAbilities;
import model.ObservableGameStatus;
import model.Player;
import model.Table;

import java.util.Random;

public class GameInitializer {
    public static ObservableGameStatus InitializeAGame(Player player1, Player player2){
        Table table = new Table(player1,player2);
        ObservableGameStatus gameStatus = new ObservableGameStatus(table,player1,player2);
        HandleRounds handleRounds=new HandleRounds(gameStatus);
        gameStatus.setHandleRounds(handleRounds);
        Ability faction1ability;
        Ability faction2ability;
        faction1ability= FactionAbilities.map.get(player1.getFaction().name.toLowerCase()).ability;
        faction2ability= FactionAbilities.map.get(player2.getFaction().name.toLowerCase()).ability;
        gameStatus.setFaction1abilitydoing(faction1ability);
        gameStatus.setFaction2abilitydoing(faction2ability);
        if(player1.getFaction().name.equals("sociatael")){
            if(player2.getFaction().name.equals("sociatael")){
                Random random=new Random();
                int curplapla= random.nextInt(1,2);
                gameStatus.getTable().setCurrentPlayerPlaying(curplapla);
            }else {
                gameStatus.getTable().setCurrentPlayerPlaying(1);
            }
            }else{
                if(player2.getFaction().name.equals("sociatael")){
                    gameStatus.getTable().setCurrentPlayerPlaying(2);
                }else{
                    Random random=new Random();
                    int curplapla= random.nextInt(1,2);
                    gameStatus.getTable().setCurrentPlayerPlaying(curplapla);
                }
            }

        return gameStatus;

        //todo:daisy of the valley !!
    }
}
