package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;

public class TransformerCard extends UnitCard {
    private CardName cardToTransformTo;

    public TransformerCard(CardName cardName, String name, PossibleRowsToPlayCard rows, int power, boolean isHero,
                           CardName transformsTo) {
        super(cardName, name, rows, null, power, isHero);
    }

    public UnitCard getTransformedCard() {
        UnitCard transformedCard = (UnitCard) cardToTransformTo.getNewCard();
        transformedCard.transformed = true;
        return transformedCard;
    }
}