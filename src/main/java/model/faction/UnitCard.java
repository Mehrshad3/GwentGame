package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.UnitOrSpellCardAbility;

public class UnitCard extends Card {
    public UnitCard(CardName cardName, String name, PossibleRowsToPlayCard rows, UnitOrSpellCardAbility ability,
                    int power, boolean isHero) {
        super(cardName, name, rows, ability, power);
        this.isHero = isHero;
        assert rows != PossibleRowsToPlayCard.SPECIAL;
    }
}