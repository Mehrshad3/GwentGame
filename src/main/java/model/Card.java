package model;

abstract public class Card {
    protected String name;
    protected boolean isUnitCard;
    protected boolean isHero = false;

    public Card(String name, boolean isUnitCard, String ability, int power) {
        this.name = name;
        this.isUnitCard = isUnitCard;
        this.ability = ability;
        this.power = power;
    }

    protected Integer rowNumber = null;
    protected final String ability;
    protected int power;
    private boolean transformed = false;

    public void transform() {
        transformed = true;
    }
}
