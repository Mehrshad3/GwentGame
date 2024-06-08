package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.UnitOrSpellCardAbility;

public class UnitCard extends Card {
    public final boolean isHero;
    protected int currentPower;

    public UnitCard(CardName cardName, String name, PossibleRowsToPlayCard rows, UnitOrSpellCardAbility ability,
                    int power, boolean isHero) {
        super(cardName, name, rows, ability, power);
        this.isHero = isHero;
        this.currentPower = power;
        assert rows != PossibleRowsToPlayCard.SPECIAL;
    }

    public void resetPower() {
        this.currentPower = this.initialPower;
    }

    public int getPower() {
        return this.currentPower;
    }

    public void setPower(int newPower) {
        if (!this.isHero) this.currentPower = newPower;
    }

    public boolean isHero() {
        return this.isHero;
    }
}