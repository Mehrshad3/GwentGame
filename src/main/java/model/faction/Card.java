package model.faction;

import controller.GameController;
import enums.card.CardName;
import enums.card.PossibleRowsToPlayCard;
import enums.card.ability.CardAbility;
import model.GameStatus;
import model.ObservableGameStatus;
import model.Player;

import java.io.Serializable;
import java.util.Objects;

public abstract class Card implements Serializable {
    protected final transient CardAbility ability;
    protected final PossibleRowsToPlayCard rows;
    protected final Integer initialPower;
    public transient Player player;
    @Deprecated
    public transient GameStatus gameStatus;
    protected CardName cardName;
    protected String name;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Card card = (Card) object;
        return transformed == card.transformed
                && Objects.equals(ability, card.ability)
                && rows == card.rows
                && Objects.equals(initialPower, card.initialPower)
                && cardName == card.cardName
                && Objects.equals(name, card.name)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), ability, rows, initialPower, cardName, name, transformed);
    }

    public void doAbility(GameController gaming) {
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Deprecated
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Deprecated
    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus.getGameStatus();
    }
}
