package model;

import enums.card.RowWeather;
import model.faction.SpellCard;
import model.faction.WeatherCard;

import java.util.LinkedList;

public class Table {
    final Row[] rows = new Row[6];
    final Player player1;
    final Player player2;
    final LinkedList<WeatherCard> weatherCards = new LinkedList<>();
    int currentPlayerPlaying = 1;
    int numberOfRounds;
    Deck player1Deck;
    Deck player2Deck;
    private int numberOfTurnsPassed = 0;

    public Table(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Deck = player1.getDeck();
        player2Deck = player2.getDeck();
        for (short i = 0; i < rows.length; i++) {
            rows[i] = new Row((short) (i + 1));
        }
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

    public void addSpell(SpellCard spellCard, int rowNumber) {
        Row rowToAddSpell = rows[rowNumber];
        rowToAddSpell.setSpell(spellCard);
    }

    public RowWeather[] getCurrentWeather() {
        RowWeather[] weathers = new RowWeather[3];
        for (int i = 0; i < weathers.length; i++) {
            weathers[i] = rows[i].getWeather();
        }
        return weathers;
    }

    public void setCurrentWeather(WeatherCard weather) {
        weather.doAbility(App.getAppObject().getGaming());
    }

    void changeTurn() {
        currentPlayerPlaying = 3 - currentPlayerPlaying;
        numberOfTurnsPassed += 1;
    }

    public void finishRound() {
        this.numberOfRounds += 1;
    }
}
