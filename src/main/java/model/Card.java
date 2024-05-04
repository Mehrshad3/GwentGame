package model;

abstract public class Card {
    private String name;
    private boolean transformed = false;

    public void transform() {
        transformed = true;
    }
}
