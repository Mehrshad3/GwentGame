package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import model.GameStatus;
import model.Row;
import model.Table;
import model.faction.Card;
import model.faction.UnitCard;

import java.util.List;

public class CommandersHornAbility extends Ability{
    public GameStatus gameStatus;
    public Card MainCard;

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public Card getMainCard() {
        return MainCard;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void DoAbilityOnRow(int row){
        Table table=gameStatus.getTable();
        Row[] rows=table.getRows();
        Row wantedrow=rows[row];
        List<UnitCard> cards = wantedrow.getCards();
        for (UnitCard card0 : cards) {
            DoAbilityOnCard(card0);
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
        if (HeroChecking.HeroChecking(card)) {
        } else {
            int power = card.getPower();
            card.setPower(2 * power);
        }
    }

    public void DoAbilityOnARow(int row){
        Row[] rows=gameStatus.getTable().getRows();
        Row wantedrow=rows[row];
        for(UnitCard card0: wantedrow.getCards()){
            DoAbilityOnCard(card0);
        }
    }


    @Override
    public void DoCardAbility() {

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
