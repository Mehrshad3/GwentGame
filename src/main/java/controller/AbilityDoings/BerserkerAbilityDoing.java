package controller.AbilityDoings;

import controller.Checking.HeroChecking;
import enums.card.CardName;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import model.GameStatus;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class BerserkerAbilityDoing {
    public GameStatus game;
    public Card MainCard;

    public void setMainCard(Card mainCard) {
        MainCard = mainCard;
    }

    public Card getMainCard() {
        return MainCard;
    }

    public GameStatus getGame() {
        return game;
    }

    public void setGame(GameStatus game) {
        this.game = game;
    }

    public void DOAbilityOnACard(UnitCard card) {
        if (!HeroChecking.HeroChecking(card)) {
//TODO:get a bear card    UnitCard Bearcard= CardName.getCardByName(Bear);
//TODO:Maybe you need initialize Bearcard (remember prototype designe pattern)
            int row = card.getRowNumber();
            Row[] rows = game.getTable().getRows();
            Row wantedrow = rows[row];
            ObservableList<UnitCard> cards = wantedrow.getCards();
            cards.remove(card);
//TODO:Import Bearcard in row        cards.add(Bearcard);
        }
        else{}
    }
}
