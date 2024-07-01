package controller;

import model.GameStatus;
import model.Player;
import model.faction.Card;
import model.faction.Faction;

public class HandleRounds {
    public GameStatus gameStatus;
    public Faction faction1;
    public Faction faction2;

    public boolean notfinishedyet=true;

    public Faction getFaction1() {
        return faction1;
    }

    public Faction getFaction2() {
        return faction2;
    }

    public void setFaction1(Faction faction1) {
        this.faction1 = faction1;
    }

    public void setFaction2(Faction faction2) {
        this.faction2 = faction2;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void Handlerounds(String InputString){
        //TODO

    }

    public void PlaceCardByName(String CardName,int Row){
        //TODO
    }

    public void PlaceCard(Card card,int Row){
        //TODO

    }

    public void Initialize(){
        faction1=gameStatus.getPlayer1().getFaction();
        faction2=gameStatus.getPlayer2().getFaction();
    }

}
