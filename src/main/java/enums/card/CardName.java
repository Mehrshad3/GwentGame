package enums.card;

import enums.card.ability.CardAbility;
import enums.card.ability.CommanderCardAbility;
import enums.card.ability.UnitOrSpellCardAbility;
import enums.card.ability.WeatherCardAbility;
import model.faction.*;

import java.util.function.Supplier;

import static enums.card.PossibleRowsToPlayCard.CLOSE_COMBAT;
import static enums.card.ability.CommanderCardAbility.*;
import static enums.card.ability.UnitOrSpellCardAbility.*;
import static model.faction.Faction.*;

public enum CardName {
    // Skellige Cards
    // Leaders
    CRACH_AN_CRAITE("Crach an Craite", SKELLIGE, CRACH_AN_CRAITE_ABILITY_PERFORMER),
    KING_BRAN("King Bran", SKELLIGE, KING_BRAN_ABILITY_PERFORMER),
    // Unit cards
    BERSERKER("Berserker", SKELLIGE, 4, 1,
            CLOSE_COMBAT, UnitOrSpellCardAbility.BERSERKER, false),
    VILDKAARL("Vildkaarl", SKELLIGE, 14, 0, CLOSE_COMBAT, MORAL_BOOST, false),
    SVANRIGE("Svanrige", SKELLIGE, 4, 1, CLOSE_COMBAT, null, false),

    // Scoia'tael
    // Leaders
    QUEEN_OF_DOL_BLATHANNA("Queen of Dol Blathanna", SCOIA_TAELL, QUEEN_OF_DOL_BLATHANNA_ABILITY_PERFORMER),
    THE_BEAUTIFUL("The Beautiful", SCOIA_TAELL, THE_BEAUTIFUL_ABILITY_PERFORMER),
    DAISY_OF_THE_VALLEY("Daisy of the Valley", SCOIA_TAELL, DAISY_OF_THE_VALLEY_ABILITY_PERFORMER),
    PURE_BLOOD_ELF("Pure Blood Elf", SCOIA_TAELL, PURE_BLOOD_ELF_ABILITY_PERFORMER),
    HOPE_OF_THE_AEN_SEIDHE("Hope of the Aen Seidhe", SCOIA_TAELL, HOPE_OF_THE_AEN_SEIDHE_ABILITY_PERFORMER),
    // Unit cards

    // Northern Realms
    // Leaders
    THE_SIEGEMASTER("The Siegemaster", REALMS_NORTHERN, THE_SIEGE_MASTER_ABILITY_PERFORMER),
    THE_STEEL_FORGED("The Steel Forged", REALMS_NORTHERN, THE_STEEL_FORGED_ABILITY_PERFORMER),
    KING_OF_TEMERIA("King of Temeria", REALMS_NORTHERN, KING_OF_TEMERIA_ABILITY_PERFORMER),
    LORD_COMMANDER_OF_THE_NORTH("Lord Commander of the North", REALMS_NORTHERN, LORD_COMMANDER_OF_THE_NORTH_ABILITY_PERFORMER),
    SON_OF_MEDELL("Son of Medell", REALMS_NORTHERN, SON_OF_MEDELL_ABILITY_PERFORMER),
    // Unit cards

    // Nilfgaard
    // Leaders
    THE_WHITE_FLAME("The White Flame", EMPIRE_NILFGAARDIAN, THE_WHITE_FLAME_ABILITY_PERFORMER),
    HIS_IMPERIAL_MAJESTY("His Imperial Majesty", EMPIRE_NILFGAARDIAN, HIS_IMPERIAL_MAJESTY_ABILITY_PERFORMER),
    EMPEROR_OF_NILFGAARD("Emperor of Nilfgaard", EMPIRE_NILFGAARDIAN, EMPEROR_OF_NILFGAARD_ABILITY_PERFORMER),
    THE_RELENTLESS("The Relentless", EMPIRE_NILFGAARDIAN, THE_RELENTLESS_ABILITY_PERFORMER),
    INVADER_OF_THE_NORTH("Invader of the North", EMPIRE_NILFGAARDIAN, INVADER_OF_THE_NORTH_ABILITY_PERFORMER),
    // Unit cards

    // Monsters
    // Leaders
    BRINGER_OF_DEATH("Bringer of Death", MONSTERS, BRINGER_OF_DEATH_ABILITY_PERFORMER),
    KING_OF_THE_WILD_HUNT("King of the Wild Hunt", MONSTERS, KING_OF_THE_WILD_HUNT_ABILITY_PERFORMER),
    DESTROYER_OF_WORLDS("Destroyer of Worlds", MONSTERS, DESTROYER_OF_WORLDS_ABILITY_PERFORMER),
    COMMANDER_OF_THE_RED_RIDERS("Commander of the Red Riders", MONSTERS, COMMANDER_OF_THE_RED_RIDERS_ABILITY_PERFORMER),
    THE_TREACHEROUS("The Treacherous", MONSTERS, THE_TREACHEROUS_ABILITY_PERFORMER),
    // Unit cards

    // Neutral cards
    // Spells
    MARDROEME("مردمی", UnitOrSpellCardAbility.MARDROEME),
    DECOY("Decoy", UnitOrSpellCardAbility.DECOY),
    // Weather cards
    BITING_FROST("Biting Frost", WeatherCardAbility.BITING_FROST),
    IMPENETRABLE_FOG("Impenetrable Fog", WeatherCardAbility.IMPENETRABLE_FOG),
    TORRENTIAL_RAIN("Torrential Rain", WeatherCardAbility.TORRENTIAL_RAIN),
    // Neutral unit cards
    DANDELION("Dandelion", NEUTRAL, 2, 1, CLOSE_COMBAT, COMMANDER_HORN, false),
    MYSTERIOUS_ELF("Mysterious Elf", NEUTRAL, 0, 1, CLOSE_COMBAT, SPY, true),
    ;
    public final static int MAXIMUM_NUMBER_OF_EACH_SPECIAL_CARD = 3;
    public final Faction faction;
    final Supplier<Card> getNewCard;
    final String name;
    final Integer initialPower;
    final int maximumNumberOfCardsInGame;
    final CardAbility ability;

    /**
     * Constructor for unit cards and hero cards
     */
    CardName(String name, Faction faction, Integer initialPower, int maximumNumberOfCardsInGame, PossibleRowsToPlayCard rows,
             UnitOrSpellCardAbility ability, boolean isHero) {
        this.name = name;
        this.faction = faction;
        this.initialPower = initialPower;
        this.maximumNumberOfCardsInGame = maximumNumberOfCardsInGame;
        this.ability = ability;
        if (isHero) getNewCard = () -> new HeroCard(this, name, rows, ability, initialPower);
        else getNewCard = () -> new UnitCard(this, name, rows, ability, initialPower);
    }

    /**
     * Constructor for Leader cards
     */
    CardName(String name, Faction faction, CommanderCardAbility ability) {
        this.name = name;
        this.faction = faction;
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
        this.initialPower = null;
        this.maximumNumberOfCardsInGame = MAXIMUM_NUMBER_OF_EACH_SPECIAL_CARD;
        this.ability = ability;
        this.getNewCard = () -> new WeatherCard(this, name, ability);
    }
}