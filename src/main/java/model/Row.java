package model;

import enums.card.RowWeather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.faction.Card;
import model.faction.SpellCard;
import model.faction.UnitCard;

public class Row {
    private final ObservableList<UnitCard> unitCards = FXCollections.observableArrayList();
    private final short number;
    private boolean specialCardExists = false;
    private RowWeather weather = RowWeather.CLEAR_WEATHER;
    private SpellCard spell = null;

    public Row(short rowNumber) {
        this.number = rowNumber;
    }

    public SpellCard getSpell() {
        return spell;
    }

    public void setSpell(SpellCard spell) {
        this.specialCardExists = true;
        this.spell = spell;
    }

    public RowWeather getWeather() {
        return weather;
    }

    public void setWeather(RowWeather weather) {
        this.specialCardExists = true;
        this.weather = weather;
    }

    public void placeCard(UnitCard card) {
        unitCards.add(card);
        card.setRowNumber(this.number);
    }

    public void removeCard(Card card) {
        // TODO
    }

    public void setSpecialCardExists(boolean specialCardExists) {
        this.specialCardExists = specialCardExists;
    }

    public ObservableList<UnitCard> getCards() {
        return unitCards;
    }

    public boolean doesSpecialCardExist() {
        return specialCardExists;
    }
}