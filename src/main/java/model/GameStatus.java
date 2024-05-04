package model;

public class GameStatus {
    private int numberOfPassedRounds = 0;
    private Table table;
    private Player player1;
    private Player player2;
    private int numberOfTurns = 0;
    private int player1Wins;
    private int player2Wins;

    public GameStatus(Table table, Player player1, Player player2, int player1Wins, int player2Wins) {
        this.table = table;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Wins = player1Wins;
        this.player2Wins = player2Wins;
    }

    public int getNumberOfPassedRounds() {
        return numberOfPassedRounds;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }

    public int getNumberOfRecentPastTurns() {
        return table.getNumberOfTurnsPassed();
    }

    public void changeTurn() {
        table.changeTurn();
    }
}