package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.CommanderCardAbility;

public class LeaderCard extends Card {
    public LeaderCard(CardName cardName, String name, CommanderCardAbility ability) {
        super(cardName, name, PossibleRowsToPlayCard.LEADER_SPOT, ability, null);
    }
}
