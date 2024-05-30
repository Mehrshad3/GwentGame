package enums.card.ability;

import enums.card.RowWeather;
import javafx.scene.Scene;
import model.Row;

import java.util.function.Consumer;

public enum WeatherCardAbility implements CardAbility {
    BITING_FROST((scene -> {
        getRows()[2].setWeather(RowWeather.BITING_FROST);
        getRows()[3].setWeather(RowWeather.BITING_FROST);
    })),
    IMPENETRABLE_FOG((scene -> {
        getRows()[1].setWeather(RowWeather.IMPENETRABLE_FOG);
        getRows()[4].setWeather(RowWeather.IMPENETRABLE_FOG);
    })),
    TORRENTIAL_RAIN((scene -> {
        getRows()[0].setWeather(RowWeather.TORRENTIAL_RAIN);
        getRows()[5].setWeather(RowWeather.TORRENTIAL_RAIN);
    })),
    // TWO last cards of the AH MZ table are omitted because they don't appear in the official document.
    ;
    private final Consumer<Scene> action;

    WeatherCardAbility(Consumer<Scene> action) {
        this.action = action;
    }

    private static Row[] getRows() {
        return CardAbility.getTable().getRows();
    }

    @Override
    public void doAction(Scene scene) {
        this.action.accept(scene);
        // TODO: think whether it should notify the gameController or not.
    }
}
