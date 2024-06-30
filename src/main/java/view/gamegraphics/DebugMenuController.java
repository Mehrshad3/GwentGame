package view.gamegraphics;

import controller.GameController;
import enums.card.CardName;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    @FXML
    private void increasePlayerLives() {
        gameController.fillPlayer1LivesForDebug();
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
