package controller.AbilityDoings;

import controller.CardRemoverFromGame;
import controller.Checking.HeroChecking;
import controller.GetRowNumberFromRowName;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.ArrayList;

public class ScorchAbilityDoing extends Ability {
    public GameStatus game;
    public Card MainCard;

    public ScorchAbilityDoing(String status){
        setStatus(status);
    }
    public ScorchAbilityDoing(){

    }

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public Card getMainCard() {
        return MainCard;
    }

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DoAbilityOnWholeBoardUnconditionally(){
        Row[] rows=game.getTable().getRows();
        ArrayList<UnitCard>MaxCards=new ArrayList<UnitCard>();
        int maxPower = 0;
        for(Row row:rows){
            for(UnitCard card:row.getCards()){
                if(card.getPower() > maxPower){
                    maxPower= card.getPower();
                    for(UnitCard card0:MaxCards){
                        MaxCards.remove(card0);
                    }
                    MaxCards.add(card);
                } else if (card.getPower() == maxPower) {
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

    public void DoAbilityOnARowConditionally(int row) {
        Row[] rows=game.getTable().getRows();
        Row wantedrow=rows[row];
        ArrayList<UnitCard>MaxCards=new ArrayList<UnitCard>();
        int maxpower=0;
        int sumrowpowers=0;
        for(UnitCard card:wantedrow.getCards()){
            if(!HeroChecking.HeroChecking(card)){
            sumrowpowers=sumrowpowers+card.getPower();}
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

    @Override
    public void DoCardAbility() {
        switch (status){
            case "general":
                DoAbilityOnWholeBoardUnconditionally();
                break;
            case "opponent:ranged combat":
                int opraco;
                opraco=GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus,"opponent:ranged combat");
                DoAbilityOnARowConditionally(opraco);
                break;
            case "general:opponent":
                if(maincard.getPlayer().equals(maincard.getGameStatus().getPlayer1())){
                    DoAbilityOnARowConditionally(6);
                    DoAbilityOnARowConditionally(5);
                    DoAbilityOnARowConditionally(4);
                }else{
                    DoAbilityOnARowConditionally(1);
                    DoAbilityOnARowConditionally(2);
                    DoAbilityOnARowConditionally(3);
                }
                break;
            case "siege combat:opponent":
                int opsico;
                opsico=GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus,"opponent:siege combat");
                DoAbilityOnARowConditionally(opsico);
                break;
            case "Close combat:oponnent":
                int opclco;
                opclco=GetRowNumberFromRowName.getrownumberbyplayeranddetail(player,gameStatus,"opponent:close combat");
                DoAbilityOnARowConditionally(opclco);
                break;
            default:

        }
    }

    @Override
    public Ability Copy(Card card) {
        ScorchAbilityDoing scorchAbilityDoing=new ScorchAbilityDoing();
        scorchAbilityDoing.setmaincard(card);
        scorchAbilityDoing.setStatus(status);
        scorchAbilityDoing.setGame(game);
        return scorchAbilityDoing;
    }
}
