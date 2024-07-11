package controller.AbilityDoings.FactionAbilityDoings;

public class EmpireNilfgaardianAbilityDoing extends FactionAbility {

    @Override
    public void DoCardAbility() {

    }

    @Override
    public FactionAbility Copy() {
        EmpireNilfgaardianAbilityDoing abilityDoing=new EmpireNilfgaardianAbilityDoing();
        abilityDoing.setGameStatus(gameStatus);
        abilityDoing.setPlayer(player);
        return  abilityDoing;
    }


}
