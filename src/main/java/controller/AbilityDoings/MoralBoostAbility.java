package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class MoralBoostAbility extends Ability{
    public GameStatus game;
    public Card MainCard;

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public Card getMainCard() {
        return MainCard;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public GameStatus getGame() {
        return game;
    }

    public void DoAbilityOnACard(UnitCard card) {
        if (HeroChecking.HeroChecking(card)) {

        } else {
            card.setPower(card.getPower() + 1);
        }
    }

    public void DoAbilityOnARow(int row){
        Row[] rows=game.getTable().getRows();
        Row wantedrow=rows[row];
        for(UnitCard card:wantedrow.getCards()){
            DoAbilityOnACard(card);
        }
    }

    @Override
    public void DoCardAbility() {

    }
}
