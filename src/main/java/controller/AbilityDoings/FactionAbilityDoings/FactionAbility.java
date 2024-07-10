package controller.AbilityDoings.FactionAbilityDoings;

import model.ObservableGameStatus;
import model.Player;

public abstract class FactionAbility {
    public ObservableGameStatus gameStatus;
    public Player player;

    public void setGameStatus(ObservableGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ObservableGameStatus getGameStatus() {
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
