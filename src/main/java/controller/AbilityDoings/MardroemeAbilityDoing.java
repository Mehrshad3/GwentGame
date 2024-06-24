package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import model.GameStatus;
import model.Row;
import model.faction.UnitCard;

public class MardroemeAbilityDoing {
    public GameStatus game;

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DoAbilityOnACard(UnitCard card) {
        if (HeroChecking.HeroChecking(card)) {
        } else {
            Boolean isBerserker = false;
            //TODO:CHecking Card has Berseerker Ability? Boolean isBerserker=...
            if (isBerserker) {
                BerserkerAbilityDoing bearer = new BerserkerAbilityDoing();
                bearer.setGame(game);
                bearer.DOAbilityOnACard(card);
            } else {

            }
        }
    }
    public void DoAbilityOnARow(int row){
        Row[] rows=game.getTable().getRows();
        Row wantedrow=rows[row];
        for(UnitCard card0:wantedrow.getCards()){
            DoAbilityOnACard(card0);
        }
    }
}
