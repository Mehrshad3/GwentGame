package controller.Checking;

import controller.AbilityDoings.Ability;
import controller.HandleRounds;
import enums.EnumAbilities.Abilities;
import model.ObservableGameStatus;
import model.Player;
import model.faction.Card;

public class GetAbility {
    public static void getAbility(Card card, ObservableGameStatus gameStatus, Player player,
                                  HandleRounds handleRounds) {
        String name = card.getName().toLowerCase();
        Abilities cardabilitystatus = Abilities.map.get(name);
        Ability ability = cardabilitystatus.Abilityname.Copy(card);
        ability.DoCardAbility();
    }
}
