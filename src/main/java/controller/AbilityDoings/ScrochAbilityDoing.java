package controller.AbilityDoings;

import controller.CardRemoverFromGame;
import model.GameStatus;
import model.Row;
import model.faction.UnitCard;

import java.util.ArrayList;

public class ScrochAbilityDoing {
    public GameStatus game;

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DoAbilityOnWholeBoardUnconditionally(){
        Row[] rows=game.getTable().getRows();
        ArrayList<UnitCard>MaxCards=new ArrayList<UnitCard>();
        int maxpower=0;
        for(Row row:rows){
            for(UnitCard card:row.getCards()){
                if(card.getPower()>maxpower){
                    maxpower= card.getPower();
                    for(UnitCard card0:MaxCards){
                        MaxCards.remove(card0);
                    }
                    MaxCards.add(card);
                }else if(card.getPower()==maxpower){
                    MaxCards.add(card);
                }else{}
            }
        }
        for(UnitCard card0:MaxCards){
            CardRemoverFromGame.Remove(game,card0);
        }
    }

    public void DoAbilityOnARowUnconditionally(int row){
        Row[] rows=game.getTable().getRows();
        Row wantedrow=rows[row];
        ArrayList<UnitCard>MaxCards=new ArrayList<UnitCard>();
        int maxpower=0;
        for(UnitCard card:wantedrow.getCards()){
            if(card.getPower()>maxpower){
                maxpower= card.getPower();
                for(UnitCard card0:MaxCards){
                    MaxCards.remove(card0);
                }
                MaxCards.add(card);
            }else if(card.getPower()==maxpower){
                MaxCards.add(card);
            }else{}
        }
        for(UnitCard card0:MaxCards){
            CardRemoverFromGame.RemoveHavingRow(game,card0,row);
        }
    }

    public void DoAbilityOnARowConditionaly(int row){
        Row[] rows=game.getTable().getRows();
        Row wantedrow=rows[row];
        ArrayList<UnitCard>MaxCards=new ArrayList<UnitCard>();
        int maxpower=0;
        int sumrowpowers=0;
        for(UnitCard card:wantedrow.getCards()){
            sumrowpowers=sumrowpowers+card.getPower();
            if(card.getPower()>maxpower){
                maxpower= card.getPower();
                for(UnitCard card0:MaxCards){
                    MaxCards.remove(card0);
                }
                MaxCards.add(card);
            }else if(card.getPower()==maxpower){
                MaxCards.add(card);
            }else{}
        }
        if(sumrowpowers>9) {
            for (UnitCard card0 : MaxCards) {
                CardRemoverFromGame.RemoveHavingRow(game, card0, row);
            }
        }else{}
    }

}
