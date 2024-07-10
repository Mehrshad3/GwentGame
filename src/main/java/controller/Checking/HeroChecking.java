package controller.Checking;

import model.faction.Card;
import model.faction.UnitCard;

public class HeroChecking {
    public static Boolean HeroChecking(Card card) {
        if (card.getClass().equals(UnitCard.class)) {

            Boolean isHero0 = false;
            if ( ((UnitCard) card).isHero) {
                isHero0 = true;
            } else {
            }
            return isHero0;
        }else {
            return false;
        }
    }
}
