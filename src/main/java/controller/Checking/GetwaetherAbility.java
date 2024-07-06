package controller.Checking;

import controller.AbilityDoings.Ability;
import controller.HandleRounds;
import enums.EnumAbilities.Abilities;
import model.GameStatus;
import model.Player;
import model.faction.Card;
import model.faction.UnitCard;

public class GetwaetherAbility {
    public static void getAbility(Card card, GameStatus gameStatus, Player player, HandleRounds handleRounds){
        String name= card.getName().toLowerCase();
        Abilities cardabilitystatus=Abilities.map.get(name);
        Ability ability=cardabilitystatus.Abilityname.Copy(card);
        if(gameStatus.getTable().getCurrentPlayerPlaying()==1) {
            ability.setPlayer(gameStatus.getPlayer1());
        }else{
            ability.setPlayer(gameStatus.getPlayer2());
        }
        handleRounds.getNextweatherdoingAbilitys().add(ability);

    }
}
