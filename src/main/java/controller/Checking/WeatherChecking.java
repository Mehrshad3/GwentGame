package controller.Checking;

import enums.EnumAbilities.Abilities;
import model.faction.Card;

public class WeatherChecking {
    public static boolean weatherchecking(Card card){
        boolean answer=false;
        Abilities abilities=Abilities.map.get(card.getName().toLowerCase());
        if(abilities.Cardname.toLowerCase().equals("biting frost")){
            answer=true;
        }else if(abilities.Cardname.toLowerCase().equals("impenetrable fog")){
            answer=true;
        }else if(abilities.Cardname.toLowerCase().equals("torrential rain")){
            answer=true;
        }else if(abilities.Cardname.toLowerCase().equals("skellige storm")){
            answer=true;
        }else if(abilities.Cardname.toLowerCase().equals("clear weather")){
            answer=true;
        }else{
            answer=false;
        }
        return answer;
    }
}
