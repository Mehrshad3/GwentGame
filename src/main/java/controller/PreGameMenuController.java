package controller;

import enums.Menu;
import model.*;
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
        System.err.println("Player's deck is empty");
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
        User currentUser = User.getCurrentUser();
        Player player1 = new Player(currentUser.getName(), currentUser.getNickname()); // TODO: replace this
        Player player2 = new Player("", "opponent"); // TODO: replace this
        Table table = new Table(player1, player2);
        // TODO
        App.setCurrentMenu(Menu.GameMenu);
        MenuController gameMenuController = Menu.GameMenu.getMenuController();
        GameController gameController = (GameController) gameMenuController;
        gameController.setGaming(new GameStatus(table, player1, player2));
    }
}
