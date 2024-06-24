package controller.AbilityDoings;

import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class MoralBoostAbility {
    public GameStatus game;

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public GameStatus getGame() {
        return game;
    }

    public void DoAbilityOnACard(UnitCard card){
        card.setPower(card.getPower()+1);
    }

    public void DoAbilityOnARow(int row){
        Row[] rows=game.getTable().getRows();
        Row wantedrow=rows[row];
        for(UnitCard card:wantedrow.getCards()){
            DoAbilityOnACard(card);
        }
    }
}
