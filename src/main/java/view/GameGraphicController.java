package view;

import controller.GameController;
import enums.Menu;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.faction.Card;
import model.faction.Faction;
import model.faction.UnitCard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameGraphicController {
    private static final double playerInfoWidthPercentOnScreen = 283.75 / 12;
    private static final long debugMenuCooldown = 200000000L;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final GameController gameController;
    private final Image player1FactionImage;
    private final Image player2FactionImage;
    private final Object debugMenuLock = new Object();
    @FXML
    private Label selfSiegePowerLabel;
    @FXML
    private Label selfRangedPowerLabel;
    @FXML
    private Label selfCloseCombatPowerLabel;
    @FXML
    private Label opponentCloseCombatPowerLabel;
    @FXML
    private Label opponentRangedPowerLabel;
    @FXML
    private Label opponentSiegePowerLabel;
    @FXML
    private ImageView selfDiscardPile;
    @FXML
    private ImageView opponentDiscardPile;
    @FXML
    private ImageView opponentDeckImageView;
    @FXML
    private ImageView selfDeckImageView;
    @FXML
    private StackPane selfDeck;
    @FXML
    private StackPane opponentDeck;
    @FXML
    private HBox inHandCards;
    @FXML
    private HBox selfSiegeCombat;
    @FXML
    private HBox selfRangedCombat;
    @FXML
    private HBox selfCloseCombat;
    @FXML
    private HBox opponentCloseCombat;
    @FXML
    private HBox opponentRangedCombat;
    @FXML
    private HBox opponentSiegeCombat;
    @FXML
    private Pane commanderHornSpot2;
    @FXML
    private Pane commanderHornSpot3;
    @FXML
    private Pane commanderHornSpot1;
    @FXML
    private Pane commanderHornSpot4;
    @FXML
    private Pane commanderHornSpot5;
    @FXML
    private Pane commanderHornSpot6;
    @FXML
    private GridPane board;
    @FXML
    private StackPane specialCardsPane;
    @FXML
    private ImageView opponentLeaderCard;
    @FXML
    private ImageView selfLeaderCard;
    @FXML
    private GridPane playersInfo;
    @FXML
    private BorderPane rootPane;
    private Stage debugMenu;
    private long lastTimeDebugHit = 0;
    private short debugMenuHitNumber = 0;
    private double boardWidth;
    private double opponentLeaderVerticalPercent;
    private double leaderHorizontalPercent;
    private double selfLeaderVerticalPercent;
    private double cardVerticalPercent;
    private double cardHorizontalPercent;
    @SuppressWarnings("FieldCanBeLocal")
    private MediaPlayer mediaPlayer;

    public GameGraphicController() {
        gameController = (GameController) Menu.GameMenu.getMenuController();
        Faction player1Faction = gameController.getPlayer1Faction();
        Faction player2Faction = gameController.getPlayer2Faction();
        URL player1FactionURL = getClass().getResource("/IMAGES/Faction" + (player1Faction.ordinal() + 1) + ".jpg");
        URL player2FactionURL = getClass().getResource("/IMAGES/Faction" + (player2Faction.ordinal() + 1) + ".jpg");
        player1FactionImage = new Image(Objects.requireNonNull(player1FactionURL).toExternalForm());
        player2FactionImage = new Image(Objects.requireNonNull(player2FactionURL).toExternalForm());
    }

    @FXML
    private void initialize() {
        rootPane.setPrefWidth(1200);
        rootPane.setPrefHeight(800);

        rootPane.widthProperty().addListener((observableValue, number, t1) -> updateWidths((Double) observableValue.getValue()));
        rootPane.heightProperty().addListener((observableValue, number, t1) -> updateHeights((Double) observableValue.getValue()));

        setImagesPercentInRootPane();

        if (gameController.getSizeOfPlayer1Deck() != 0) selfDeckImageView.setImage(player1FactionImage);
        if (gameController.getSizeOfPlayer2Deck() != 0) opponentDeckImageView.setImage(player2FactionImage);

        System.gc();

        URL musicURL = Objects.requireNonNull(getClass().getResource("/Media/03 Ride For Freedom.wav"));
        Media media = new Media(musicURL.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(-1); // Plays infinitely until the end of game.

        selfSiegePowerLabel.textProperty().bind(gameController.getSumOfRowNumber(1).map(Object::toString));
        selfRangedPowerLabel.textProperty().bind(gameController.getSumOfRowNumber(2).map(Object::toString));
        selfCloseCombatPowerLabel.textProperty().bind(gameController.getSumOfRowNumber(3).map(Object::toString));
        opponentCloseCombatPowerLabel.textProperty().bind(gameController.getSumOfRowNumber(4).map(Object::toString));
        opponentRangedPowerLabel.textProperty().bind(gameController.getSumOfRowNumber(5).map(Object::toString));
        opponentSiegePowerLabel.textProperty().bind(gameController.getSumOfRowNumber(6).map(Object::toString));

        showInHandCards();

        //specialCardsPane = new UnitCardView(); // Todo : see whether it's correct or not
    }

    private void setImagesPercentInRootPane() {
        opponentLeaderVerticalPercent = playersInfo.getRowConstraints().get(1).getPercentHeight();
        selfLeaderVerticalPercent = playersInfo.getRowConstraints().get(5).getPercentHeight();
        leaderHorizontalPercent = playersInfo.getColumnConstraints().get(1).getPercentWidth() +
                playersInfo.getColumnConstraints().get(2).getPercentWidth();
        cardHorizontalPercent = board.getColumnConstraints().get(7).getPercentWidth();
        cardVerticalPercent = board.getRowConstraints().get(7).getPercentHeight();
    }

    private void updateWidths(double screenWidth) {
        double playersInfoWidth = screenWidth * playerInfoWidthPercentOnScreen / 100;
        boardWidth = screenWidth - playersInfoWidth;
        playersInfo.setPrefWidth(screenWidth * playerInfoWidthPercentOnScreen / 100);
        opponentLeaderCard.setFitWidth(leaderHorizontalPercent / 100 * playersInfo.getPrefWidth());
        selfLeaderCard.setFitWidth(leaderHorizontalPercent / 100 * playersInfoWidth);
        selfDeckImageView.setFitWidth(cardHorizontalPercent / 100 * boardWidth);
        opponentDeckImageView.setFitWidth(cardHorizontalPercent / 100 * boardWidth);
        for (Node node : inHandCards.getChildren()) {
            if (node instanceof CardView cardView) {
                updateCardWidth(cardView);
            }
        }
    }

    private void updateHeights(double screenHeight) {
        opponentLeaderCard.setFitHeight(opponentLeaderVerticalPercent / 100 * screenHeight);
        selfLeaderCard.setFitHeight(selfLeaderVerticalPercent / 100 * screenHeight);
        selfDeckImageView.setFitHeight(cardVerticalPercent / 100 * screenHeight);
        opponentDeckImageView.setFitHeight(cardVerticalPercent / 100 * screenHeight);
        for (Node node : inHandCards.getChildren()) {
            if (node instanceof CardView cardView) {
                updateCardHeight(cardView);
            }
        }
    }

    private void updateCardWidth(CardView cardView) {
        cardView.setFitWidth(cardHorizontalPercent / 100 * boardWidth);
    }

    private void updateCardHeight(CardView cardView) {
        cardView.setFitHeight(cardVerticalPercent / 100 * rootPane.getHeight());
    }

    private void showInHandCards() {
        List<Card> player1InHandCards = gameController.getPlayer1InHandCards();
        addCardsToHandView(0, player1InHandCards.size(), player1InHandCards);
        gameController.getPlayer1InHandCards().addListener((ListChangeListener<? super Card>) change -> {
            while (change.next()) { // I don't know why this method is used.
                if (change.wasAdded()) addAddedCardsToHandView(change);
                else if (change.wasPermutated()) {
                    LOGGER.log(Level.SEVERE, "The program is not capable of showing permutated cards.");
                } else if (change.wasRemoved()) removeRemovedCardsFromHandView(change);
                else if (change.wasReplaced()) replaceReplacedCardsInHandView(change);
            }
        });
    }

    private void addAddedCardsToHandView(ListChangeListener.Change<? extends Card> change) {
        try {
            addCardsToHandView(change.getFrom(), change.getTo(), change.getList());
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Exception / Error in updating cards in the view occurred.", e);
        }
    }

    private void addCardsToHandView(int from, int to, List<? extends Card> list) {
        ObservableList<Node> children = inHandCards.getChildren();
        for (int i = from; i < to; i++) {
            Card card = list.get(i);
            CardView cardView;
            if (card instanceof UnitCard) {
                URL imageURL = Objects.requireNonNull(getClass().getResource("/IMAGES/man.png"));
                cardView = new UnitCardView(new Image(imageURL.toExternalForm()), (UnitCard) card);
            } else {
                URL imageURL = Objects.requireNonNull(getClass().getResource("/IMAGES/mon.png"));
                cardView = new CardView(new Image(imageURL.toExternalForm()));
            }
            updateCardWidth(cardView);
            updateCardHeight(cardView);
            children.add(i, cardView);
        }
    }

    private void removeRemovedCardsFromHandView(ListChangeListener.Change<? extends Card> change) {
        ObservableList<Node> children = inHandCards.getChildren();
        children.remove(change.getFrom(), change.getTo());
    }

    private void replaceReplacedCardsInHandView(ListChangeListener.Change<? extends Card> change) {
        removeRemovedCardsFromHandView(change);
        addAddedCardsToHandView(change);
    }

    void initializeWithStage(Stage stage) {
        stage.maximizedProperty().addListener((observableValue, aBoolean, t1) -> {
            updateHeights(rootPane.getHeight());
            updateWidths(rootPane.getWidth());
        });
    }


    @FXML
    private void clickedOnCard(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == selfLeaderCard) {
            gameController.playCard(gameController.getPlayer1LeaderCard());
        }
    }

    @FXML
    private void showSelfDiscardPile(MouseEvent mouseEvent) {
        ArrayList<Card> discardPile = gameController.getPlayer1DiscardPile();
        // TODO
    }

    @FXML
    private void showOpponentDiscardPile(MouseEvent mouseEvent) {
        ArrayList<Card> discardPile = gameController.getPlayer2DiscardPile();
        // TODO
    }

    @FXML
    private void clickedOnDebugMenu(MouseEvent mouseEvent) {
        if (debugMenu != null) {
            debugMenu.requestFocus();
            return;
        }
        long currentTime = System.nanoTime();
        if (currentTime - lastTimeDebugHit > debugMenuCooldown) debugMenuHitNumber = 0;
        if (debugMenuHitNumber > 10 && debugMenu == null) openDebugMenu();
        debugMenuHitNumber++;
        lastTimeDebugHit = currentTime;
    }

    private void openDebugMenu() {
        synchronized (debugMenuLock) {
            try {
                URL url = getClass().getResource("/FXML/DebugMenu.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                BorderPane pane = fxmlLoader.load();
                DebugMenuController debugMenuController = fxmlLoader.getController();
                debugMenuController.setGameController(gameController);
                Scene scene = new Scene(pane);
                debugMenu = new Stage();
                debugMenu.setScene(scene);
                debugMenu.show();
                debugMenu.showingProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (!t1) debugMenu = null;
                });
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Couldn't open the debug menu", e);
                debugMenu = null;
            }
        }
    }
}
