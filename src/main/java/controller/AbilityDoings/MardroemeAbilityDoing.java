package controller.AbilityDoings;

import controller.Checking.BerserkerChecking;
import controller.Checking.HeroChecking;
import model.ObservableGameStatus;
import model.ObservableRow;
import model.faction.Card;
import model.faction.UnitCard;

public class MardroemeAbilityDoing extends Ability{
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

    public void DoAbilityOnACard(UnitCard card) {
        if (HeroChecking.HeroChecking(card)) {
        } else {
            Boolean isBerserker = BerserkerChecking.berserkerchecking(card);
            if (isBerserker) {
                BerserkerAbilityDoing bearer = new BerserkerAbilityDoing();
                bearer.setGame(game);
                bearer.DOAbilityOnACard(card);
            } else {

            }
        }
    }
    public void DoAbilityOnARow(int row){
        ObservableRow[] rows=game.getTable().getRows();
        ObservableRow wantedrow=rows[row];
        for(UnitCard card0:wantedrow.getCards()){
            DoAbilityOnACard(card0);
        }
    }


    @Override
    public void DoCardAbility() {
        DoAbilityOnARow(maincard.getRowNumber());
        game.getHandleRounds().getNextDoingMethods().remove(this);
    }

    @Override
    public Ability Copy(Card card) {
        MardroemeAbilityDoing mardroemeAbilityDoing=new MardroemeAbilityDoing();
        mardroemeAbilityDoing.setmaincard(card);
        mardroemeAbilityDoing.setGame(game);
        mardroemeAbilityDoing.setStatus(status);
        return mardroemeAbilityDoing;
    }
}
