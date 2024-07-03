package controller.Checking;

import controller.AbilityDoings.BerserkerAbilityDoing;
import enums.EnumAbilities.Abilities;
import model.faction.UnitCard;

public class BerserkerChecking {
    public static Boolean berserkerchecking(UnitCard card){
        Boolean isBerserker=false;
        Abilities abilities=Abilities.map.get(card.getName().toLowerCase());
        if(abilities.Abilityname.getClass()== BerserkerAbilityDoing.class){
            isBerserker=true;
        }
        return  isBerserker;
    }
}
