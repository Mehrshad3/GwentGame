package model.faction;

import java.util.Scanner;

import static model.faction.CommanderCardAbility.*;

public enum Faction {
    MONSTERS(null, MonstersLeaderType.class),
    EMPIRE_NILFGAARDIAN(null, NilfgaardianEmpireLeaderType.class),
    REALMS_NORTHERN(null, NorthernRealmLeaderType.class),
    SCOIA_TAELL(null, ScoiaTaellLeaderType.class),
    SKELLIGE(null, SkelligeLeaderType.class),
    ;
    public final Card commander;
    public final Class<? extends leaderCardType> leaderCardTypes;
    private final Runnable ability;

    Faction(Runnable ability, Class<? extends leaderCardType> leaderCardTypes) {
        LeaderCard commander = null; // TODO
        this.ability = ability;
        this.commander = commander;
        this.leaderCardTypes = leaderCardTypes;
    }

    private enum NorthernRealmLeaderType implements leaderCardType {
        THE_SIEGE_MASTER(THE_SIEGE_MASTER_ABILITY_PERFORMER),
        THE_STEEL_FORGED(THE_STEEL_FORGED_ABILITY_PERFORMER),
        KING_OF_TEMERIA(KING_OF_TEMERIA_ABILITY_PERFORMER),
        LORD_COMMANDER_OF_THE_NORTH(LORD_COMMANDER_OF_THE_NORTH_ABILITY_PERFORMER),
        SON_OF_MEDELL(SON_OF_MEDELL_ABILITY_PERFORMER),
        ;
        private final CommanderCardAbility abilityPerformer;

        NorthernRealmLeaderType(CommanderCardAbility abilityPerformer) {
            this.abilityPerformer = abilityPerformer;
        }

        @Override
        public void doAction(Scanner scanner) {
            this.abilityPerformer.doAction(scanner);
        }
    }

    private enum NilfgaardianEmpireLeaderType implements leaderCardType {
        THE_WHITE_FLAME(THE_WHITE_FLAME_ABILITY_PERFORMER),
        HIS_IMPERIAL_MAJESTY(HIS_IMPERIAL_MAJESTY_ABILITY_PERFORMER),
        EMPEROR_OF_NILFGAARD(EMPEROR_OF_NILFGAARD_ABILITY_PERFORMER),
        THE_RELENTLESS(THE_RELENTLESS_ABILITY_PERFORMER),
        INVADER_OF_THE_NORTH(INVADER_OF_THE_NORTH_ABILITY_PERFORMER),
        ;
        private final CommanderCardAbility abilityPerformer;

        NilfgaardianEmpireLeaderType(CommanderCardAbility abilityPerformer) {
            this.abilityPerformer = abilityPerformer;
        }

        @Override
        public void doAction(Scanner scanner) {
            this.abilityPerformer.doAction(scanner);
        }
    }

    private enum MonstersLeaderType implements leaderCardType {
        BRINGER_OF_DEATH(BRINGER_OF_DEATH_ABILITY_PERFORMER),
        KING_OF_THE_WILD_HUNT(KING_OF_THE_WILD_HUNT_ABILITY_PERFORMER),
        DESTROYER_OF_WORLDS(DESTROYER_OF_WORLDS_ABILITY_PERFORMER),
        COMMANDER_OF_THE_RED_RIDERS(COMMANDER_OF_THE_RED_RIDERS_ABILITY_PERFORMER),
        THE_TREACHEROUS(THE_TREACHEROUS_ABILITY_PERFORMER),
        ;
        private final CommanderCardAbility abilityPerformer;

        MonstersLeaderType(CommanderCardAbility abilityPerformer) {
            this.abilityPerformer = abilityPerformer;
        }

        @Override
        public void doAction(Scanner scanner) {
            this.abilityPerformer.doAction(scanner);
        }
    }

    private enum ScoiaTaellLeaderType implements leaderCardType {
        QUEEN_OF_DOL_BLATHANNA(QUEEN_OF_DOL_BLATHANNA_ABILITY_PERFORMER),
        THE_BEAUTIFUL(THE_BEAUTIFUL_ABILITY_PERFORMER),
        DAISY_OF_THE_VALLEY(DAISY_OF_THE_VALLEY_ABILITY_PERFORMER),
        PURE_BLOOD_ELF(PURE_BLOOD_ELF_ABILITY_PERFORMER),
        HOPE_OF_THE_AEN_SEIDHE(HOPE_OF_THE_AEN_SEIDHE_ABILITY_PERFORMER),
        ;
        private final CommanderCardAbility abilityPerformer;

        ScoiaTaellLeaderType(CommanderCardAbility abilityPerformer) {
            this.abilityPerformer = abilityPerformer;
        }

        @Override
        public void doAction(Scanner scanner) {
            this.abilityPerformer.doAction(scanner);
        }
    }

    private enum SkelligeLeaderType implements leaderCardType {
        CRACH_AN_CRAITE(CRACH_AN_CRAITE_ABILITY_PERFORMER),
        KING_BRAN(KING_BRAN_ABILITY_PERFORMER),
        ;
        private final CommanderCardAbility abilityPerformer;

        SkelligeLeaderType(CommanderCardAbility abilityPerformer) {
            this.abilityPerformer = abilityPerformer;
        }

        @Override
        public void doAction(Scanner scanner) {
            this.abilityPerformer.doAction(scanner);
        }
    }

    private interface leaderCardType {
        void doAction(Scanner scanner);
    }
}