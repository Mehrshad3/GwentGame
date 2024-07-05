package controller;

import controller.AbilityDoings.Ability;
import controller.Checking.GetAbility;
import enums.card.CardName;
import model.*;
import model.faction.Card;
import model.faction.Faction;
import model.faction.UnitCard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class HandleRounds {
    public ObservableGameStatus gameStatus;
    public Faction faction1;
    public Faction faction2;

    public ArrayList<Ability> NextDoingAbilitys = new ArrayList<>();

    public HandleRounds() {
    }

    public HandleRounds(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

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

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(ObservableGameStatus gameStatus) {
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

    public void PlaceCard(UnitCard card, int row, Player player) {
        //TODO:spells and weathers ability doing
        //TODO:commander horn ability exist or not? check it!
        ObservableRow[] rows = gameStatus.getTable().getRows();
        ObservableRow row0 = rows[row];
        row0.getUnitCards().add(card);
        card.setRowNumber(row);
        player.getDeck().getInHandCards().remove(card);
        GetAbility.getAbility(card, gameStatus, player, this);
    }

    public  void passround(){
        // This reversed loop prevents ConcurrentModificationException.
        for (int i = getNextDoingMethods().size() - 1; i >= 0; i--) {
            Ability ability = getNextDoingMethods().get(i);
            ability.DoCardAbility();
        }
    }

    public void Initialize(){
        faction1=gameStatus.getPlayer1().getFaction();
        faction2=gameStatus.getPlayer2().getFaction();
    }

}
