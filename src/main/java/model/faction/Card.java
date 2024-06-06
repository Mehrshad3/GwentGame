package model.faction;
import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.CardAbility;
import model.GameStatus;

public abstract class Card {

    protected final CardAbility ability;
    protected final PossibleRowsToPlayCard rows;
    protected CardName cardName;
    protected Integer initialPower;
    protected String name;
    protected boolean isHero = false;
    protected Integer rowNumber = null;
    protected boolean transformed = false;

    public Card(CardName cardName, String name, PossibleRowsToPlayCard rows, CardAbility ability, Integer initialPower) {
        this.cardName = cardName;
        this.name = name;
        this.rows = rows;
        this.ability = ability;
        this.initialPower = initialPower;
    }

    public void doAbility(GameStatus gaming) {
        if (ability != null) this.ability.doAction(gaming, this);
    }

    public void transform() {
        transformed = true;
        // TODO: really transform the card
        // Note that TransformerCard has a method that returns a new card, so maybe this method should be removed.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
