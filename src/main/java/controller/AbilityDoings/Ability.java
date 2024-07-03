package controller.AbilityDoings;

import model.faction.Card;

public abstract class Ability {
    public String status;
    public Card maincard;

    public Card getMaincard() {
        return maincard;
    }

    public void setMaincard(Card maincard) {
        this.maincard = maincard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void DoCardAbility() ;
}
