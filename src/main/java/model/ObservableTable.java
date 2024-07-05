package model;

import enums.card.RowWeather;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.faction.SpellCard;
import model.faction.WeatherCard;

public class ObservableTable {
    final ObservableRow[] rows = new ObservableRow[6];
    private final Table table;
    private final ObservableList<WeatherCard> weatherCards = FXCollections.observableArrayList();

    public ObservableTable(Table table) {
        this.table = table;
        for (short i = 0; i < rows.length; i++) rows[i] = new ObservableRow(i);
        weatherCards.addListener((ListChangeListener<? super WeatherCard>) change -> {
            change.next();
            if (change.wasAdded()) {
                for (int i = change.getFrom(); i < change.getTo(); i++) {
                    table.addWeather(change.getList().get(i));
                }
            } else if (change.wasRemoved()) {
                assert change.getFrom() == change.getTo();
                for (int i = 0; i < change.getRemovedSize(); i++) {
                    table.getWeatherCards().remove(change.getFrom());
                }
            }
        });
    }

    public ObservableTable(Player player1, Player player2) {
        this(new Table(player1, player2));
    }

    public ObservableList<WeatherCard> getWeatherCards() {
        return weatherCards;
    }

    public ObservableRow[] getRows() {
        return rows;
    }

    public Player getPlayer1() {
        return table.getPlayer1();
    }

    public Player getPlayer2() {
        return table.getPlayer2();
    }

    public int getCurrentPlayerPlaying() {
        return table.getCurrentPlayerPlaying();
    }

    int getNumberOfTurnsPassed() {
        return table.getNumberOfTurnsPassed();
    }

    public int getNumberOfRounds() {
        return table.getNumberOfRounds();
    }

    public Deck getPlayer1Deck() {
        return table.getPlayer1Deck();
    }

    public Deck getPlayer2Deck() {
        return table.getPlayer2Deck();
    }

    public void addSpell(SpellCard spellCard, int rowIndex) {
        ObservableRow rowToAddSpell = rows[rowIndex];
        rowToAddSpell.setSpell(spellCard);
    }

    public ObservableValue<RowWeather>[] getCurrentWeather() {
        ObservableValue<RowWeather>[] weathers = new ObservableValue[3];
        for (int i = 0; i < weathers.length; i++) {
            weathers[i] = rows[i].getWeather();
        }
        return weathers;
    }

    public void addWeather(WeatherCard weather) {
        weatherCards.add(weather);
    }

    void changeTurn() {
        table.changeTurn();
    }

    public void finishRound() {
        table.finishRound();
    }
}