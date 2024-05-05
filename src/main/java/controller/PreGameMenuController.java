package controller;

import enums.Menu;
import model.App;
import model.GameStatus;
import model.Player;
import model.Table;
import view.AppView;

public class PreGameMenuController extends MenuController {


    public void createGame(String player2Username) {
        // TODO
    }

    public void showFactions() {
        // TODO
    }

    public void selectFaction(String factionName) {
        // TODO
    }

    public void showCards() {
        // TODO
    }

    public void showDeck() {
        // TODO
    }

    public void showCurrentUserInfo() {
        // TODO
    }

    private void emptyPlayerDeckError() {
        AppView.getAppViewObject().serrPrintStream.println("Player's deck is empty");
    }

    public void changeTurn() {
        // TODO
    }

    public void saveDeckByName(String name) {
        // TODO
    }

    public void saveDeckToFileAddress(String fileAddress) {
        // TODO
    }

    public void loadDeckFromFileAddress(String fileAddress) {
        // TODO
    }

    public void showLeaders() {
        // TODO
    }

    public void selectLeader(int leaderNumber) {
        // TODO
    }

    public void addToDeck(String cardName) {
        addToDeck(cardName, 1);
    }

    public void addToDeck(String cardName, int count) {
        // TODO
    }

    public void deleteCardFromDeck(int cardNumber) {
        deleteCardFromDeck(cardNumber, 1);
    }

    public void deleteCardFromDeck(int cardNumber, int count) {
        // TODO
    }

    public void startGame() {
        GameStatus gaming;
        Player player1 = new Player(); // TODO: replace this
        Player player2 = new Player(); // TODO: replace this
        Table table = new Table(player1, player2);
        // TODO
        App.setCurrentMenu(Menu.GameMenu);
        MenuController gameMenuController = Menu.GameMenu.getMenuController();
        GameController gameController = (GameController) gameMenuController;
        gameController.setGaming(new GameStatus(table, player1, player2, player1.getWins(), player2.getWins()));
    }
}
