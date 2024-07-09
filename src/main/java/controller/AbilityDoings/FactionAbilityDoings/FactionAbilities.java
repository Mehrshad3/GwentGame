package controller.AbilityDoings.FactionAbilityDoings;

import controller.AbilityDoings.Ability;
import enums.EnumAbilities.Abilities;

import java.util.HashMap;
import java.util.Map;

public enum FactionAbilities {
    EMPIRE_NILFGAARDIAN("empire nilfgardian",new EmpireNilfgaardianAbilityDoing()),

    ;

    public String name;
    public Ability ability;

    public static final Map<String,FactionAbilities> map=new HashMap<>(FactionAbilities.values().length);
    static {
        for(FactionAbilities factionAbilities:FactionAbilities.values()){
            map.put(factionAbilities.name.toLowerCase(),factionAbilities);
        }
    }
    FactionAbilities(String name, Ability abilityDoing) {
    this.name=name;
    this.ability=abilityDoing;
    }
}
