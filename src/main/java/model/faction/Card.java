package model.faction;

abstract public class Card {
    protected final CardAbilityPerformer ability;
    protected String name;
    protected boolean isUnitCard;
    protected boolean isHero = false;
    protected Integer rowNumber = null;
    protected int power;
    private boolean transformed = false;

    public Card(String name, boolean isUnitCard, CardAbilityPerformer ability, int power) {
        this.name = name;
        this.isUnitCard = isUnitCard;
        this.ability = ability;
        this.power = power;
    }

    public void transform() {
        transformed = true;
    }
}
