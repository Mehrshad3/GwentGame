package controller.AbilityDoings.FactionAbilityDoings;

public class MonstersAbilityDoing extends FactionAbility {
    @Override
    public void DoCardAbility() {

    }

    @Override
    public FactionAbility Copy() {
        MonstersAbilityDoing abilityDoing=new MonstersAbilityDoing();
        abilityDoing.setGameStatus(gameStatus);
        abilityDoing.setPlayer(player);
        return  abilityDoing;
    }
}
