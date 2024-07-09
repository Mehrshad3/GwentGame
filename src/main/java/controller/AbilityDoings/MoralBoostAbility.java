package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class MoralBoostAbility extends Ability{
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

    public void DoAbilityOnACard(UnitCard card) {
        if (HeroChecking.HeroChecking(card)) {

        } else {
            card.setPower(card.getPower() + 1);
        }
    }

    public void DoAbilityOnARow(int row){
        ObservableRow[] rows=game.getTable().getRows();
        ObservableRow wantedrow=rows[row];
        for (Card card : wantedrow.getCards()) {
            if (!(card instanceof UnitCard unitCard)) continue;
            DoAbilityOnACard(unitCard);
        }
    }

    @Override
    public void DoCardAbility() {

    }

    @Override
    public Ability Copy(Card card) {
        MoralBoostAbility moralBoostAbility=new MoralBoostAbility();
        moralBoostAbility.setmaincard(card);
        moralBoostAbility.setStatus(status);
        moralBoostAbility.setGame(game);
        return moralBoostAbility;
    }
}
