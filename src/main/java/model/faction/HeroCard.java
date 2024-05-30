package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.UnitOrSpellCardAbility;

public class HeroCard extends UnitCard {
    public HeroCard(CardName cardName, String name, PossibleRowsToPlayCard rows, UnitOrSpellCardAbility ability, int initialPower) {
        super(cardName, name, rows, ability, initialPower);
        super.isHero = true;
    }
}
