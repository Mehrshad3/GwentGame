package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import model.*;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.List;

public class CommandersHornAbility extends Ability{
    public Card MainCard;

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public Card getMainCard() {
        return MainCard;
    }

    public ObservableGameStatus getGameStatus() {
        return gameStatus;
    }

    public void DoAbilityOnRow(int row){
        ObservableTable table = gameStatus.getTable();
        ObservableRow[] rows=table.getRows();
        ObservableRow wantedRow = rows[row];
        List<Card> cards = wantedRow.getCards();
        for (Card card0 : cards) {
            if (card0 instanceof UnitCard unitCard) DoAbilityOnCard(unitCard);
        }
    }

    public void DoAbilityOnCardArbitrary(UnitCard card) {
        if (HeroChecking.HeroChecking(card)) {

        } else if (card instanceof UnitCard) {
            int power = ((UnitCard) card).getPower();
            ((UnitCard) card).setPower(2 * power);
        }
    }

    public void DoAbilityOnCard(UnitCard card) {
        if (/*HeroChecking.HeroChecking(card)*/false) {
        } else {
            int power = card.getPower();
            card.setPower(2 * power);
        }
    }

    public void DoAbilityOnARow(int row) {
        ObservableRow[] rows = gameStatus.getTable().getRows();
        ObservableRow wantedrow = rows[row];
        for (Card card0 : wantedrow.getCards()) {
            if (card0 instanceof UnitCard unitCard) DoAbilityOnCard(unitCard);
        }
    }


    @Override
    public void DoCardAbility() {
        DoAbilityOnARow(maincard.getRowNumber());
        gameStatus.getHandleRounds().getNextDoingMethods().remove(this);
    }

    @Override
    public Ability Copy(Card card) {
        CommandersHornAbility commandersHornAbility=new CommandersHornAbility();
        commandersHornAbility.setmaincard(card);
        commandersHornAbility.setStatus(status);
        commandersHornAbility.setGameStatus(gameStatus);
        return commandersHornAbility;
    }
}
