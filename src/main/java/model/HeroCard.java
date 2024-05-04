package model;

public class HeroCard extends Card {
    public HeroCard(String name, boolean isUnitCard, String ability, int power) {
        super(name, isUnitCard, ability, power);
        super.isHero = true;
    }
}
