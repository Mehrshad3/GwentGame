package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.CardAbility;

public class SpellCard extends Card {
    public SpellCard(CardName cardName, String name, CardAbility ability) {
        super(cardName, name, PossibleRowsToPlayCard.SPECIAL, ability, null);
    }
}