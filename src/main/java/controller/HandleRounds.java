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
        for (UnitCard unitCard : row0.getUnitCards()) {
            ArrayList<UnitCard> newrowmates0 = new ArrayList<UnitCard>(card.getRowmates());
            newrowmates0.add(unitCard);
            card.setRowmates(newrowmates0);
            ArrayList<UnitCard> newrowmates1 = new ArrayList<>(unitCard.getRowmates());
            newrowmates1.add(card);
            unitCard.setRowmates(newrowmates1);
        }
        GetAbility.getAbility(card, gameStatus, player, this);
        passRoundAbility();
        row0.placeCard(card);
        card.setRowNumber(row);
        player.getDeck().getInHandCards().remove(card);
        passroundweatherability();
        passroundCard();
    }

    /**
     * This method is used when the player has put all of their cards to the board, and wants to give the turn to their
     * opponent.
     */
    public void passRound() {
        // I'm not sure with the correct order of these methods, so they should probably be changed later.
        passRoundAbility();
        passroundweatherability();
        passroundCard();
    }

    public  void passRoundAbility() {
        // This reversed loop prevents ConcurrentModificationException.
        for (int i = getNextDoingMethods().size() - 1; i >= 0; i--) {
            Ability ability = getNextDoingMethods().get(i);
            ability.DoCardAbility();
        }
    }

    public void passroundweatherability() {
        for (Ability ability : getNextweatherdoingAbilitys()) {
            ability.DoCardAbility();
        }
    }

    public void passroundCard() {
        for (ObservableRow row : gameStatus.getTable().getRows()) {
            for (UnitCard unitCard : row.getUnitCards()) {
                unitCard.UpdatePower();
            }
        }
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

}
