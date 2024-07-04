package view.game.graphics;

import controller.GameController;
import enums.card.CardName;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import model.App;
import model.faction.Card;

import java.util.Optional;
import java.util.logging.Level;

public class DebugMenuController {
    private GameController gameController;

    @FXML
    private void addCardToHand() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Card name");
        Optional<String> optional = textInputDialog.showAndWait();
        Card card;
        if (optional.isPresent()) {
            card = CardName.getCardByName(optional.get());
            if (card != null) {
                gameController.addCardToHandForDebug(card);
                App.LOGGER.log(Level.FINER, "Card" + card.getName() + " added to hand.");
            }
        }
    }

    private int askForWhichPlayer() {
        String[] playerNames = {"Player 1", "Player 2"};
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Which player?", playerNames);
        choiceDialog.showAndWait();
        if (choiceDialog.getSelectedItem().equals(playerNames[0])) return 1;
        else if (choiceDialog.getSelectedItem().equals(playerNames[1])) return 2;
        else return 0;
    }

    @FXML
    private void fillPlayerLives() {
        int playerNumber = askForWhichPlayer();
        if (playerNumber == 1) gameController.fillPlayer1LivesForDebug();
        else if (playerNumber == 2) gameController.fillPlayer2LivesForDebug();
    }

    @FXML
    private void decreasePlayerLives() {
        int playerNumber = askForWhichPlayer();
        if (playerNumber == 1) gameController.decreasePlayer1LivesForDebug();
        else if (playerNumber == 2) gameController.decreasePlayer2LivesForDebug();
    }

    void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void showHandCardNames() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Cards in the hand");
        alert.setTitle("Cards in the hand");
        StringBuilder contextText = new StringBuilder("Cards in the hand:\n");
        for (Card card : gameController.getPlayer1InHandCards()) {
            contextText.append(card.getName()).append("\n");
        }
        alert.setContentText(contextText.toString());
        alert.show();
    }
}
