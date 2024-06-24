package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import model.GameStatus;
import model.Row;
import model.faction.UnitCard;

public class TightBoundAbilityDoing {
    public GameStatus game;

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public GameStatus getGame() {
        return game;
    }

    public void DoAbilityInARow(int row, UnitCard card) {
        //TODO: Hero? what to do what not to do :)
            int countofthiscards = 0;
            Row[] rows = game.getTable().getRows();
            Row wantedrow = rows[row];
            //TODO:What is this?

    }
}
