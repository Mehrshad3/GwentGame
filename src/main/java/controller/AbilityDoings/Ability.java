package controller.AbilityDoings;

import model.GameStatus;
import model.ObservableGameStatus;
import model.Player;
import model.faction.Card;

public abstract class Ability {
    public ObservableGameStatus gameStatus;
    public String status = "general";
    public Card maincard;
    public Player player;

    public ObservableGameStatus getGameStatus() {
        return this.gameStatus;
    }

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = new ObservableGameStatus(gameStatus.getPlayer1(),gameStatus.getPlayer2(),gameStatus);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Card getmaincard() {
        return maincard;
    }

    public void setmaincard(Card maincard) {
        this.maincard = maincard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void DoCardAbility();

    public abstract Ability Copy(Card card);
}
