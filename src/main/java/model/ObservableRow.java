package model;

import enums.card.CardName;
import enums.card.RowWeather;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.faction.Card;
import model.faction.SpellCard;
import model.faction.UnitCard;

import java.util.stream.Stream;

public class ObservableRow {
    private final Row row;
    private final ObservableList<Card> unitCards = FXCollections.observableArrayList();
    private final ObjectProperty<RowWeather> weather = new SimpleObjectProperty<>(RowWeather.CLEAR_WEATHER);
    private final ObjectProperty<SpellCard> spellCard = new SimpleObjectProperty<>();
    private final IntegerProperty power = new SimpleIntegerProperty();

    public ObservableRow(Row row) {
        this.row = row;
        unitCards.addListener((ListChangeListener<? super Card>) change -> {
            change.next();
            if (change.wasAdded()) {
                for (int i = change.getFrom(); i < change.getTo(); i++) {
                    Card cardAdded = change.getList().get(i);
                    row.placeCard(i, cardAdded);
                    if (cardAdded instanceof UnitCard unitCard) {
                        unitCard.getPowerProperty().addListener((observableValue, number, t1) -> updateTotalPower());
                    }
                }
            } else if (change.wasRemoved()) {
                assert change.getFrom() == change.getTo();
                for (int i = 0; i < change.getRemovedSize(); i++) {
                    row.getCards().remove(change.getFrom());
                }
            }
            updateTotalPower();
        });
        weather.addListener((observableValue, rowWeather, t1) -> row.setWeather(t1));
        spellCard.addListener((observableValue, spellCard1, t1) -> row.setSpell(t1));
    }

    public ObservableRow(short rowNumber) {
        this(new Row(rowNumber));
    }

    public IntegerProperty powerProperty() {
        return this.power;
    }

    void updateTotalPower() {
        int sumOfPowers = 0;
        for (Card card : unitCards) {
            if (card instanceof UnitCard unitCard) {
                sumOfPowers += unitCard.getPower();
            }
        }
        power.set(sumOfPowers);
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

    public void placeCard(int index, Card card) {
        assert card instanceof UnitCard || card.getCardName() == CardName.DECOY;
        unitCards.add(index, card);
    }

    public void removeCard(UnitCard card) {
        unitCards.remove(card);
    }

    public ObservableList<Card> getCards() {
        return unitCards;
    }

    public UnitCard getSpcialpot() {
        return row.getSpcialpot();
    }
}
