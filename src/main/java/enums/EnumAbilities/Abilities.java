package enums.EnumAbilities;

import controller.AbilityDoings.*;
import controller.AbilityDoings.LeadersAbilityDoings.*;
import enums.card.ability.UnitOrSpellCardAbility;
import enums.card.ability.WeatherCardAbility;

import java.util.HashMap;
import java.util.Map;

import static enums.card.PossibleRowsToPlayCard.*;
import static enums.card.PossibleRowsToPlayCard.CLOSE_COMBAT;
import static enums.card.ability.CommanderCardAbility.*;
import static enums.card.ability.CommanderCardAbility.SON_OF_MEDELL_ABILITY_PERFORMER;
import static enums.card.ability.UnitOrSpellCardAbility.*;
import static model.faction.Faction.*;
import static model.faction.Faction.NEUTRAL;

public enum Abilities {
    // Monsters
    // Leaders
    BRINGER_OF_DEATH("Bringer of Death",new BringerofDeathAbilityDoing()),
    KING_OF_THE_WILD_HUNT("King of the Wild Hunt",new KingofthewildHountAbilityDoing()),
    DESTROYER_OF_WORLDS("Destroyer of Worlds",new DestroyerofWorldsAbilityDoing()),
    COMMANDER_OF_THE_RED_RIDERS("Commander of the Red Riders",new CommanderoftheRedRidersAbilityDoing()),
    THE_TREACHEROUS("The Treacherous",new TheTrecherousAbilityDoing()),
    // Unit cards
    DRAUG("Draug", true),
    IMLERITH("Imlerith",true),
    LESHEN("Leshen",  true),
    KAYRAN("Kayran", new MoralBoostAbility(), true),
    TOAD("Toad", new ScorchAbilityDoing("opponent:ranged combat"), false),
    ARACHAS_BEHEMOTH("Arachas Behemoth",new MusterAbilityDoing(), false),
    CRONE_BREWESS("Brewess", new MusterAbilityDoing() ,false), // TODO: download its image from the internet!
    CRONE_WEAVESS("Weavess", new MusterAbilityDoing(), false),
    CRONE_WHISPESS("Whispess", new MusterAbilityDoing(), false),
    EARTH_ELEMENTAL("Earth Elemental", false),
    FIEND("Fiend", false),
    FIRE_ELEMENTAL("Fire Elemental", false),
    FORK_TAIL("Forktail", false),
    FRIGHTENER("Frightener", false),
    GRAVE_HAG("Grave Hag", false),
    GRIFFIN("Griffin",  false),
    ICE_GIANT("Ice Giant",  false),
    PLAGUE_MAIDEN("Plague Maiden",  false),
    VAMPIRE_KATAKAN("Katakan",new MusterAbilityDoing(), false),
    WEREWOLF("Werewolf", false),
    ARACHAS("Arachas",  false),
    BOTCHLING("Botchling",  false),
    VAMPIRE_BRUXA("Bruxa", new MusterAbilityDoing(), false),
    VAMPIRE_EKIMMARA("Ekimmara", new MusterAbilityDoing(), false),
    VAMPIRE_FLEDER("Fleder", new MusterAbilityDoing(), false),
    VAMPIRE_GARKAIN("Garkain", new MusterAbilityDoing(), false),
    CELAENO_HARPY("Celaeno Harpy",  false),
    COCKATRICE("Cockatrice",  false),
    ENDREGA("Endrega",  false),
    FOGLET("Foglet",  false),
    GARGOYLE("Gargoyle",  false),
    HARPY("Harpy",  false),
    NEKKER("Nekker", new MusterAbilityDoing(), false),
    WYVERN("Wyvern",  false),
    GHOUL("Ghoul", new MusterAbilityDoing(), false),

    // Nilfgaard
    // Leaders

    THE_WHITE_FLAME("the White Flame", new TheWhiteFlameAbilityDoing()),
    HIS_IMPERIAL_MAJESTY("His Imperial Majesty", new HisImperialMajestyAbilityDoing()),
    EMPEROR_OF_NILFGAARD("Emperor of Nilfgaard", new EmprorOfNilfGardAbilityDoing()),
    THE_RELENTLESS("the Relentless", new TheRelentlessAbilityDoing()),
    INVADER_OF_THE_NORTH("Invader of the North", new InvaderoftheNorthAbilityDoing()),
    // Unit cards
    IMPERA_BRIGADE_GUARD("Impera Brigade Guard", new TightBoundAbilityDoing(), false),
    STEFAN_SKELLEN("Stefan Skellen", new SpyAbilityDoing(), false),
    YOUNG_EMISSARY("Young Emissary", new TightBoundAbilityDoing(), false),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach",  false),
    VATTIER_DE_RIDEAUX("Vattier de Rideaux",  false),
    MENNO_COEHOORN("Menno Coehoorn", new MedicAbilityDoing(), true),
    PUTTKAMMER("Puttkammer",  false),
    BLACK_INFANTRY_ARCHER("Black Infantry Archer", false),
    TIBOR_EGGEBRACHT("Tibor Eggebracht",  true),
    RENUALD_AEP_MATSEN("Renuald aep Matsen",  false),
    FRINGILLA_VIGO("Fringilla Vigo",  false),
    ROTTEN_MANGONEL("Rotten Mangonel",  false),
    ZERRIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion",  false),
    SIEGE_ENGINEER("Siege Engineer", false),
    MORVRAN_VOORHIS("Morvran Voorhis",  true),
    CYNTHIA("Cynthia",  false),
    ETOLIAN_AUXILIARY_ARCHERS("Etolian Auxiliary Archers", new MedicAbilityDoing(), false),
    MORTEISEN("Morteisen",  false),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", new TightBoundAbilityDoing(), false),
    SIEGE_TECHNICIAN("Siege Technician", new MedicAbilityDoing(), false),
    SWEERS("Sweers",  false),
    VANHEMAR("Vanhemar",  false),
    VREEMDE("Vreemde",  false), // Source: Random reward from gwent players


    // Skellige Cards
    // Leaders
    CRACH_AN_CRAITE("Crach an Craite", new CrachanCraiteAbilityDoing()),
    KING_BRAN("King Bran", new KingBranAbilityDoing()),
    // Unit cards
    BERSERKER("Berserker", new BerserkerAbilityDoing(), false),
    VILDKAARL("Vildkaarl", new MoralBoostAbility(), false),
    SVANRIGE("Svanrige", false),
    UDALRYK("Udalryk", false),
    DONAR_AN_HINDAR("Donar an Hindar", false),
    CLAN_AN_CRAITE("Clan an Craite", new TightBoundAbilityDoing(), false),
    MADMAN_LUGOS("Madman Lugos",  false),
    CERYS("Cerys", new MusterAbilityDoing(), true), // Its Muster effect will summon Shield Maiden cards and other musters.
    HEMDALL("Hemdall",  true),
    KAMBI("Kambi", new TransformerAbilityDoing("Hemdall"), true), // Turns into a card with a power of 8 after one round
    BIRNA_BRAN("Birna Bran", new MedicAbilityDoing(), false),
    CLAN_DRUMMOND_SHIELD_MAIDEN("Clan Drummond Shield Maiden",new TightBoundAbilityDoing(), false),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", new ScorchAbilityDoing("general:opponent"), false),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer",  false),
    ERMION("Ermion", new MardroemeAbilityDoing(), true),
    HJALMAR("Hjalmar",  true),
    YOUNG_BERSERKER("Young Berserker", new BerserkerAbilityDoing(), false),
    YOUNG_VILDKAARL("Young Vildkaarl", new TightBoundAbilityDoing(), false),
    LIGHT_LONGSHIP("Light Longship",new MusterAbilityDoing(), false),
    WAR_LONGSHIP("War Longship", new TightBoundAbilityDoing(), false),
    DRAIG_BON_DHU("Draig Bon-Dhu", new CommandersHornAbility(), false),
    OLAF("Olaf", new MoralBoostAbility(), false),

    // Scoia'tael
    // Leaders
    QUEEN_OF_DOL_BLATHANNA("Queen of Dol Blathanna", new QueenofDolBalsannaAbilityDoing()),
    THE_BEAUTIFUL("the Beautiful", new TheBeautifulAbilityDoing()),
    DAISY_OF_THE_VALLEY("Daisy of the Valley", new DaisyoftheValleyAbilityDoing()),
    PURE_BLOOD_ELF("Pureblood Elf", new PurebloodElfAbilityDoing()),
    HOPE_OF_THE_AEN_SEIDHE("Hope of the Aen Seidhe", new HopeoftheAenSeidheAbilityDoing()),
    // Unit cards
    ELVEN_SKIRMISHER("Elven Skirmisher", new MusterAbilityDoing(), false),
    YAEVINN("Yaevinn",  false),
    CIARAN_AEP("Ciaran Aep", false),
    DENNIS_CRANMER("Dennis Cranmer",  false),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", false),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer",  false),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", new MusterAbilityDoing(), false),
    HAVEKAR_HEALER("Havekar Healer", new MedicAbilityDoing(), false),
    HAVEKAR_SMUGGLER("Havekar Smuggler", new MusterAbilityDoing(), false),
    IDA_EMEAN_AEP("Ida Emean Aep",  false),
    RIORDAIN("Riordain",  false),
    TORUVIEL("Toruviel",  false),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit",  false),
    VRIHEDD_BRIGADE_VETERAN("Vrihedd Brigade Veteran",  false),
    MILVA("Milva", new MoralBoostAbility(), false),
    SAESENTHESSIS("Saesenthessis",  true),
    SCHIRRU("Schirrú", new ScorchAbilityDoing("siege combat:opponent"), false), // kills the opponent's card(s) with most power in enemy's Siege combat row if the sum of powers of none-hero cards in this row is 10 or more
    EITHNE("Eithne",  true),
    ISENGRIM_FAOILTIARNA("Isengrim Faoiltiarna",new MoralBoostAbility(), true),

    // Northern Realms
    // Leaders
    THE_SIEGE_MASTER("the Siegemaster", new TheSiegemasterAbilityDoing()),
    THE_STEEL_FORGED("the Steel-Forged", new TheSteelForgedAbilityDoing()),
    KING_OF_TEMERIA("King of Temeria", new KingOfTemeriaAbilityDoing()),
    LORD_COMMANDER_OF_THE_NORTH("Lord Commander of the North", new LordCommanderOfTheNorthAbilityDoing()),
    SON_OF_MEDELL("Son of Medell", new SunOfMedellAbilityDoing()),
    // Unit cards
    BALLISTA("Ballista",  false),
    CATAPULT("Catapult", new TightBoundAbilityDoing(), false),
    DRAGON_HUNTER("Dragon Hunter", new TightBoundAbilityDoing(), false),
    DUN_BANNER_MEDIC("Dun banner Medic", new MedicAbilityDoing(), false),
    ESTERAD_THYSSEN("Esterad Thyssen",  true),
    JOHN_NATALIS("John Natalis",  true),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", new MoralBoostAbility(), false),
    PHILIPPA_EILHART("Philippa Eilhart",  true),
    POOR_INFANTRY("Poor Infantry", new TightBoundAbilityDoing(), false),
    PRINCE_STENNIS("Prince Stennis", new SpyAbilityDoing(), false),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier",  false),
    SABRINA_GLEVISSIG("Sabrina Glevissig",  false),
    SIEGE_TOWER("Siege Tower",  false),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle",  false),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra",false),
    SHEALA_DE_TANCARVILLE("Síle de Tansarville", false),
    THALER("Thaler", new SpyAbilityDoing(), false),
    VERNON_ROCHE("Vernon Roche",  true),
    VES("Ves", false),
    YARPEN_ZIGRIN("Yarpen Zigrin",  false),

    // Neutral cards
    // Spells
    MARDROEME("Mardroeme", new MardroemeAbilityDoing()),
    DECOY("Decoy", new DecoyAbilityDoing()),
    SCORCH("Scorch", new ScorchAbilityDoing("general")),
    COMMANDER_HORN("Commander's Horn", new CommandersHornAbility()),
    // Weather cards
    BITING_FROST("Biting Frost", "biting frost"),
    IMPENETRABLE_FOG("Impenetrable Fog", "impenetrable fog"),
    TORRENTIAL_RAIN("Torrential Rain", "torrential rain"),
    // TODO: add skellige storm and clear weather
    // Neutral unit cards
    DANDELION("Dandelion", new CommandersHornAbility(), false),
    EMIEL_REGIS("Emiel Regis",  false),
    GAUNTER_O_DIMM("Gaunter O'Dimm", new MusterAbilityDoing(), false),
    GAUNTER_O_DIMM_DARKNESS("Gaunter O'Dimm: Darkness", new MusterAbilityDoing(), false),
    MYSTERIOUS_ELF("Mysterious Elf", new SpyAbilityDoing(), true),
    OLGIERD_VON_EVEREC("Olgierd von Everec", new MoralBoostAbility(), false),
    TRISS_MERIGOLD("Triss merigold",  true),
    VILLENTRETENMERTH("Villentretenmerth", new ScorchAbilityDoing("Close combat:oponnent"), false),
    ;
    public static final Map<String, Abilities> map = new HashMap<>(Abilities.values().length);

    static {
        for (Abilities abilities : Abilities.values()) {
            map.put(abilities.Cardname.toLowerCase(), abilities);
        }
    }

    public String Cardname;
    public Ability Abilityname = new TransformerAbilityDoing();
    public boolean Ishero;

    public String spellpower;

    Abilities(String name, Ability AbilityName, boolean Ishero) {
        this.Cardname = name;
        this.Abilityname = AbilityName;
        this.Ishero = Ishero;
    }

    Abilities(String name, Ability AbilityName) {
        this.Cardname = name;
        this.Abilityname = AbilityName;
    }

    Abilities(String name) {
        this.Cardname = name;
    }

    Abilities(String name, boolean Ishero) {
        this.Cardname = name;
        this.Ishero = Ishero;
    }

    Abilities(String name, String spellpower) {
        this.Cardname = name;
        this.spellpower = spellpower;
    }

}
