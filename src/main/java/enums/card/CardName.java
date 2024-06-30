package enums.card;

import enums.card.ability.CardAbility;
import enums.card.ability.CommanderCardAbility;
import enums.card.ability.UnitOrSpellCardAbility;
import enums.card.ability.WeatherCardAbility;
import model.faction.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static enums.card.PossibleRowsToPlayCard.*;
import static enums.card.ability.CommanderCardAbility.*;
import static enums.card.ability.UnitOrSpellCardAbility.*;
import static model.faction.Faction.*;

/**
 * List of all card names, factions, initial powers, maximum instances per player, etc.
 * Names sorted by their factions, sorted by their order in {@link Faction} and finally listed neutral cards.
 */
public enum CardName {
    // Monsters
    // Leaders
    BRINGER_OF_DEATH("Bringer of Death", MONSTERS, BRINGER_OF_DEATH_ABILITY_PERFORMER),
    KING_OF_THE_WILD_HUNT("King of the Wild Hunt", MONSTERS, KING_OF_THE_WILD_HUNT_ABILITY_PERFORMER),
    DESTROYER_OF_WORLDS("Destroyer of Worlds", MONSTERS, DESTROYER_OF_WORLDS_ABILITY_PERFORMER),
    COMMANDER_OF_THE_RED_RIDERS("Commander of the Red Riders", MONSTERS, COMMANDER_OF_THE_RED_RIDERS_ABILITY_PERFORMER),
    THE_TREACHEROUS("The Treacherous", MONSTERS, THE_TREACHEROUS_ABILITY_PERFORMER),
    // Unit cards
    DRAUG("Draug", MONSTERS, 10, 1, CLOSE_COMBAT, null, true),
    IMLERITH("Imlerith", MONSTERS, 10, 1, CLOSE_COMBAT, null, true),
    LESHEN("Leshen", MONSTERS, 10, 1, CLOSE_COMBAT, null, true),
    KAYRAN("Kayran", MONSTERS, 8, 1, AGILE, MORALE_BOOST, true),
    TOAD("Toad", MONSTERS, 7, 1, RANGED, RANGED_SCORCH, false),
    ARACHAS_BEHEMOTH("Arachas Behemoth", MONSTERS, 6, 1, SIEGE, MUSTER, false),
    CRONE_BREWESS("Brewess", MONSTERS, 6, 1, CLOSE_COMBAT, MUSTER, false), // TODO: download its image from the internet!
    CRONE_WEAVESS("Weavess", MONSTERS, 6, 1, CLOSE_COMBAT, MUSTER, false),
    CRONE_WHISPESS("Whispess", MONSTERS, 6, 1, CLOSE_COMBAT, MUSTER, false),
    EARTH_ELEMENTAL("Earth Elemental", MONSTERS, 6, 1, SIEGE, null, false),
    FIEND("Fiend", MONSTERS, 6, 1, CLOSE_COMBAT, null, false),
    FIRE_ELEMENTAL("Fire Elemental", MONSTERS, 6, 1, SIEGE, null, false),
    FORK_TAIL("Forktail", MONSTERS, 5, 1, CLOSE_COMBAT, null, false),
    FRIGHTENER("Frightener", MONSTERS, 5, 1, CLOSE_COMBAT, null, false),
    GRAVE_HAG("Grave Hag", MONSTERS, 5, 1, RANGED, null, false),
    GRIFFIN("Griffin", MONSTERS, 5, 1, CLOSE_COMBAT, null, false),
    ICE_GIANT("Ice Giant", MONSTERS, 5, 1, SIEGE, null, false),
    PLAGUE_MAIDEN("Plague Maiden", MONSTERS, 5, 1, CLOSE_COMBAT, null, false),
    VAMPIRE_KATAKAN("Katakan", MONSTERS, 5, 1, CLOSE_COMBAT, MUSTER, false),
    WEREWOLF("Werewolf", MONSTERS, 5, 1, CLOSE_COMBAT, null, false),
    ARACHAS("Arachas", MONSTERS, 4, 3, CLOSE_COMBAT, MUSTER, false),
    BOTCHLING("Botchling", MONSTERS, 4, 1, CLOSE_COMBAT, null, false),
    VAMPIRE_BRUXA("Bruxa", MONSTERS, 4, 1, CLOSE_COMBAT, MUSTER, false),
    VAMPIRE_EKIMMARA("Ekimmara", MONSTERS, 4, 1, CLOSE_COMBAT, MUSTER, false),
    VAMPIRE_FLEDER("Fleder", MONSTERS, 4, 1, CLOSE_COMBAT, MUSTER, false),
    VAMPIRE_GARKAIN("Garkain", MONSTERS, 4, 1, CLOSE_COMBAT, MUSTER, false),
    CELAENO_HARPY("Celaeno Harpy", MONSTERS, 2, 1, AGILE, null, false),
    COCKATRICE("Cockatrice", MONSTERS, 2, 1, RANGED, null, false),
    ENDREGA("Endrega", MONSTERS, 2, 1, RANGED, null, false),
    FOGLET("Foglet", MONSTERS, 2, 1, CLOSE_COMBAT, null, false),
    GARGOYLE("Gargoyle", MONSTERS, 2, 1, RANGED, null, false),
    HARPY("Harpy", MONSTERS, 2, 1, AGILE, null, false),
    NEKKER("Nekker", MONSTERS, 2, 3, CLOSE_COMBAT, MUSTER, false),
    WYVERN("Wyvern", MONSTERS, 2, 1, RANGED, null, false),
    GHOUL("Ghoul", MONSTERS, 1, 3, RANGED, MUSTER, false),


    // Nilfgaard
    // Leaders
    THE_WHITE_FLAME("the White Flame", EMPIRE_NILFGAARDIAN, THE_WHITE_FLAME_ABILITY_PERFORMER),
    HIS_IMPERIAL_MAJESTY("His Imperial Majesty", EMPIRE_NILFGAARDIAN, HIS_IMPERIAL_MAJESTY_ABILITY_PERFORMER),
    EMPEROR_OF_NILFGAARD("Emperor of Nilfgaard", EMPIRE_NILFGAARDIAN, EMPEROR_OF_NILFGAARD_ABILITY_PERFORMER),
    THE_RELENTLESS("the Relentless", EMPIRE_NILFGAARDIAN, THE_RELENTLESS_ABILITY_PERFORMER),
    INVADER_OF_THE_NORTH("Invader of the North", EMPIRE_NILFGAARDIAN, INVADER_OF_THE_NORTH_ABILITY_PERFORMER),
    // Unit cards
    IMPERA_BRIGADE_GUARD("Impera Brigade Guard", EMPIRE_NILFGAARDIAN, 3, 4, CLOSE_COMBAT, TIGHT_BOND, false),
    STEFAN_SKELLEN("Stefan Skellen", EMPIRE_NILFGAARDIAN, 9, 1, CLOSE_COMBAT, SPY, false),
    YOUNG_EMISSARY("Young Emissary", EMPIRE_NILFGAARDIAN, 9, 1, CLOSE_COMBAT, TIGHT_BOND, false),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach", EMPIRE_NILFGAARDIAN, 6,
            1, CLOSE_COMBAT, null, false),
    VATTIER_DE_RIDEAUX("Vattier de Rideaux", EMPIRE_NILFGAARDIAN, 4, 1, CLOSE_COMBAT, null, false),
    MENNO_COEHOORN("Menno Coehoorn", EMPIRE_NILFGAARDIAN, 10, 1, CLOSE_COMBAT, MEDIC, true),
    PUTTKAMMER("Puttkammer", EMPIRE_NILFGAARDIAN, 3, 1, RANGED, null, false),
    BLACK_INFANTRY_ARCHER("Black Infantry Archer", EMPIRE_NILFGAARDIAN, 10, 2, RANGED, null, false),
    TIBOR_EGGEBRACHT("Tibor Eggebracht", EMPIRE_NILFGAARDIAN, 10, 1, RANGED, null, true),
    RENUALD_AEP_MATSEN("Renuald aep Matsen", EMPIRE_NILFGAARDIAN, 5, 1, RANGED, null, false),
    FRINGILLA_VIGO("Fringilla Vigo", EMPIRE_NILFGAARDIAN, 6, 1, RANGED, null, false),
    ROTTEN_MANGONEL("Rotten Mangonel", EMPIRE_NILFGAARDIAN, 3, 1, SIEGE, null, false),
    ZERRIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion", EMPIRE_NILFGAARDIAN, 5, 1, SIEGE, null, false),
    SIEGE_ENGINEER("Siege Engineer", EMPIRE_NILFGAARDIAN, 6, 1, SIEGE, null, false),
    MORVRAN_VOORHIS("Morvran Voorhis", EMPIRE_NILFGAARDIAN, 10, 1, SIEGE, null, true),
    CYNTHIA("Cynthia", EMPIRE_NILFGAARDIAN, 4, 1, RANGED, null, false),
    ETOLIAN_AUXILIARY_ARCHERS("Etolian Auxiliary Archers", EMPIRE_NILFGAARDIAN, 1, 2, RANGED, MEDIC, false),
    MORTEISEN("Morteisen", EMPIRE_NILFGAARDIAN, 3, 1, CLOSE_COMBAT, null, false),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", EMPIRE_NILFGAARDIAN, 2, 3, CLOSE_COMBAT, TIGHT_BOND, false),
    SIEGE_TECHNICIAN("Siege Technician", EMPIRE_NILFGAARDIAN, 0, 1, SIEGE, MEDIC, false),
    SWEERS("Sweers", EMPIRE_NILFGAARDIAN, 2, 1, RANGED, null, false),
    VANHEMAR("Vanhemar", EMPIRE_NILFGAARDIAN, 4, 1, RANGED, null, false),
    VREEMDE("Vreemde", EMPIRE_NILFGAARDIAN, 2, 0, CLOSE_COMBAT, null, false), // Source: Random reward from gwent players


    // Skellige Cards
    // Leaders
    CRACH_AN_CRAITE("Crach an Craite", SKELLIGE, CRACH_AN_CRAITE_ABILITY_PERFORMER),
    KING_BRAN("King Bran", SKELLIGE, KING_BRAN_ABILITY_PERFORMER),
    // Unit cards
    BERSERKER("Berserker", SKELLIGE, 4, 1,
            CLOSE_COMBAT, UnitOrSpellCardAbility.BERSERKER, false),
    VILDKAARL("Vildkaarl", SKELLIGE, 14, 0, CLOSE_COMBAT, MORALE_BOOST, false),
    SVANRIGE("Svanrige", SKELLIGE, 4, 1, CLOSE_COMBAT, null, false),
    UDALRYK("Udalryk", SKELLIGE, 4, 1, CLOSE_COMBAT, null, false),
    DONAR_AN_HINDAR("Donar an Hindar", SKELLIGE, 4, 1, CLOSE_COMBAT, null, false),
    CLAN_AN_CRAITE("Clan an Craite", SKELLIGE, 6, 3, CLOSE_COMBAT, TIGHT_BOND, false),
    MADMAN_LUGOS("Madman Lugos", SKELLIGE, 6, 1, CLOSE_COMBAT, null, false),
    CERYS("Cerys", SKELLIGE, 10, 1, CLOSE_COMBAT, MUSTER, true), // Its Muster effect will summon Shield Maiden cards and other musters.
    HEMDALL("Hemdall", SKELLIGE, 8, 0, CLOSE_COMBAT, null, true),
    KAMBI("Kambi", SKELLIGE, 11, 1, CLOSE_COMBAT, TRANSFORMERS, true, HEMDALL), // Turns into a card with a power of 8 after one round
    BIRNA_BRAN("Birna Bran", SKELLIGE, 2, 1, CLOSE_COMBAT, MEDIC, false),
    CLAN_DRUMMOND_SHIELD_MAIDEN("Clan Drummond Shield Maiden", SKELLIGE, 4, 3, CLOSE_COMBAT, TIGHT_BOND, false),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", SKELLIGE, 6, 1, RANGED, SIEGE_SCORCH, false),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer", SKELLIGE, 6, 3, RANGED, null, false),
    ERMION("Ermion", SKELLIGE, 8, 1, RANGED, UnitOrSpellCardAbility.MARDROEME, true),
    HJALMAR("Hjalmar", SKELLIGE, 10, 1, RANGED, null, true),
    YOUNG_BERSERKER("Young Berserker", SKELLIGE, 2, 3, RANGED, UnitOrSpellCardAbility.BERSERKER, false),
    YOUNG_VILDKAARL("Young Vildkaarl", SKELLIGE, 8, 0, RANGED, TIGHT_BOND, false),
    LIGHT_LONGSHIP("Light Longship", SKELLIGE, 4, 3, RANGED, MUSTER, false),
    WAR_LONGSHIP("War Longship", SKELLIGE, 6, 3, SIEGE, TIGHT_BOND, false),
    DRAIG_BON_DHU("Draig Bon-Dhu", SKELLIGE, 2, 1, SIEGE, UnitOrSpellCardAbility.COMMANDER_HORN, false),
    OLAF("Olaf", SKELLIGE, 12, 1, AGILE, MORALE_BOOST, false),

    // Scoia'tael
    // Leaders
    QUEEN_OF_DOL_BLATHANNA("Queen of Dol Blathanna", SCOIA_TAELL, QUEEN_OF_DOL_BLATHANNA_ABILITY_PERFORMER),
    THE_BEAUTIFUL("the Beautiful", SCOIA_TAELL, THE_BEAUTIFUL_ABILITY_PERFORMER),
    DAISY_OF_THE_VALLEY("Daisy of the Valley", SCOIA_TAELL, DAISY_OF_THE_VALLEY_ABILITY_PERFORMER),
    PURE_BLOOD_ELF("Pureblood Elf", SCOIA_TAELL, PURE_BLOOD_ELF_ABILITY_PERFORMER),
    HOPE_OF_THE_AEN_SEIDHE("Hope of the Aen Seidhe", SCOIA_TAELL, HOPE_OF_THE_AEN_SEIDHE_ABILITY_PERFORMER),
    // Unit cards
    ELVEN_SKIRMISHER("Elven Skirmisher", SCOIA_TAELL, 2, 3, RANGED, MUSTER, false),
    YAEVINN("Yaevinn", SCOIA_TAELL, 6, 1, AGILE, null, false),
    CIARAN_AEP("Ciaran Aep", SCOIA_TAELL, 3, 1, AGILE, null, false),
    DENNIS_CRANMER("Dennis Cranmer", SCOIA_TAELL, 6, 1, CLOSE_COMBAT, null, false),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", SCOIA_TAELL, 6, 3, AGILE, null, false),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer", SCOIA_TAELL, 4, 1, RANGED, null, false),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", SCOIA_TAELL, 3, 3, CLOSE_COMBAT, MUSTER, false),
    HAVEKAR_HEALER("Havekar Healer", SCOIA_TAELL, 0, 3, RANGED, MEDIC, false),
    HAVEKAR_SMUGGLER("Havekar Smuggler", SCOIA_TAELL, 5, 3, CLOSE_COMBAT, MUSTER, false),
    IDA_EMEAN_AEP("Ida Emean Aep", SCOIA_TAELL, 6, 1, RANGED, null, false),
    RIORDAIN("Riordain", SCOIA_TAELL, 1, 1, RANGED, null, false),
    TORUVIEL("Toruviel", SCOIA_TAELL, 2, 1, RANGED, null, false),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit", SCOIA_TAELL, 4, 1, RANGED, null, false),
    VRIHEDD_BRIGADE_VETERAN("Vrihedd Brigade Veteran", SCOIA_TAELL, 5, 2, AGILE, null, false),
    MILVA("Milva", SCOIA_TAELL, 10, 1, RANGED, MORALE_BOOST, false),
    SAESENTHESSIS("Saesenthessis", SCOIA_TAELL, 10, 1, RANGED, null, true),
    SCHIRRU("Schirrú", SCOIA_TAELL, 8, 1, SIEGE, GLOBAL_SCORCH, false), // kills the opponent's card(s) with most power in enemy's Siege combat row if the sum of powers of none-hero cards in this row is 10 or more
    EITHNE("Eithne", SCOIA_TAELL, 10, 1, RANGED, null, true),
    ISENGRIM_FAOILTIARNA("Isengrim Faoiltiarna", SCOIA_TAELL, 10, 1, CLOSE_COMBAT, MORALE_BOOST, true),

    // Northern Realms
    // Leaders
    THE_SIEGE_MASTER("the Siegemaster", NORTHERN_REALMS, THE_SIEGE_MASTER_ABILITY_PERFORMER),
    THE_STEEL_FORGED("the Steel-Forged", NORTHERN_REALMS, THE_STEEL_FORGED_ABILITY_PERFORMER),
    KING_OF_TEMERIA("King of Temeria", NORTHERN_REALMS, KING_OF_TEMERIA_ABILITY_PERFORMER),
    LORD_COMMANDER_OF_THE_NORTH("Lord Commander of the North", NORTHERN_REALMS, LORD_COMMANDER_OF_THE_NORTH_ABILITY_PERFORMER),
    SON_OF_MEDELL("Son of Medell", NORTHERN_REALMS, SON_OF_MEDELL_ABILITY_PERFORMER),
    // Unit cards
    BALLISTA("Ballista", NORTHERN_REALMS, 6, 2, SIEGE, null, false),
    CATAPULT("Catapult", NORTHERN_REALMS, 8, 2, SIEGE, TIGHT_BOND, false),
    DRAGON_HUNTER("Dragon Hunter", NORTHERN_REALMS, 5, 3, RANGED, TIGHT_BOND, false),
    DUN_BANNER_MEDIC("Dun banner Medic", NORTHERN_REALMS, 5, 1, SIEGE, MEDIC, false),
    ESTERAD_THYSSEN("Esterad Thyssen", NORTHERN_REALMS, 10, 1, CLOSE_COMBAT, null, true),
    JOHN_NATALIS("John Natalis", NORTHERN_REALMS, 10, 1, CLOSE_COMBAT, null, true),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", NORTHERN_REALMS, 1, 3, SIEGE, MORALE_BOOST, false),
    PHILIPPA_EILHART("Philippa Eilhart", NORTHERN_REALMS, 10, 1, RANGED, null, true),
    POOR_FUCKING_INFANTRY("Poor Fucking Infantry", NORTHERN_REALMS, 1, 4, CLOSE_COMBAT, TIGHT_BOND, false),
    PRINCE_STENNIS("Prince Stennis", NORTHERN_REALMS, 5, 1, CLOSE_COMBAT, SPY, false),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", NORTHERN_REALMS, 1, 2, CLOSE_COMBAT, null, false),
    SABRINA_GLEVISSIG("Sabrina Glevissig", NORTHERN_REALMS, 4, 1, RANGED, null, false),
    SIEGE_TOWER("Siege Tower", NORTHERN_REALMS, 6, 1, SIEGE, null, false),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", NORTHERN_REALMS, 5, 1, CLOSE_COMBAT, null, false),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra", NORTHERN_REALMS, 5, 1, CLOSE_COMBAT, null, false),
    SHEALA_DE_TANCARVILLE("Síle de Tansarville", NORTHERN_REALMS, 5, 1, RANGED, null, false),
    THALER("Thaler", NORTHERN_REALMS, 1, 1, SIEGE, SPY, false),
    VERNON_ROCHE("Vernon Roche", NORTHERN_REALMS, 10, 1, CLOSE_COMBAT, null, true),
    VES("Ves", NORTHERN_REALMS, 5, 1, CLOSE_COMBAT, null, false),
    YARPEN_ZIGRIN("Yarpen Zigrin", NORTHERN_REALMS, 2, 1, CLOSE_COMBAT, null, false),

    // Neutral cards
    // Spells
    MARDROEME("Mardroeme", UnitOrSpellCardAbility.MARDROEME),
    DECOY("Decoy", UnitOrSpellCardAbility.DECOY),
    SCORCH("Scorch", UnitOrSpellCardAbility.GLOBAL_SCORCH),
    COMMANDER_HORN("Commander's Horn", UnitOrSpellCardAbility.COMMANDER_HORN),
    // Weather cards
    BITING_FROST("Biting Frost", WeatherCardAbility.BITING_FROST),
    IMPENETRABLE_FOG("Impenetrable Fog", WeatherCardAbility.IMPENETRABLE_FOG),
    TORRENTIAL_RAIN("Torrential Rain", WeatherCardAbility.TORRENTIAL_RAIN),
    // TODO: add skellige storm and clear weather
    // Neutral unit cards
    DANDELION("Dandelion", NEUTRAL, 2, 1, CLOSE_COMBAT, UnitOrSpellCardAbility.COMMANDER_HORN, false),
    EMIEL_REGIS("Emiel Regis", NEUTRAL, 5, 1, CLOSE_COMBAT, null, false),
    GAUNTER_O_DIMM("Gaunter O'Dimm", NEUTRAL, 2, 1, SIEGE, MUSTER, false),
    GAUNTER_O_DIMM_DARKNESS("Gaunter O'Dimm: Darkness", NEUTRAL, 4, 3, RANGED, MUSTER, false),
    MYSTERIOUS_ELF("Mysterious Elf", NEUTRAL, 0, 1, CLOSE_COMBAT, SPY, true),
    OLGIERD_VON_EVEREC("Olgierd von Everec", NEUTRAL, 6, 1, AGILE, MORALE_BOOST, false),
    TRISS_MERIGOLD("Triss merigold", NEUTRAL, 7, 1, CLOSE_COMBAT, null, true),
    VILLENTRETENMERTH("Villentretenmerth", NEUTRAL, 7, 1, CLOSE_COMBAT, OPPONENT_COMBAT_SCORCH, false),
    ;
    public final static int MAXIMUM_NUMBER_OF_EACH_SPECIAL_CARD = 3;
    /**
     * Maps the name of the card to its corresponding enum in {@code CardName}. Names of cards are stored here lowercase
     * , and without special characters. Prefixes like "Vampire: " are omitted.
     */
    private static final Map<String, CardName> map = new LowerCaseHashMap<>(CardName.values().length);
    private static final CardName[] factionFirstLeaders =
            new CardName[]{CRACH_AN_CRAITE, QUEEN_OF_DOL_BLATHANNA, THE_SIEGE_MASTER, THE_WHITE_FLAME, BRINGER_OF_DEATH};
    private static final CardName[] factionFirstNonLeaders =
            new CardName[]{BERSERKER, ELVEN_SKIRMISHER, BALLISTA, THE_WHITE_FLAME, DRAUG};

    static {
        for (CardName object : CardName.values()) {
            map.put(object.name.toLowerCase(), object);
        }
    }

    public final Faction faction;
    public final boolean isLeader;
    final String name;
    final Integer initialPower;
    final int maximumNumberOfCardsInGame;
    final CardAbility ability;
    private final Supplier<Card> getNewCard;

    /**
     * Constructor for non-transformer unit cards and hero cards
     */
    CardName(String name, Faction faction, int initialPower, int maximumNumberOfCardsInGame, PossibleRowsToPlayCard rows,
             UnitOrSpellCardAbility ability, boolean isHero) {
        this.name = name;
        this.faction = faction;
        this.isLeader = false;
        this.initialPower = initialPower;
        this.maximumNumberOfCardsInGame = maximumNumberOfCardsInGame;
        this.ability = ability;
        assert ability != TRANSFORMERS;
        getNewCard = () -> new UnitCard(this, name, rows, ability, initialPower, isHero);
    }

    /**
     * Constructor for {@link TransformerCard}s
     */
    CardName(String name, Faction faction, int initialPower, int maximumNumberOfCardsInGame, PossibleRowsToPlayCard rows
            , UnitOrSpellCardAbility ability, boolean isHero, CardName transformsTo) {
        assert ability == TRANSFORMERS;
        this.name = name;
        this.faction = faction;
        this.isLeader = false;
        this.initialPower = initialPower;
        this.maximumNumberOfCardsInGame = maximumNumberOfCardsInGame;
        this.ability = null;
        this.getNewCard = () -> new TransformerCard(this, name, rows, initialPower, isHero, transformsTo);
    }

    /**
     * Constructor for Leader cards
     */
    CardName(String name, Faction faction, CommanderCardAbility ability) {
        this.name = name;
        this.faction = faction;
        this.isLeader = true;
        this.initialPower = null;
        this.maximumNumberOfCardsInGame = 1;
        this.ability = ability;
        getNewCard = () -> new LeaderCard(this, name, ability);
    }

    /**
     * Constructor for Spell cards
     */
    CardName(String name, UnitOrSpellCardAbility ability) {
        this.name = name;
        this.faction = Faction.NEUTRAL;
        this.isLeader = false;
        this.initialPower = null;
        this.maximumNumberOfCardsInGame = MAXIMUM_NUMBER_OF_EACH_SPECIAL_CARD;
        this.ability = ability;
        this.getNewCard = () -> new SpellCard(this, name, ability);
    }

    /**
     * Constructor for Weather cards
     */
    CardName(String name, WeatherCardAbility ability) {
        this.name = name;
        this.faction = Faction.NEUTRAL;
        this.isLeader = false;
        this.initialPower = null;
        this.maximumNumberOfCardsInGame = MAXIMUM_NUMBER_OF_EACH_SPECIAL_CARD;
        this.ability = ability;
        this.getNewCard = () -> new WeatherCard(this, name, ability);
    }

    public static Card getCardByName(String name) {
        CardName cardName = map.get(name.toLowerCase());
        if (cardName == null) return null;
        return cardName.getNewCard.get();
    }

    public static CardName[] getLeadersOfFaction(Faction faction) {
        int factionOrdinal = faction.ordinal();
        CardName[] leadersOfFaction = new CardName
                [factionFirstNonLeaders[factionOrdinal].ordinal() - factionFirstLeaders[factionOrdinal].ordinal()];
        System.arraycopy(CardName.values(), factionFirstLeaders[factionOrdinal].ordinal(), leadersOfFaction, 0,
                leadersOfFaction.length);
        return leadersOfFaction;
    }

    public static CardName getCardNameEnumByName(String name) {
        return map.get(name.toLowerCase());
    }

    public static int getMaximumNumberOfCardsByName(String name) {
        CardName cardName = map.get(name.toLowerCase());
        if (cardName == null) return 0;
        return cardName.maximumNumberOfCardsInGame;
    }

    public Card getNewCard() {
        return getNewCard.get();
    }

    private static class LowerCaseHashMap<V> extends HashMap<String, V> {
        public LowerCaseHashMap(int initialCapacity) {
            super(initialCapacity);
        }

        @Override
        public V put(String key, V value) {
            return super.put(key.toLowerCase(), value);
        }

        @Override
        public V get(Object key) {
            assert key instanceof String;
            return super.get(key);
        }
    }
}