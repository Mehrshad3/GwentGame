package controller.Checking;

import controller.AbilityDoings.Ability;
import controller.AbilityDoings.BerserkerAbilityDoing;
import controller.HandleRounds;
import enums.EnumAbilities.Abilities;
import model.GameStatus;
import model.Player;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.Arrays;
import java.util.Locale;

public class GetAbility {

        public static void getAbility(UnitCard card, GameStatus gameStatus, Player player, HandleRounds handleRounds){
            //TODO:Must get AbilityDoing and add it to nextdoingabilities of gamestatus
            String name= card.getName().toLowerCase();
            Abilities cardabilitystatus=Abilities.map.get(name);
            handleRounds.getNextDoingMethods().add(cardabilitystatus.Abilityname);

        }
    }
