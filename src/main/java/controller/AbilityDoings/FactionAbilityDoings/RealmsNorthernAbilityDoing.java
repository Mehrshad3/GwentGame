package controller.AbilityDoings.FactionAbilityDoings;

public class RealmsNorthernAbilityDoing extends FactionAbility {
    @Override
    public void DoCardAbility() {

    }

    @Override
    public FactionAbility Copy() {
        RealmsNorthernAbilityDoing abilityDoing=new RealmsNorthernAbilityDoing();
        abilityDoing.setGameStatus(gameStatus);
        abilityDoing.setPlayer(player);
        return  abilityDoing;
    }


}
