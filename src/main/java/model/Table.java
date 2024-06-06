package model;

import enums.card.RowWeather;
import model.faction.Card;
import model.faction.SpellCard;
import model.faction.WeatherCard;

import java.util.ArrayList;

public class Table {
    final Row[] rows = new Row[6];
    final Player player1;
    final Player player2;
    int currentPlayerPlaying = 1;
    int numberOfRounds;
    Deck player1Deck;
    Deck player2Deck;
    ArrayList<SpellCard> spells;
    Card currentSpell = null;
    private int numberOfTurnsPassed = 0;

    public Table(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Deck = player1.getDeck();
        player2Deck = player2.getDeck();
    }

    public Row[] getRows() {
        return rows;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getCurrentPlayerPlaying() {
        return currentPlayerPlaying;
    }

    int getNumberOfTurnsPassed() {
        return numberOfTurnsPassed;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public Deck getPlayer1Deck() {
        return player1Deck;
    }

    public Deck getPlayer2Deck() {
        return player2Deck;
    }

    public ArrayList<SpellCard> getSpells() {
        return spells;
    }

    public Card getCurrentSpell() {
        return currentSpell;
    }

    public void setCurrentSpell(SpellCard currentSpell) {
        spells.add(currentSpell);
        this.currentSpell = currentSpell;
    }

    public RowWeather[] getCurrentWeather() {
        RowWeather[] weathers = new RowWeather[3];
        for (int i = 0; i < weathers.length; i++) {
            weathers[i] = rows[i].getWeather();
        }
        return weathers;
    }

    public void setCurrentWeather(WeatherCard weather) {
        weather.doAbility(null); // TODO: either give gaming to doAbility or remove setCurrentWeather
    }

    void changeTurn() {
        currentPlayerPlaying = 3 - currentPlayerPlaying;
        numberOfTurnsPassed += 1;
    }

    public void finishRound() {
        this.numberOfRounds += 1;
    }
}
