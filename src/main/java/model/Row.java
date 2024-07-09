package model;

import enums.card.CardName;
import enums.card.RowWeather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.faction.Card;
import model.faction.SpellCard;
import model.faction.UnitCard;

public class Row {
    private final ObservableList<Card> unitCards = FXCollections.observableArrayList();
    private final short number;
    public UnitCard spcialpot = null;
    @Deprecated
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
        spell.setRowNumber(this.number);
    }

    public RowWeather getWeather() {
        return weather;
    }

    public void setWeather(RowWeather weather) {
        if (weather != RowWeather.CLEAR_WEATHER) {
            this.specialCardExists = true;
            this.weather = weather;
        } else {
            this.weather = RowWeather.CLEAR_WEATHER;
        }
    }

    public void placeCard(UnitCard card) {
        placeCard(unitCards.size(), card);
    }

    public void placeCard(int index, Card card) {
        assert card instanceof UnitCard || card.getCardName() == CardName.DECOY;
        unitCards.add(index, card);
        card.setRowNumber(this.number);
    }

    public void removeCard(Card card) {
        unitCards.remove(card);
    }

    @Deprecated
    public void setSpecialCardExists(boolean specialCardExists) {
        this.specialCardExists = specialCardExists;
    }

    public ObservableList<Card> getCards() {
        return unitCards;
    }

    @Deprecated
    public boolean doesSpecialCardExist() {
        return specialCardExists;
    }

    UnitCard getSpcialpot() {
        return spcialpot;
    }

    void setSpcialpot(UnitCard spcialpot) {
        this.spcialpot = spcialpot;
    }
}