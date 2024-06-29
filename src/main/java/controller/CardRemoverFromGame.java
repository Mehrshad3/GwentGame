package controller;

import model.GameStatus;
import model.Player;
import model.Row;
import model.faction.Card;

public class CardRemoverFromGame {

    public static void Remove(GameStatus game, Card card) {
        int row = -1;
        Row[] rows = game.getTable().getRows();
        for (int row0 = 0; row0 < 6; row0++) {
            if (rows[row0].getCards().contains(card)) {
                row = row0;
            }
        }
        RemoveHavingRow(game, card, row);
    }

    public static void RemoveHavingRow(GameStatus game, Card card, int row) {
        Player player = new Player("1", "1");
        boolean z = true;
        if (row == 0 || row == 1 || row == 2) {
            player = game.getPlayer1();
        } else if (row == 3 || row == 4 || row == 5) {
            player = game.getPlayer2();
        } else {
            z = false;
        }
        if (z) {
            Row[] rows = game.getTable().getRows();
            rows[row].removeCard(card);
            player.getDeck().getCardsInDeck().add(card);
        } else {


        }

    }
}
