package controller;

import model.GameStatus;
import model.Player;
import model.Row;
import model.faction.Card;
import model.faction.UnitCard;

public class CardRemoverFromGame {

    public static void Remove(GameStatus game, UnitCard card) {
        int row = -1;
        Row[] rows = game.getTable().getRows();
        for (int row0 = 0; row0 < 6; row0++) {
            if (rows[row0].getCards().contains(card)) {
                row = row0;
            }
        }
        RemoveHavingRow(game, card, row);
    }

    public static void RemoveHavingRow(GameStatus game, UnitCard card, int row) {
        Player player=card.getPlayer();
        Row[] rows=game.getTable().getRows();
        Row wantedrow=rows[row];
        wantedrow.getCards().remove(card);
        card.getRowmates().removeAll(card.getRowmates());
        for(UnitCard unitCard:wantedrow.getCards()){
            unitCard.getRowmates().remove(card);
        }
        player.getDeck().getDiscardCards().add(card);
    }
}
