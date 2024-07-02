package controller;

import controller.AbilityDoings.Ability;
import controller.Checking.GetAbility;
import model.GameStatus;
import model.Player;
import model.Row;
import model.faction.Faction;
import model.faction.UnitCard;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class HandleRounds {
    public GameStatus gameStatus;
    public Faction faction1;
    public Faction faction2;

    public ArrayList<Ability> NextDoingAbilitys;

    public ArrayList<Ability> getNextDoingMethods() {
        return NextDoingAbilitys;
    }

    public void setNextDoingMethods(ArrayList<Ability> NextDoingAbilitys) {
        this.NextDoingAbilitys = NextDoingAbilitys;
    }

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
        //TODO:placeCard if wanted
        //TODO:after place card , for(Ability ability:NextDoingAbilitiys){ability.DoCardAction;}

    }

    public void PlaceCardByName(String CardName,int row){
        //TODO
    }

    public void PlaceCard(UnitCard card, int row,Player player){
        //TODO
        Row[] rows=gameStatus.getTable().getRows();
        Row row0=rows[row];
        row0.getCards().add(card);
        player.getDeck().getInHandCards().remove(card);
        GetAbility.getAbility(card,gameStatus,player);
    }

    public void Initialize(){
        faction1=gameStatus.getPlayer1().getFaction();
        faction2=gameStatus.getPlayer2().getFaction();
    }

}
