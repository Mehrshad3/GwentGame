package view;

import controller.GameController;
import enums.Menu;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.faction.Card;
import model.faction.Faction;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class GameGraphicController {
    private final static double playerInfoPercent = 283.75 / 12;
    private final GameController gameController;
    private final Image player1FactionImage;
    private final Image player2FactionImage;
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
    private Pane rootPane;
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
        mediaPlayer.play();
        mediaPlayer.setCycleCount(-1);
    }

    private void setImagesPercentInRootPane() {
        opponentLeaderVerticalPercent = playersInfo.getRowConstraints().get(1).getPercentHeight();
        selfLeaderVerticalPercent = playersInfo.getRowConstraints().get(5).getPercentHeight();
        leaderHorizontalPercent = playersInfo.getColumnConstraints().get(1).getPercentWidth() +
                playersInfo.getColumnConstraints().get(2).getPercentWidth();
        cardHorizontalPercent = board.getColumnConstraints().get(7).getPercentWidth();
        cardVerticalPercent = board.getRowConstraints().get(2).getPercentHeight() +
                board.getRowConstraints().get(3).getPercentHeight() + board.getRowConstraints().get(4).getPercentHeight();
    }

    private void updateWidths(double screenWidth) {
        double playersInfoWidth = screenWidth * playerInfoPercent / 100;
        double boardWidth = screenWidth - playersInfoWidth;
        playersInfo.setPrefWidth(screenWidth * playerInfoPercent / 100);
        opponentLeaderCard.setFitWidth(leaderHorizontalPercent / 100 * playersInfo.getPrefWidth());
        selfLeaderCard.setFitWidth(leaderHorizontalPercent / 100 * playersInfoWidth);
        selfDeckImageView.setFitWidth(cardHorizontalPercent / 100 * boardWidth);
        opponentDeckImageView.setFitWidth(cardHorizontalPercent / 100 * boardWidth);
    }

    private void updateHeights(double screenHeight) {
        opponentLeaderCard.setFitHeight(opponentLeaderVerticalPercent / 100 * screenHeight);
        selfLeaderCard.setFitHeight(selfLeaderVerticalPercent / 100 * screenHeight);
        selfDeckImageView.setFitHeight(cardVerticalPercent / 100 * screenHeight);
        opponentDeckImageView.setFitHeight(cardVerticalPercent / 100 * screenHeight);
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
    public void showOpponentDiscardPile(MouseEvent mouseEvent) {
        ArrayList<Card> discardPile = gameController.getPlayer2DiscardPile();
        // TODO
    }
}
