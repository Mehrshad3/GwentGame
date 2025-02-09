package controller;

import controller.AbilityDoings.FactionAbilityDoings.FactionAbilities;
import controller.AbilityDoings.FactionAbilityDoings.FactionAbility;
import enums.EnumAbilities.Abilities;
import model.GameStatus;
import model.ObservableGameStatus;
import model.Player;
import model.Table;
import model.faction.LeaderCard;

import java.util.Random;

public class GameInitializer {
    public static ObservableGameStatus InitializeAGame(Player player1, Player player2){
        Table table = new Table(player1,player2);
        ObservableGameStatus gameStatus = new ObservableGameStatus(player1,player2,new GameStatus(table,player1,player2));
        HandleRounds handleRounds=new HandleRounds(gameStatus);
        gameStatus.setHandleRounds(handleRounds);
        FactionAbility faction1ability;
        FactionAbility faction2ability;
        faction1ability= FactionAbilities.map.get(player1.getFaction().name.toLowerCase()).ability.Copy();
        faction2ability= FactionAbilities.map.get(player2.getFaction().name.toLowerCase()).ability.Copy();
        faction1ability.setPlayer(gameStatus.getPlayer1());
        faction2ability.setPlayer(gameStatus.getPlayer2());
        faction1ability.setGameStatus(gameStatus);
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
        //todo set leaders
        LeaderCard leaderCard1=gameStatus.getPlayer1().getDeck().getCurrentLeaderCard();
        LeaderCard leaderCard2=gameStatus.getPlayer2().getDeck().getCurrentLeaderCard();
        gameStatus.setLeader1ability(Abilities.map.get(leaderCard1.getName().toLowerCase()).Abilityname);
        gameStatus.setLeader2ability(Abilities.map.get(leaderCard2.getName().toLowerCase()).Abilityname);
        //todo:daisy of the valley !!

        return gameStatus;

    }
}
