package model.faction;

import controller.AbilityDoings.CommandersHornAbility;
import controller.AbilityDoings.MoralBoostAbility;
import enums.EnumAbilities.Abilities;
import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.UnitOrSpellCardAbility;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class UnitCard extends Card {
    public final boolean isHero;
    public boolean isWeatherChanged = false;

    public boolean ispowerlocked = false;

    public int beforeweatherchangepower = 0;
    public ArrayList<UnitCard> rowmates = new ArrayList<UnitCard>();
    public boolean bringerofdeath = false;
    protected transient IntegerProperty powerProperty;


    public UnitCard(CardName cardName, String name, PossibleRowsToPlayCard rows, UnitOrSpellCardAbility ability,
                    int power, boolean isHero) {
        super(cardName, name, rows, ability, power);
        this.isHero = isHero;
        this.powerProperty = new SimpleIntegerProperty(power);
        assert rows != PossibleRowsToPlayCard.SPECIAL;
    }

    public void resetPower() {
        this.powerProperty.set(this.initialPower);
    }

    public int getPower() {
        return this.powerProperty.get();
    }

    public void setPower(int newPower) {
        if (!this.isHero) {
            if (!ispowerlocked)
                this.powerProperty.set(newPower);
        }
    }

    public IntegerProperty getPowerProperty() {
        return this.powerProperty;
    }

    public boolean isHero() {
        return this.isHero;
    }

    public boolean isWeatherChanged() {
        return isWeatherChanged;
    }

    public void setWeatherChanged(boolean weatherChanged) {
        isWeatherChanged = weatherChanged;
    }

    public int getBeforeweatherchangepower() {
        return beforeweatherchangepower;
    }

    public void setBeforeweatherchangepower(int beforeweatherchangepower) {
        this.beforeweatherchangepower = beforeweatherchangepower;
    }

    public boolean isIspowerlocked() {
        return ispowerlocked;
    }

    public void setIspowerlocked(boolean ispowerlocked) {
        this.ispowerlocked = ispowerlocked;
    }

    public ArrayList<UnitCard> getRowmates() {
        return rowmates;
    }

    public void setRowmates(ArrayList<UnitCard> rowmates) {
        this.rowmates = rowmates;
    }

    public void UpdatePower() {
        if (isWeatherChanged) {
            if (gameStatus.KingBranAbility) {
                setPower(initialPower / 2);
            } else {
                setPower(1);
            }
        } else {
        }
        if (gameStatus.TheTreacherousAbility) {
            if (name.equals("spy")) {
                setPower(getPower() * 2);
            } else {
            }
        }
        for (Card card : rowmates) {
            boolean a = Abilities.map.get(card.name.toLowerCase()).Abilityname.getClass() == CommandersHornAbility.class;
            //TODO:tightBound ability must do
        }
        for (Card card : rowmates) {
            boolean a = Abilities.map.get(card.name.toLowerCase()).Abilityname.getClass() == MoralBoostAbility.class;
            setPower(getPower() + 1);
        }
        for (Card card : rowmates) {
            boolean a = Abilities.map.get(card.name.toLowerCase()).Abilityname.getClass() == CommandersHornAbility.class;
            if (a) {
                setPower(getPower() * 2);
            } else {
            }
        }
        if (bringerofdeath) {
            setPower(getPower() * 2);
        } else {
        }
    }

}