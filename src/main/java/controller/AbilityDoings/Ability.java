package controller.AbilityDoings;

import model.GameStatus;
import model.Player;
import model.faction.Card;

public abstract class Ability {
    public String status="general";
    public Card maincard;
    public Player player;
    public GameStatus gameStatus;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
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

    public abstract void DoCardAbility() ;
    public abstract Ability Copy(Card card);
}
