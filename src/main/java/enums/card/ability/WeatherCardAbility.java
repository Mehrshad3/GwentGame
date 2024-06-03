package enums.card.ability;

import enums.card.RowWeather;
import model.GameStatus;
import model.Row;
import model.faction.Card;

import java.util.function.BiConsumer;

public enum WeatherCardAbility implements CardAbility {
    BITING_FROST(((gaming, card) -> {
        getRows()[2].setWeather(RowWeather.BITING_FROST);
        getRows()[3].setWeather(RowWeather.BITING_FROST);
    })),
    IMPENETRABLE_FOG(((gaming, card) -> {
        getRows()[1].setWeather(RowWeather.IMPENETRABLE_FOG);
        getRows()[4].setWeather(RowWeather.IMPENETRABLE_FOG);
    })),
    TORRENTIAL_RAIN(((gaming, card) -> {
        getRows()[0].setWeather(RowWeather.TORRENTIAL_RAIN);
        getRows()[5].setWeather(RowWeather.TORRENTIAL_RAIN);
    })),
    // TWO last cards of the AH MZ table are omitted because they don't appear in the official document.
    ;
    private final BiConsumer<GameStatus, Card> action;

    WeatherCardAbility(BiConsumer<GameStatus, Card> action) {
        this.action = action;
    }

    private static Row[] getRows() {
        return CardAbility.getTable().getRows();
    }

    @Override
    public void doAction(GameStatus gaming, Card card) {
        this.action.accept(gaming, card);
        // TODO: think whether it should notify the gameController or not.
    }
}
