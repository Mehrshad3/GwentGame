package enums.EnumAbilities;

import enums.card.ability.UnitOrSpellCardAbility;
import enums.card.ability.WeatherCardAbility;

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
    BRINGER_OF_DEATH("Bringer of Death", "nothing"),
    KING_OF_THE_WILD_HUNT("King of the Wild Hunt", "nothing"),
    DESTROYER_OF_WORLDS("Destroyer of Worlds", "nothing"),
    COMMANDER_OF_THE_RED_RIDERS("Commander of the Red Riders", "nothing"),
    THE_TREACHEROUS("The Treacherous", "nothing"),
    // Unit cards
    DRAUG("Draug", "nothing",true),
    IMLERITH("Imlerith", "nothing",true),
    LESHEN("Leshen", "nothing", true),
    KAYRAN("Kayran", "moralboost", true),
    TOAD("Toad", "ranged combat conditionally scroch", false),
    ARACHAS_BEHEMOTH("Arachas Behemoth","muster", false),
    CRONE_BREWESS("Brewess", "muster" ,false), // TODO: download its image from the internet!
    CRONE_WEAVESS("Weavess", "muster", false),
    CRONE_WHISPESS("Whispess", "muster", false),
    EARTH_ELEMENTAL("Earth Elemental", "nothing", false),
    FIEND("Fiend", "nothing", false),
    FIRE_ELEMENTAL("Fire Elemental","nothing", false),
    FORK_TAIL("Forktail", "nothing", false),
    FRIGHTENER("Frightener","nothing", false),
    GRAVE_HAG("Grave Hag", "nothing", false),
    GRIFFIN("Griffin", "nothing", false),
    ICE_GIANT("Ice Giant", "nothing", false),
    PLAGUE_MAIDEN("Plague Maiden", "nothing", false),
    VAMPIRE_KATAKAN("Katakan","muster", false),
    WEREWOLF("Werewolf","nothing", false),
    ARACHAS("Arachas", "nothing", false),
    BOTCHLING("Botchling", "nothing", false),
    VAMPIRE_BRUXA("Bruxa", "muster", false),
    VAMPIRE_EKIMMARA("Ekimmara", "muster", false),
    VAMPIRE_FLEDER("Fleder", "muster", false),
    VAMPIRE_GARKAIN("Garkain", "muster", false),
    CELAENO_HARPY("Celaeno Harpy", "nothing", false),
    COCKATRICE("Cockatrice", "nothing", false),
    ENDREGA("Endrega", "nothing", false),
    FOGLET("Foglet", "nothing", false),
    GARGOYLE("Gargoyle", "nothing", false),
    HARPY("Harpy", "nothing", false),
    NEKKER("Nekker", "muster", false),
    WYVERN("Wyvern", "nothing", false),
    GHOUL("Ghoul", "muster", false),

    // Nilfgaard
    // Leaders

    THE_WHITE_FLAME("the White Flame", "nothing"),
    HIS_IMPERIAL_MAJESTY("His Imperial Majesty", "nothing"),
    EMPEROR_OF_NILFGAARD("Emperor of Nilfgaard", "nothing"),
    THE_RELENTLESS("the Relentless", "nothing"),
    INVADER_OF_THE_NORTH("Invader of the North", "nothing"),
    // Unit cards
    IMPERA_BRIGADE_GUARD("Impera Brigade Guard", "tight bound", false),
    STEFAN_SKELLEN("Stefan Skellen", "spy", false),
    YOUNG_EMISSARY("Young Emissary", "tight bound", false),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach", "nothing", false),
    VATTIER_DE_RIDEAUX("Vattier de Rideaux", "nothing", false),
    MENNO_COEHOORN("Menno Coehoorn", "medic", true),
    PUTTKAMMER("Puttkammer", "nothing", false),
    BLACK_INFANTRY_ARCHER("Black Infantry Archer","nothing", false),
    TIBOR_EGGEBRACHT("Tibor Eggebracht", "nothing", true),
    RENUALD_AEP_MATSEN("Renuald aep Matsen", "nothing", false),
    FRINGILLA_VIGO("Fringilla Vigo", "nothing", false),
    ROTTEN_MANGONEL("Rotten Mangonel", "nothing", false),
    ZERRIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion", "nothing", false),
    SIEGE_ENGINEER("Siege Engineer", "nothing", false),
    MORVRAN_VOORHIS("Morvran Voorhis", "nothing", true),
    CYNTHIA("Cynthia", "nothing", false),
    ETOLIAN_AUXILIARY_ARCHERS("Etolian Auxiliary Archers", "medic", false),
    MORTEISEN("Morteisen", "nothing", false),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", "tight bound", false),
    SIEGE_TECHNICIAN("Siege Technician", "medic", false),
    SWEERS("Sweers", "nothing", false),
    VANHEMAR("Vanhemar", "nothing", false),
    VREEMDE("Vreemde", "nothing", false), // Source: Random reward from gwent players


    // Skellige Cards
    // Leaders
    CRACH_AN_CRAITE("Crach an Craite", "nothing"),
    KING_BRAN("King Bran", "nothing"),
    // Unit cards
    BERSERKER("Berserker", "berserker", false),
    VILDKAARL("Vildkaarl", "moral boost", false),
    SVANRIGE("Svanrige", "nothing", false),
    UDALRYK("Udalryk", "nothing", false),
    DONAR_AN_HINDAR("Donar an Hindar", "nothing", false),
    CLAN_AN_CRAITE("Clan an Craite", "tight bound", false),
    MADMAN_LUGOS("Madman Lugos", "nothing", false),
    CERYS("Cerys", "muster", true), // Its Muster effect will summon Shield Maiden cards and other musters.
    HEMDALL("Hemdall", "nothing", true),
    KAMBI("Kambi", "tansformer to HEMDALL", true), // Turns into a card with a power of 8 after one round
    BIRNA_BRAN("Birna Bran", "medic", false),
    CLAN_DRUMMOND_SHIELD_MAIDEN("Clan Drummond Shield Maiden","tight bound", false),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", "general scorch", false),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer", "nothing", false),
    ERMION("Ermion", "mardroeme", true),
    HJALMAR("Hjalmar", "nothing", true),
    YOUNG_BERSERKER("Young Berserker", "berserker", false),
    YOUNG_VILDKAARL("Young Vildkaarl", "tight bound", false),
    LIGHT_LONGSHIP("Light Longship","muster", false),
    WAR_LONGSHIP("War Longship", "tight bound", false),
    DRAIG_BON_DHU("Draig Bon-Dhu", "commander horn", false),
    OLAF("Olaf", "moral boost", false),

    // Scoia'tael
    // Leaders
    QUEEN_OF_DOL_BLATHANNA("Queen of Dol Blathanna", "nothing"),
    THE_BEAUTIFUL("the Beautiful", "nothing"),
    DAISY_OF_THE_VALLEY("Daisy of the Valley", "nothing"),
    PURE_BLOOD_ELF("Pureblood Elf","nothing"),
    HOPE_OF_THE_AEN_SEIDHE("Hope of the Aen Seidhe", "nothing"),
    // Unit cards
    ELVEN_SKIRMISHER("Elven Skirmisher", "muster", false),
    YAEVINN("Yaevinn", "nothing", false),
    CIARAN_AEP("Ciaran Aep", "nothing", false),
    DENNIS_CRANMER("Dennis Cranmer", "nothing", false),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", "nothing", false),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer", "nothing", false),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", "muster", false),
    HAVEKAR_HEALER("Havekar Healer", "medic", false),
    HAVEKAR_SMUGGLER("Havekar Smuggler", "muster", false),
    IDA_EMEAN_AEP("Ida Emean Aep", "nothing", false),
    RIORDAIN("Riordain", "nothing", false),
    TORUVIEL("Toruviel", "nothing", false),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit", "nothing", false),
    VRIHEDD_BRIGADE_VETERAN("Vrihedd Brigade Veteran", "nothing", false),
    MILVA("Milva", "moralboost", false),
    SAESENTHESSIS("Saesenthessis", "nothing", true),
    SCHIRRU("Schirrú", "siege combat scorch", false), // kills the opponent's card(s) with most power in enemy's Siege combat row if the sum of powers of none-hero cards in this row is 10 or more
    EITHNE("Eithne", "nothing", true),
    ISENGRIM_FAOILTIARNA("Isengrim Faoiltiarna","moralboost", true),

    // Northern Realms
    // Leaders
    THE_SIEGE_MASTER("the Siegemaster", "nothing"),
    THE_STEEL_FORGED("the Steel-Forged", "nothing"),
    KING_OF_TEMERIA("King of Temeria", "nothing"),
    LORD_COMMANDER_OF_THE_NORTH("Lord Commander of the North","nothing"),
    SON_OF_MEDELL("Son of Medell", "nothing"),
    // Unit cards
    BALLISTA("Ballista", "nothing", false),
    CATAPULT("Catapult", "tight bound", false),
    DRAGON_HUNTER("Dragon Hunter", "tight bound", false),
    DUN_BANNER_MEDIC("Dun banner Medic", "medic", false),
    ESTERAD_THYSSEN("Esterad Thyssen", "nothing", true),
    JOHN_NATALIS("John Natalis", "nothing", true),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", "moralboost", false),
    PHILIPPA_EILHART("Philippa Eilhart", "nothing", true),
    POOR_FUCKING_INFANTRY("Poor Fucking Infantry", "tight bound", false),
    PRINCE_STENNIS("Prince Stennis", "spy", false),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", "nothing", false),
    SABRINA_GLEVISSIG("Sabrina Glevissig", "nothing", false),
    SIEGE_TOWER("Siege Tower", "nothing", false),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", "nothing", false),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra","nothing", false),
    SHEALA_DE_TANCARVILLE("Síle de Tansarville","nothing", false),
    THALER("Thaler", "spy", false),
    VERNON_ROCHE("Vernon Roche", "nothing", true),
    VES("Ves", "nothing", false),
    YARPEN_ZIGRIN("Yarpen Zigrin", "nothing", false),

    // Neutral cards
    // Spells
    MARDROEME("Mardroeme", "mardroeme"),
    DECOY("Decoy", "decoy"),
    SCORCH("Scorch", "general scroch"),
    COMMANDER_HORN("Commander's Horn", "commander horn"),
    // Weather cards
    BITING_FROST("Biting Frost", "biting frost"),
    IMPENETRABLE_FOG("Impenetrable Fog", "impenetrable fog"),
    TORRENTIAL_RAIN("Torrential Rain", "torrential rain"),
    // TODO: add skellige storm and clear weather
    // Neutral unit cards
    DANDELION("Dandelion", "commander horn", false),
    EMIEL_REGIS("Emiel Regis", "nothing", false),
    GAUNTER_O_DIMM("Gaunter O'Dimm", "muster", false),
    GAUNTER_O_DIMM_DARKNESS("Gaunter O'Dimm: Darkness", "muster", false),
    MYSTERIOUS_ELF("Mysterious Elf", "spy", true),
    OLGIERD_VON_EVEREC("Olgierd von Everec", "moralboost", false),
    TRISS_MERIGOLD("Triss merigold", "nothing", true),
    VILLENTRETENMERTH("Villentretenmerth", "oponent close combat scroch", false),
    ;

    Abilities(String name, String AbilityName,boolean Ishero) {
    }

    Abilities(String name, String AbilityName) {
    }
}
