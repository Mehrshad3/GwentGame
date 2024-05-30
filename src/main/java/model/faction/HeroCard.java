package model.faction;

public class HeroCard extends Card {
    public HeroCard(String name, boolean isUnitCard, CardAbility ability, int power) {
        super(name, isUnitCard, ability, power);
        super.isHero = true;
    }
}
