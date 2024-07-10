package controller;

import controller.AbilityDoings.Ability;
import controller.Checking.GetAbility;
import controller.Checking.GetwaetherAbility;
import model.*;
import model.faction.Card;
import model.faction.Faction;
import model.faction.UnitCard;

import java.util.ArrayList;

public class HandleRounds {
    public ObservableGameStatus gameStatus;
    public Faction faction1;
    public Faction faction2;

    public ArrayList<Ability> NextDoingAbilitys = new ArrayList<>();
    public ArrayList<Ability> NextweatherdoingAbilitys = new ArrayList<>();
    public Ability nextweatherabilitydoing;

    public HandleRounds(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

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

    public boolean notfinishedyet = true;

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

    public void Handlerounds(String InputString) {
        //TODO
        //TODO:placeCard if wanted
        //TODO:after place card , for(Ability ability:NextDoingAbilitiys){ability.DoCardAction;}


    }

    public void PlaceCardByName(String CardName, int row) {
        //TODO
    }

    public void PlaceCard(UnitCard card, int row, Player player) {
        //TODO check weather and other stuffs
        //TODO place commander horns and mardroemes are different
        ObservableRow[] rows = gameStatus.getTable().getRows();
        ObservableRow row0 = rows[row];
        for (Card card0 : row0.getCards()) {
            if (!(card0 instanceof UnitCard unitCard)) continue;
            ArrayList<UnitCard> newrowmates0 = new ArrayList<>(card.getRowmates());
            newrowmates0.add(unitCard);
            card.setRowmates(newrowmates0);
            ArrayList<UnitCard> newrowmates1 = new ArrayList<>(unitCard.getRowmates());
            newrowmates1.add(card);
            unitCard.setRowmates(newrowmates1);
        }
        passroundCard();
        row0.placeCard(card);
        card.setRowNumber(row);
        player.getDeck().getInHandCards().remove(card);
        GetAbility.getAbility(card,gameStatus,player,this);
        passroundCard();
        passroundAbility();
        passroundCard();
        passroundweatherability();
        passroundCard();
        gameStatus.increaseNumberOfTurns();
        passfactionround();
        passroundCard();
    }

    public  void passroundAbility() {
        // This reversed loop prevents ConcurrentModificationException.
        for (int i = getNextDoingMethods().size() - 1; i >= 0; i--) {
            Ability ability = getNextDoingMethods().get(i);
            ability.DoCardAbility();
        }
    }

    public void playDecoycard(UnitCard decoycard,Player player){
        GetAbility.getAbility(decoycard,gameStatus,player,this);
        passroundCard();
        passroundAbility();
        passroundCard();
        passroundweatherability();
        passroundCard();
        gameStatus.increaseNumberOfTurns();
        passfactionround();
        passroundCard();
    }

    public void passroundweatherability() {
        nextweatherabilitydoing.DoCardAbility();
    }

    public void passroundCard() {
        for (ObservableRow row : gameStatus.getTable().getRows()) {
            for (Card card : row.getCards()) {
                if (card instanceof UnitCard unitCard) unitCard.UpdatePower();
            }
        }
    }

    public void passfactionround() {
        if (notfinishedyet) {
            gameStatus.getFaction1abilitydoing().DoCardAbility();
            gameStatus.getFaction2abilitydoing().DoCardAbility();
        } else {

        }
    }
    public void passround(){
        passfactionround();
        passroundCard();
        passroundAbility();
        passroundCard();
        passroundweatherability();
        passroundCard();
        checknotfinishedyet();
        gameStatus.increaseNumberOfTurns();

    }
    public void placeweathercard(Card weathercard, Player player) {
        GetwaetherAbility.getAbility(weathercard, gameStatus, player, this);
        getNextweatherdoingAbilitys().removeAll(getNextweatherdoingAbilitys());
        //TODO:insert weather card in gamestatus
    }

    public void Initialize() {
        faction1 = gameStatus.getPlayer1().getFaction();
        faction2 = gameStatus.getPlayer2().getFaction();
    }
    public boolean getLeaderdidfromplayer(Player player){
        if(player.equals(gameStatus.getPlayer1())){
            return gameStatus.didLeader1Do();
        }else if (player.equals(gameStatus.getPlayer2())){
            return gameStatus.didLeader2Do();
        }else{
            return false;
        }
    }
    public void setLeaderdidfromplayer(Player player, boolean x){
        if (player.equals(gameStatus.getPlayer1())) gameStatus.setLeader1Did(x);
        else if (player.equals(gameStatus.getPlayer2())) gameStatus.setLeader2Did(x);
        else System.out.println("Player is not in this Game!!");
    }

    public void checknotfinishedyet() {
        if (notfinishedyet) {
            if (gameStatus.getNumberOfPassedRounds() == 2) {
                notfinishedyet = false;
                finishoneround();
            } else {
            }
        }else{
            finishoneround();
        }
    }
    public void finishoneround(){
        int player1score=0;
        int player2score=0;
        ObservableRow[] rows = gameStatus.getTable().getRows();
        for(int i=1;i<4;i++){
            for (Card card : rows[i].getCards()) {
                if (card instanceof UnitCard unitCard) player1score = player1score + unitCard.getPower();
            }
        }
        for(int i=4;i<7;i++){
            for (Card card : rows[i].getCards()) {
                if (card instanceof UnitCard unitCard) player1score = player1score + unitCard.getPower();
            }
        }
        if(player1score>player2score){

        }else if(player2score>player1score){

        }else{
            //todo;one faction ability comes here
        }
    }

}
