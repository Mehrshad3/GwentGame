package controller.Checking;

import controller.AbilityDoings.SpellsAbilityDoing.BitingFrostAbilityDoing;
import model.faction.Card;

public class CheckingIsWeather {
    public static boolean IsWeather(Card card){
        boolean a=false;
        if(card.getName().toLowerCase().equals("bitingfrost")){
            a=true;
        }else if(card.getName().toLowerCase().equals("clearweather")){
            a=true;
        }else if(card.getName().toLowerCase().equals("impenetrablefog")){
            a=true;
        }else if (card.getName().toLowerCase().equals("skelligestorm")) {
            a=true;
        }else if(card.getName().toLowerCase().equals("torrentialrain")){
            a=true;
        }else{}
        return a;
    }
}
