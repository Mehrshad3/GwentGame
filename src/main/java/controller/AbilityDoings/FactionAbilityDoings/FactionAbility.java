package controller.AbilityDoings.FactionAbilityDoings;

import model.GameStatus;
import model.Player;

public abstract class FactionAbility {
    public GameStatus gameStatus;
    public Player player;

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    public abstract void DoCardAbility();
    public abstract FactionAbility Copy();
}
