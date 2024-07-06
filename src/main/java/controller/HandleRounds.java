package controller;

import controller.AbilityDoings.Ability;
import controller.Checking.GetAbility;
import controller.Checking.GetwaetherAbility;
import enums.card.CardName;
import model.GameStatus;
import model.Player;
import model.Row;
import model.faction.Card;
import model.faction.Faction;
import model.faction.UnitCard;
import model.faction.WeatherCard;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class HandleRounds {
    public GameStatus gameStatus;
    public Faction faction1;
    public Faction faction2;

    public ArrayList<Ability> NextDoingAbilitys;
    public ArrayList<Ability> NextweatherdoingAbilitys;

    public ArrayList<Ability> getNextDoingAbilitys() {
        return NextDoingAbilitys;
    }

    public ArrayList<Ability> getNextweatherdoingAbilitys() {
        return NextweatherdoingAbilitys;
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
        //TODO check weather and other stuffs
        //TODO chek commander horn exists
        Row[] rows=gameStatus.getTable().getRows();
        Row row0=rows[row];
        for(UnitCard unitCard:row0.getCards()){
            ArrayList<UnitCard> newrowmates0 = new ArrayList<UnitCard>(card.getRowmates());
            newrowmates0.add(unitCard);
            card.setRowmates(newrowmates0);
            ArrayList<UnitCard> newrowmates1 = new ArrayList<UnitCard>(unitCard.getRowmates());
            newrowmates1.add(card);
            unitCard.setRowmates(newrowmates1);
        }
        GetAbility.getAbility(card,gameStatus,player,this);
        passroundAbility();
        row0.getCards().add(card);
        card.setRowNumber(row);
        player.getDeck().getInHandCards().remove(card);
        passroundweatherability();
        passroundCard();
    }
    public  void passroundAbility(){
        for(Ability ability:getNextDoingMethods()){
            ability.DoCardAbility();
        }
    }
    public void passroundweatherability(){
        for(Ability ability:getNextweatherdoingAbilitys()){
            ability.DoCardAbility();
        }
    }
    public void passroundCard(){
        for(Row row:gameStatus.getTable().getRows()){
            for(UnitCard unitCard:row.getCards()){
                unitCard.UpdatePower();
            }
        }
    }
    public void placeweathercard(Card weathercard,Player player){
        GetwaetherAbility.getAbility(weathercard,gameStatus,player,this);
        getNextweatherdoingAbilitys().removeAll(getNextweatherdoingAbilitys());
        //TODO:insert weather card in gamestatus
    }

    public void Initialize(){
        faction1=gameStatus.getPlayer1().getFaction();
        faction2=gameStatus.getPlayer2().getFaction();
    }

}
