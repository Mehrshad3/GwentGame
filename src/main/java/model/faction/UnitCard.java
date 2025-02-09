package model.faction;

import controller.AbilityDoings.CommandersHornAbility;
import controller.AbilityDoings.MoralBoostAbility;
import controller.AbilityDoings.TightBoundAbilityDoing;
import enums.EnumAbilities.Abilities;
import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.UnitOrSpellCardAbility;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Objects;

public class UnitCard extends Card {
    public final boolean isHero;
    public boolean isWeatherChanged = false;
    public boolean ispowerlocked = false;
    public int beforeweatherchangepower = 0;
    public ArrayList<UnitCard> rowmates = new ArrayList<UnitCard>();
    public boolean bringerofdeath = false;
    public boolean boostpower = false;
    public boolean commanderboostpower = false;
    protected transient IntegerProperty powerProperty;

    public UnitCard(CardName cardName, String name, PossibleRowsToPlayCard rows, UnitOrSpellCardAbility ability,
                    int power, boolean isHero) {
        super(cardName, name, rows, ability, power);
        this.isHero = isHero;
        this.powerProperty = new SimpleIntegerProperty(power);
        assert rows != PossibleRowsToPlayCard.SPECIAL;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UnitCard unitCard)) return false;
        if (!super.equals(object)) return false;
        return isHero == unitCard.isHero
                && isWeatherChanged == unitCard.isWeatherChanged
                && ispowerlocked == unitCard.ispowerlocked
                && beforeweatherchangepower == unitCard.beforeweatherchangepower
                && bringerofdeath == unitCard.bringerofdeath
                && boostpower == unitCard.boostpower
                && commanderboostpower == unitCard.commanderboostpower
                && powerProperty.get() == unitCard.powerProperty.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isHero, isWeatherChanged, ispowerlocked, beforeweatherchangepower,
                bringerofdeath, powerProperty.get(), boostpower, commanderboostpower);
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
            if (gameStatus.isKingBranAbility()) {
                setPower(initialPower / 2);
            } else {
                setPower(1);
            }
        } else {
        }



//        if (gameStatus.isTheTreacherousAbility()) {
//            if (name.equals("spy")) {
//                setPower(getPower() * 2);
//            } else {
//            }
//        }

        if(Abilities.map.get(name.toLowerCase()).Abilityname.getClass()==TightBoundAbilityDoing.class){
            for (Card card : rowmates) {
                int count = 0;
                for (UnitCard unitcard : rowmates) {
                    boolean a0 = Abilities.map.get(unitcard.name.toLowerCase()).Abilityname.getClass() == TightBoundAbilityDoing.class;
                    if (a0) {
                        if (Objects.equals(unitcard.getInitialPower(), initialPower)) {
                            count++;
                        }
                    } else {

                    }
                }
                setPower(getPower() * (count + 1));

            }
        } else {}

            for (Card card : rowmates) {
                boolean a = Abilities.map.get(card.name.toLowerCase()).Abilityname.getClass() == MoralBoostAbility.class;
                setPower(getPower() + 1);
            }
            for (Card card : rowmates) {
                boolean a = Abilities.map.get(card.name.toLowerCase()).Abilityname.getClass() == CommandersHornAbility.class;
                if (a) {
                    boostpower = true;
                } else {
                }
            }
            if (boostpower) {
                setPower(getPower() * 2);
            } else {
                if (commanderboostpower) {
                    setPower(getPower() * 2);
                } else {
                }
            }
        }
    }