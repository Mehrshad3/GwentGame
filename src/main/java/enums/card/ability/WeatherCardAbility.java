package enums.card.ability;

import controller.GameController;
import enums.card.RowWeather;
import model.Row;
import model.faction.Card;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum WeatherCardAbility implements CardAbility {
    BITING_FROST(((gameController, card) -> {
        gameController.setRowWeather(2, RowWeather.BITING_FROST);
        gameController.setRowWeather(3, RowWeather.BITING_FROST);
    })),
    IMPENETRABLE_FOG(((gameController, card) -> {
        gameController.setRowWeather(1, RowWeather.IMPENETRABLE_FOG);
        gameController.setRowWeather(4, RowWeather.IMPENETRABLE_FOG);
    })),
    TORRENTIAL_RAIN(((gameController, card) -> {
        gameController.setRowWeather(0, RowWeather.TORRENTIAL_RAIN);
        gameController.setRowWeather(5, RowWeather.TORRENTIAL_RAIN);
    })),
    SKELLIGE_STORM((gameController, card) -> {
        IMPENETRABLE_FOG.doAction(gameController, card);
        TORRENTIAL_RAIN.doAction(gameController, card);
    }),
    CLEAR_WEATHER(((gameController, card) -> {
        for (int i = 0; i < 6; i++) {
            gameController.setRowWeather(i, RowWeather.CLEAR_WEATHER);
        }
    }));
    private final BiConsumer<GameController, Card> action;

    WeatherCardAbility(BiConsumer<GameController, Card> action) {
        this.action = action;
    }

    private static Row[] getRows(GameController gameController) {
        return (Row[]) Arrays.stream(gameController.getRows()).toArray();
    }

    @Override
    public void doAction(GameController gaming, Card card) {
        this.action.accept(gaming, card);
        // TODO: think whether it should notify the gameController or not.
    }
}
