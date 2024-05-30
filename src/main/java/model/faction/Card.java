package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.CardAbility;
import javafx.scene.Scene;

abstract public class Card {
    protected final CardAbility ability;
    protected final PossibleRowsToPlayCard rows;
    protected CardName cardName;
    protected Integer initialPower;
    protected String name;
    protected boolean isHero = false;
    protected Integer rowNumber = null;
    private boolean transformed = false;

    public Card(CardName cardName, String name, PossibleRowsToPlayCard rows, CardAbility ability, Integer initialPower) {
        this.cardName = cardName;
        this.name = name;
        this.rows = rows;
        this.ability = ability;
        this.initialPower = initialPower;
    }

    public void doAbility(Scene scene) {
        this.ability.doAction(scene);
    }

    public void transform() {
        transformed = true;
        // TODO: really transform the card
    }
}
