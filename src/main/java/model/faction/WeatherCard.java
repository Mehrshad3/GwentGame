package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.WeatherCardAbility;

public class WeatherCard extends Card {
    public WeatherCard(CardName cardName, String name, WeatherCardAbility ability) {
        super(cardName, name, PossibleRowsToPlayCard.SPECIAL, ability, null);
    }
}
