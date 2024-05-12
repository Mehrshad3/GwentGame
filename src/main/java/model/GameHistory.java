package model;

import java.sql.Date;
import java.util.Arrays;

public record GameHistory(Player player1, Player player2, int[] scoresOfPlayer1, int[] scoresOfPlayer2, Date date,
                          int finalWinner) {
    /**
     * @param finalWinner is 1 if player1 won, 2 if player2 won, and 0 if the entire game tied.
     */
    public GameHistory(Player player1, Player player2, int[] scoresOfPlayer1, int[] scoresOfPlayer2, Date date, int finalWinner) {
        if (finalWinner < 0 || finalWinner > 2) throw new IllegalArgumentException("finalWinner is invalid");
        if (scoresOfPlayer1.length != 3) throw new IllegalArgumentException("scoresOfPlayer1 must have length 3");
        if (scoresOfPlayer2.length != 3) throw new IllegalArgumentException("scoresOfPlayer2 must have length 3");
        this.player1 = player1;
        this.player2 = player2;
        this.scoresOfPlayer1 = scoresOfPlayer1.clone();
        this.scoresOfPlayer2 = scoresOfPlayer2.clone();
        this.date = date;
        this.finalWinner = finalWinner;
    }

    public int getFinalPlayer1Score() {
        return Arrays.stream(scoresOfPlayer1).sum();
    }

    public int getFinalPlayer2Score() {
        return Arrays.stream(scoresOfPlayer2).sum();
    }
}
