package controller.AbilityDoings;

import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class TightBoundAbilityDoing extends Ability{
    public ObservableGameStatus game;
    public Card MainCard;

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public Card getMainCard() {
        return MainCard;
    }

    public void setGame(ObservableGameStatus game) {
        this.game = game;
    }

    public ObservableGameStatus getGame() {
        return game;
    }

    public void DoAbilityInARow(int row, UnitCard card) {

    }

    @Override
    public void DoCardAbility() {

    }

    @Override
    public Ability Copy(Card card) {
        TightBoundAbilityDoing tightBoundAbilityDoing=new TightBoundAbilityDoing();
        tightBoundAbilityDoing.setmaincard(card);
        tightBoundAbilityDoing.setGame(game);
        tightBoundAbilityDoing.setStatus(status);
        return tightBoundAbilityDoing;
    }
}
