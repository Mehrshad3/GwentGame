package model;

import enums.card.RowWeather;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.faction.SpellCard;
import model.faction.UnitCard;

public class ObservableRow {
    private final Row row;
    private final ObservableList<UnitCard> unitCards = FXCollections.observableArrayList();
    private final ObjectProperty<RowWeather> weather = new SimpleObjectProperty<>(RowWeather.CLEAR_WEATHER);
    private final ObjectProperty<SpellCard> spellCard = new SimpleObjectProperty<>();

    public ObservableRow(Row row) {
        this.row = row;
        unitCards.addListener((ListChangeListener<? super UnitCard>) change -> {
            change.next();
            if (change.wasAdded()) {
                for (int i = change.getFrom(); i < change.getTo(); i++) {
                    row.placeCard(i, change.getList().get(i));
                }
            } else if (change.wasRemoved()) {
                assert change.getFrom() == change.getTo();
                for (int i = 0; i < change.getRemovedSize(); i++) {
                    row.getCards().remove(change.getFrom());
                }
            }
        });
        weather.addListener((observableValue, rowWeather, t1) -> row.setWeather(t1));
        spellCard.addListener((observableValue, spellCard1, t1) -> row.setSpell(t1));
    }

    public ObservableRow(short rowNumber) {
        this(new Row(rowNumber));
    }

    public ObservableValue<SpellCard> getSpell() {
        return spellCard;
    }

    public void setSpell(SpellCard spell) {
        this.spellCard.set(spell);
    }

    public ObservableValue<RowWeather> getWeather() {
        return weather;
    }

    public void setWeather(RowWeather weather) {
        this.weather.set(weather);
    }

    public void placeCard(UnitCard card) {
        unitCards.add(card);
    }

    public void placeCard(int index, UnitCard card) {
        unitCards.add(index, card);
    }

    public void removeCard(UnitCard card) {
        unitCards.remove(card);
    }

    public ObservableList<UnitCard> getUnitCards() {
        return unitCards;
    }
}
