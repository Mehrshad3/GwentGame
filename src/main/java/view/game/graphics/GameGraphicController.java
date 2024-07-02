package view.game.graphics;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.App;
import model.faction.Card;
import model.faction.Faction;
import model.faction.LeaderCard;
import model.faction.UnitCard;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class GameGraphicController {
    private static final double playerInfoWidthPercentOnScreen = 283.75 / 12;
    private static final long debugMenuCooldown = 200000000L;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final int MAXIMUM_NUMBER_OF_CARDS_IN_HAND = 10;
    private static final int NUMBER_OF_ROWS = 6;
    private final GameController gameController;
    private final Image player1FactionImage;
    private final Image player2FactionImage;
    private final Object debugMenuLock = new Object();
    private final Pane[] commanderHornSpots = new Pane[NUMBER_OF_ROWS];
    private final HBox[] rows = new HBox[NUMBER_OF_ROWS];
    @FXML
    private Text opponentPower;
    @FXML
    private Text selfPower;
    @FXML
    private ImageView opponentSecondLife;
    @FXML
    private ImageView opponentFirstLife;
    @FXML
    private ImageView selfSecondLife;
    @FXML
    private ImageView selfFirstLife;
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
    private HBox specialCardsPane;
    @FXML
    private ImageView opponentLeaderCard;
    @FXML
    private ImageView selfLeaderCard;
    @FXML
    private GridPane playersInfo;
    @FXML
    private BorderPane rootPane;
    private CardView cardChosenToPlay = null;
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

    /**
     * This method is used in {@link #initialize} method, and also when you want to reload the game status screen from
     * the scratch, except two players' factions.
     */
    public void loadGameStatus() {
        if (gameController.getSizeOfPlayer1Deck() != 0) selfDeckImageView.setImage(player1FactionImage);
        if (gameController.getSizeOfPlayer2Deck() != 0) opponentDeckImageView.setImage(player2FactionImage);

        selfSiegePowerLabel.textProperty().bind(gameController.getSumOfRowNumber(1).map(Object::toString));
        selfRangedPowerLabel.textProperty().bind(gameController.getSumOfRowNumber(2).map(Object::toString));
        selfCloseCombatPowerLabel.textProperty().bind(gameController.getSumOfRowNumber(3).map(Object::toString));
        opponentCloseCombatPowerLabel.textProperty().bind(gameController.getSumOfRowNumber(4).map(Object::toString));
        opponentRangedPowerLabel.textProperty().bind(gameController.getSumOfRowNumber(5).map(Object::toString));
        opponentSiegePowerLabel.textProperty().bind(gameController.getSumOfRowNumber(6).map(Object::toString));

        showInHandCards();
        showLeaderCards();
    }

    @FXML
    private void initialize() {
        rootPane.setPrefWidth(1200);
        rootPane.setPrefHeight(800);

        rootPane.widthProperty().addListener((observableValue, number, t1) ->
                updateWidths((Double) observableValue.getValue()));
        rootPane.heightProperty().addListener((observableValue, number, t1) ->
                updateHeights((Double) observableValue.getValue()));

        setImagesPercentInRootPane();

        initializeRowsArray();

        loadGameStatus();

        System.gc();

        URL musicURL = Objects.requireNonNull(getClass().getResource("/Media/03 Ride For Freedom.wav"));
        Media media = new Media(musicURL.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(-1); // Plays infinitely until the end of game.
    }

    private void initializeRowsArray() {
        this.rows[0] = selfSiegeCombat;
        this.rows[1] = selfRangedCombat;
        this.rows[2] = selfCloseCombat;
        this.rows[3] = opponentCloseCombat;
        this.rows[4] = opponentRangedCombat;
        this.rows[5] = opponentSiegeCombat;
    }

    private void initializeCommanderHornSpotsArray() {
        this.commanderHornSpots[0] = commanderHornSpot1;
        this.commanderHornSpots[1] = commanderHornSpot2;
        this.commanderHornSpots[2] = commanderHornSpot3;
        this.commanderHornSpots[3] = commanderHornSpot4;
        this.commanderHornSpots[4] = commanderHornSpot5;
        this.commanderHornSpots[5] = commanderHornSpot6;
    }

    private double getNodeVerticalPercentInGridPane(Node node) {
        int beginIndex = GridPane.getRowIndex(node);
        Integer rowSpan = GridPane.getRowSpan(node);
        int lastIndex = beginIndex + (rowSpan != null ? rowSpan : 1);
        GridPane nodeParent = (GridPane) node.getParent();
        ObservableList<RowConstraints> rowConstraints = nodeParent.getRowConstraints();
        double sumOfPercents = 0;
        for (int i = beginIndex; i < lastIndex; i++) {
            sumOfPercents += rowConstraints.get(i).getPercentHeight();
        }
        return sumOfPercents;
    }

    private double getNodeHorizontalPercentInGridPane(Node node) {
        int beginIndex = GridPane.getColumnIndex(node);
        Integer columnSpan = GridPane.getColumnSpan(node);
        int lastIndex = beginIndex + (columnSpan != null ? columnSpan : 1);
        GridPane nodeParent = (GridPane) node.getParent();
        ObservableList<ColumnConstraints> columnConstraints = nodeParent.getColumnConstraints();
        double sumOfPercents = 0;
        for (int i = beginIndex; i < lastIndex; i++) {
            sumOfPercents += columnConstraints.get(i).getPercentWidth();
        }
        return sumOfPercents;
    }

    private void setImagesPercentInRootPane() {
        opponentLeaderVerticalPercent = getNodeVerticalPercentInGridPane(opponentLeaderCard.getParent());
        selfLeaderVerticalPercent = getNodeVerticalPercentInGridPane(selfLeaderCard.getParent());
        leaderHorizontalPercent = getNodeHorizontalPercentInGridPane(selfLeaderCard.getParent());
        cardHorizontalPercent = getNodeHorizontalPercentInGridPane(inHandCards) / MAXIMUM_NUMBER_OF_CARDS_IN_HAND;
        cardVerticalPercent = board.getRowConstraints().get(7).getPercentHeight();
    }

    private Stream<CardView> getCardsToUpdateSize() {
        return Stream.concat(
                inHandCards.getChildren().stream()
                        .filter(node -> node instanceof CardView).map(node -> (CardView) node),
                specialCardsPane.getChildren().stream()
                        .filter(node -> node instanceof CardView).map(node -> ((CardView) node))
        );
    }

    private void updateWidths(double screenWidth) {
        double playersInfoWidth = screenWidth * playerInfoWidthPercentOnScreen / 100;
        boardWidth = screenWidth - playersInfoWidth;
        playersInfo.setPrefWidth(screenWidth * playerInfoWidthPercentOnScreen / 100);
        opponentLeaderCard.setFitWidth(leaderHorizontalPercent / 100 * playersInfo.getPrefWidth());
        selfLeaderCard.setFitWidth(leaderHorizontalPercent / 100 * playersInfoWidth);
        selfDeckImageView.setFitWidth(cardHorizontalPercent / 100 * boardWidth);
        opponentDeckImageView.setFitWidth(cardHorizontalPercent / 100 * boardWidth);
        getCardsToUpdateSize().forEach(this::updateCardWidth);
    }

    private void updateHeights(double screenHeight) {
        opponentLeaderCard.setFitHeight(opponentLeaderVerticalPercent / 100 * screenHeight);
        selfLeaderCard.setFitHeight(selfLeaderVerticalPercent / 100 * screenHeight);
        selfDeckImageView.setFitHeight(cardVerticalPercent / 100 * screenHeight);
        opponentDeckImageView.setFitHeight(cardVerticalPercent / 100 * screenHeight);
        getCardsToUpdateSize().forEach(this::updateCardHeight);
    }

    private void updateCardWidth(CardView cardView) {
        cardView.setFitWidth(cardHorizontalPercent / 100 * boardWidth
                - inHandCards.getSpacing() * (1 + (double) 1 / MAXIMUM_NUMBER_OF_CARDS_IN_HAND)
        );
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

    private void showLeaderCards() {
        LeaderCard player1LeaderCard = gameController.getPlayer1LeaderCard();
        LeaderCard player2LeaderCard = gameController.getPlayer2LeaderCard();
        selfLeaderCard.setImage(CardImageLoader.loadImage(player1LeaderCard));
        opponentLeaderCard.setImage(CardImageLoader.loadImage(player2LeaderCard));
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
            children.add(i, createCardViewOf(card));
        }
    }

    private CardView createCardViewOf(Card card) {
        CardView cardView;
        if (card instanceof UnitCard) cardView = new UnitCardView((UnitCard) card);
        else cardView = new CardView(card);
        updateCardWidth(cardView);
        updateCardHeight(cardView);
        cardView.imageView.setOnMouseClicked(this::mousePressedOnCard);
        return cardView;
    }

    private void removeRemovedCardsFromHandView(ListChangeListener.Change<? extends Card> change) {
        assert change.getFrom() == change.getTo();
        ObservableList<Node> children = inHandCards.getChildren();
        children.remove(change.getFrom(), change.getTo() + change.getRemovedSize());
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
    private void mousePressedOnCard(MouseEvent mouseEvent) {
        App.LOGGER.log(Level.FINER, "Clicked on the card", mouseEvent);
        Node source = (Node) mouseEvent.getSource();
        if (source == selfLeaderCard) {
            App.LOGGER.log(Level.FINE, "Current user clicked on the leader card.");
            gameController.playLeaderCard(gameController.getPlayer1LeaderCard());
        } else if (source.getParent() instanceof CardView cardView) {
            cardChosenToPlay = cardChosenToPlay == null ? cardView : null;
            // It'll be beautiful if we ask the controller that which rows can this card be played to.
        }
    }

    /**
     * This method is used to detect when the player wants to place the card that they've been chosen.
     * It unfortunately doesn't detect the exact index in which the card is played.
     */
    @FXML
    private void onRowClicked(MouseEvent mouseEvent) {
        if (cardChosenToPlay == null) return; // There is no card to play.
        Pane source = (Pane) mouseEvent.getSource();
        // Detect the row number which the card will be played on.
        int rowToPlay = 0;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            if (rows[i] == source) rowToPlay = i + 1;
        }
        // Tells the controller to play the card
        gameController.playCard(cardChosenToPlay.card, rowToPlay);
        cardChosenToPlay = null;
    }

    @FXML
    private void onCommanderHornSpotClicked(MouseEvent mouseEvent) {
        if (cardChosenToPlay == null) return; // There is no card to play.
        Pane source = (Pane) mouseEvent.getSource();
        // Detect the row number which the card will be played on.
        int rowToPlay = 0;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            if (commanderHornSpots[i] == source) rowToPlay = i + 1;
        }
        // Tells the controller to play the card
        gameController.playCommanderHorn(cardChosenToPlay.card, rowToPlay);
        cardChosenToPlay = null;
    }

    @FXML
    private void onSpecialPaneClicked() {
        if (cardChosenToPlay == null) return;
        gameController.playWeatherCard(cardChosenToPlay.card);
        cardChosenToPlay = null;
    }

    @FXML
    private void showSelfDiscardPile() {
        ObservableList<Card> discardPile = gameController.getPlayer1DiscardPile();
        // TODO
    }

    @FXML
    private void showOpponentDiscardPile() {
        ObservableList<Card> discardPile = gameController.getPlayer2DiscardPile();
        // TODO
    }

    @FXML
    private void clickedOnDebugMenu() {
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
