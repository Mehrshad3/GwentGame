package controller;

import model.*;
import model.faction.UnitCard;

public class CardRemoverFromGame {

    public static void Remove(ObservableGameStatus game, UnitCard card) {
        int row = -1;
        ObservableRow[] rows = game.getTable().getRows();
        for (int row0 = 0; row0 < 6; row0++) {
            if (rows[row0].getCards().contains(card)) {
                row = row0;
            }
        }
        RemoveHavingRow(game, card, row);
    }

    public static void RemoveHavingRow(ObservableGameStatus game, UnitCard card, int row) {
        Player player=card.getPlayer();
        ObservableRow[] rows=game.getTable().getRows();
        ObservableRow wantedrow=rows[row];
        wantedrow.getCards().remove(card);
        card.getRowmates().removeAll(card.getRowmates());
        for(UnitCard unitCard:wantedrow.getCards()){
            unitCard.getRowmates().remove(card);
        }
        player.getDeck().getDiscardCards().add(card);
    }
}
