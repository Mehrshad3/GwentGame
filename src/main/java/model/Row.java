package model;

import enums.card.RowWeather;
import model.faction.Card;
import model.faction.SpellCard;

public class Row {
    private final Card[] cards = new Card[9];
    private boolean specialCardExists = false;
    private RowWeather weather = RowWeather.CLEAR_WEATHER;
    private SpellCard spell = null;

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

    public void placeCard(Card card) {
        // TODO
    }

    public void removeCard(Card card) {
        // TODO
    }

    public void setSpecialCardExists(boolean specialCardExists) {
        this.specialCardExists = specialCardExists;
    }

    public Card[] getCards() {
        return cards;
    }

    public boolean doesSpecialCardExist() {
        return specialCardExists;
    }
}