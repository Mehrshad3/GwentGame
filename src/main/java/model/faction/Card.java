package model.faction;

import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.CardAbility;
import model.GameStatus;
import model.Player;

import java.io.Serializable;

public abstract class Card implements Serializable {
    protected final CardAbility ability;
    protected final PossibleRowsToPlayCard rows;
    protected final Integer initialPower;
    protected CardName cardName;
    protected String name;
    public Player player;
    public GameStatus gameStatus;
    /**
     * Number of the row in which the card is. It's {@link null} if the card isn't played yet.
     */
    protected Integer rowNumber = null;
    protected boolean transformed = false;

    public Card(CardName cardName, String name, PossibleRowsToPlayCard rows, CardAbility ability, Integer initialPower) {
        this.cardName = cardName;
        this.name = name;
        this.rows = rows;
        this.ability = ability;
        this.initialPower = initialPower;
    }

    public void doAbility(GameStatus gaming) {
        if (ability != null) this.ability.doAction(gaming, this);
    }

    public String getName() {
        return name;
    }

    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    public Integer getInitialPower() {
        return initialPower;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public Integer getRowNumber() {
        return this.rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public PossibleRowsToPlayCard getPossibleRowsToBePlayed() {
        return rows;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
