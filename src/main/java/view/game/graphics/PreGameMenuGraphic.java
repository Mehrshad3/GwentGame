package view.game.graphics;

import controller.GameController;
import enums.Menu;
import enums.card.CardName;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.App;
import model.Deck;
import model.GsonReaderWriter;
import model.User;
import model.faction.Card;
import model.faction.LeaderCard;
import view.Animation.FactionCardAnimation;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;

public class PreGameMenuGraphic extends Application {
    private HBox topButtons;
    private HBox bottomButtons;
    private HBox middleButtons;

    @Override
    public void start(Stage stage) {
//        User.setCurrentUser(GsonReaderWriter.getGsonReaderWriter().loadUser("Mehrshad"));
//        User.getCurrentUser().setFaction("monsters");
//        User.setCurrentUser(GsonReaderWriter.loadFromFile(User.getRelativePathToFile("Mehrshad"),User.class));
//        User.getCurrentUser().getDeck().addCardToDeck(new Card("man",false, NonCommanderCardAbility.SPY,10));
//        User.getCurrentUser().getDeck().addCardToDeck(new Card("mon",false,NonCommanderCardAbility.SPY,10));
//        User.getCurrentUser().getDeck().addCardToDeck(new Card("min",false,NonCommanderCardAbility.SPY,10));
        VBox buttons = new VBox();
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        topButtons = new HBox();
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setSpacing(20);

        bottomButtons = new HBox();
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setSpacing(20);

        middleButtons = new HBox();
        middleButtons.setAlignment(Pos.CENTER);
        middleButtons.setSpacing(20);

        buttons.getChildren().addAll(topButtons, bottomButtons, middleButtons);

        BorderPane pane = new BorderPane();
//        pane.setCenter(topButtons);
        pane.setBackground(null);

        pane.setCenter(buttons);
        Scene scene = new Scene(pane);
        URL backgroundImageUrl = Objects.requireNonNull(getClass().getResource("/IMAGES/back.jpeg"));
        scene.setFill(new ImagePattern(new Image(backgroundImageUrl.toExternalForm())));
        setButtons();
        App.setPreGameMenu(scene);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(800);
        stage.setMinWidth(1200);
        stage.centerOnScreen();
    }

    private void setButtons() {
        Button StartNewGame = new Button("New Game");
        StartNewGame.setOnAction(actionEvent -> startNewGame());
        topButtons.getChildren().add(StartNewGame);

        Button ShowFraction = new Button("Factions");
        ShowFraction.setOnAction(actionEvent -> showFactions());
        topButtons.getChildren().add(ShowFraction);

        Button ChangeFaction = new Button("Change Faction");
        ChangeFaction.setOnAction(actionEvent -> changeUserFaction());
        topButtons.getChildren().add(ChangeFaction);

        Button ShowCards = new Button("Show Cards");
        ShowCards.setOnAction(actionEvent -> showNonLeaderCards());
        topButtons.getChildren().add(ShowCards);

        Button ShowDeck = new Button("Show Deck");
//        ShowDeck.setOnAction(actionEvent -> showDeck());
        ShowDeck.setOnAction(actionEvent -> showDeckCards());
        topButtons.getChildren().add(ShowDeck);

        Button ShowInfo = new Button("Show Info");
        ShowInfo.setOnAction(actionEvent -> showInfo());
        topButtons.getChildren().add(ShowInfo);

        Button SaveDeckByName = new Button("Save Deck By Name");
        SaveDeckByName.setOnAction(actionEvent -> saveDeckByName());
        bottomButtons.getChildren().add(SaveDeckByName);

        Button SaveDeckToFile = new Button("Save To File");
        SaveDeckToFile.setOnAction(actionEvent -> saveDeckToFile());
        bottomButtons.getChildren().add(SaveDeckToFile);

        Button LoadDeckFromFile = new Button("Load Deck From File");
        LoadDeckFromFile.setOnAction(actionEvent -> loadDeckFromFile());
        bottomButtons.getChildren().add(LoadDeckFromFile);

//        ImageView imageView = new ImageView(new Image(getClass().getResource("/IMAGES/1.png").toExternalForm()));
//        bottomButtons.getChildren().add(imageView);
//        imageView.setOnAction(actionEvent -> System.out.println("hi"));

        Button LoadDeckByName = new Button("Load Deck By Name");
        LoadDeckByName.setOnAction(actionEvent -> loadDeckByName());
        bottomButtons.getChildren().add(LoadDeckByName);

        Button ShowLeaders = new Button("Leaders");
        ShowLeaders.setOnAction(actionEvent -> showLeaders());

        Button SelectLeader = new Button("Select Leader");
        SelectLeader.setOnAction(actionEvent -> selectLeader());

        Button AddCardToDeck = new Button("Add To Deck");
        AddCardToDeck.setOnAction(actionEvent -> addCardToDeck());

        Button DeleteCardFromDeck = new Button("Delete From Deck");
        DeleteCardFromDeck.setOnAction(actionEvent -> deleteFromDeck());

        middleButtons.getChildren().addAll(ShowLeaders, SelectLeader, AddCardToDeck, DeleteCardFromDeck);


        for (Node child : topButtons.getChildren()) {
            ((Button) child).setMinWidth(60);
            ((Button) child).setMinHeight(40);
        }
        for (Node child : bottomButtons.getChildren()) {
            ((Button) child).setMinWidth(60);
            ((Button) child).setMinHeight(40);
        }
    }

    private void deleteFromDeck() {
        TextInputDialog card = new TextInputDialog();
        card.setContentText("Enter Card Name");
        Optional<String> name = card.showAndWait();
        boolean contains = false;
        if (name.isPresent() && !name.get().matches("//s*")) {
            for (Card inDeckCard : User.getCurrentUser().getDeck().getCardsInDeck()) {
                if (inDeckCard.getName().equals(name.get())) {
                    contains = true;
                }
            }
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (contains) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Card " + name.get() + "deleted");
                alert.show();
                ArrayList<Card> copy = new ArrayList<>(User.getCurrentUser().getDeck().getInHandCards());
                int index = 0;
                for (Card inHandCard : copy) {
                    if (inHandCard.getName().equalsIgnoreCase(name.get())) {
                        User.getCurrentUser().getDeck().getInHandCards().remove(index);
                        break;
                    }
                    index++;
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("You don't have card " + name.get() + " in your hand");
                alert.show();
            }
        }
    }

    private void addCardToDeck() {
        TextInputDialog card = new TextInputDialog();
        card.setContentText("Enter Card Name");
        Optional<String> name = card.showAndWait();
        if (name.isPresent() && !name.get().isEmpty()) {
//            System.out.println(name.get());
            int counter = 0;
            for (Card cardInDeck : User.getCurrentUser().getDeck().getCardsInDeck()) {
                if (cardInDeck.getName().equalsIgnoreCase(name.get())) {
                    counter++;
                }
            }
            Card newCard = CardName.getCardByName(name.get());
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (newCard == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No such card");
                alert.show();
            } else if (counter == CardName.getMaximumNumberOfCardsByName(name.get())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("maximum number of " + name.get() + " in deck");
                alert.show();
            } else {
                if (User.getCurrentUser().getDeck().getCardsInDeck().size() == 32) {//TODO check if spells are more than 10
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Your deck in already full");
                    alert.show();
                } else {
                    CardName cardName = CardName.getCardNameEnumByName(name.get());
                    if ((getClass().getResource(CardImageLoader.getRelativePathToCard(cardName))) != null) {
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setContentText("Card " + name.get() + " Added to Your deck");
                        alert.show();
                        User.getCurrentUser().getDeck().addCardToDeck(newCard);
                    } else {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("Card " + name.get() + " is not from your faction");
                        alert.show();
                    }
                }
            }
        }
    }

    private void selectLeader() {
        TextInputDialog LeaderName = new TextInputDialog();
        LeaderName.setContentText("Enter Leader name");
        Optional<String> leader = LeaderName.showAndWait();
        if (leader.isPresent()) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            CardName cardName = CardName.getCardNameEnumByName(leader.get());
            if (cardName == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No such Leader");
                alert.show();
            } else if (!cardName.isLeader) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("The card chosen is not a leader card.");
                alert.show();
            } else if (cardName.faction != User.getCurrentUser().getFaction()) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("Leader " + leader.get() + " is not from your faction.");
                alert.show();
            } else {
                User.getCurrentUser().getDeck().setCurrentLeaderCard((LeaderCard) CardName.getCardByName(leader.get()));
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Leader selected");
                alert.show();
            }
        }
    }

    private void showLeaders() {
        String leadersDirectoryPath = "/IMAGES/leaders/" + User.getCurrentUser().getFaction().getName() + "/";
        URL leadersDirectoryURL = Objects.requireNonNull(getClass().getResource(leadersDirectoryPath));
        showCards(leadersDirectoryURL);
    }

    private void loadDeckByName() {
        TextInputDialog nameInput = new TextInputDialog();
        nameInput.setContentText("Enter deck Name");
        nameInput.setHeaderText("Load deck");
        Optional<String> name = nameInput.showAndWait();
        if (name.isPresent() && !name.get().isEmpty()) {
            Deck deck = GsonReaderWriter.getGsonReaderWriter().loadDeckByName(name.get());
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (deck == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No such Deck");
                alert.show();
            } else {
                App.LOGGER.log(Level.FINER, "Deck loaded having cards: ", deck.getCardsInDeck());
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Deck Loaded");
                alert.show();
                User.getCurrentUser().setDeck(deck);
            }
        }
    }

    private void loadDeckFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Deck");

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("All Files", "*.*");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            System.out.println(file.getAbsolutePath());
            Deck deck = GsonReaderWriter.getGsonReaderWriter().loadDeckFromFile(file);
            User.getCurrentUser().setDeck(deck);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deck Loaded");
            alert.show();
        }
    }

    private void saveDeckToFile() {
        Deck deck = User.getCurrentUser().getDeck();
        if (deck.getCurrentLeaderCard() == null) {
            //TODO
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Gson", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) GsonReaderWriter.getGsonReaderWriter().saveDeckToFile(User.getCurrentUser().getDeck(), file);
    }

    private void saveDeckByName() {
        TextInputDialog nameInput = new TextInputDialog();
        nameInput.setContentText("Enter name for your deck");
        Optional<String> name = nameInput.showAndWait();
        if (name.isPresent() && !name.get().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (GsonReaderWriter.getGsonReaderWriter().loadDeckByName(name.get()) != null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("A deck already exists with this name");
                alert.show();
            } else {
                App.LOGGER.log(Level.FINE, "Deck that is going to be saved consists these cards: ",
                        User.getCurrentUser().getDeck().getCardsInDeck());
                GsonReaderWriter.getGsonReaderWriter().saveDeckByName(User.getCurrentUser().getDeck(), name.get());
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Deck " + name.get() + " saved");
                alert.show();
            }
        }
    }

    private void showInfo() {
        BorderPane pane = new BorderPane();
        Label info = new Label();
        VBox box = new VBox(info);

        Button back = new Button("Back");
        pane.setLeft(back);
        back.setOnAction(actionEvent -> App.getStage().setScene(App.getPreGameMenu()));

        pane.setCenter(box);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        info.setTextFill(Color.color(0, 0, 0));
        info.setText("name: " + User.getCurrentUser().getName() + "\n\nnickname: " + User.getCurrentUser().getNickname() +
                "\n\nnumberOfWins: " + User.getCurrentUser().getWins() + "\n\nnumberOfLoses: " + User.getCurrentUser().getNumberOfLoses() +
                "\n\nfaction: " + User.getCurrentUser().getFaction() + "\n\nnumberOfCardsInDeck: " + User.getCurrentUser().getDeck().getNumberOfCardsInDeck() +
                "\n\nnumberOfSoldiers: " + User.getCurrentUser().getNumberOfSoldiers() + "\n\nnumberOfSpecialCards: " +
                "\n\nnumberOfHeroCards: " + "" + "\n\nallCardsPower: " + SumOfCardPowers());
        info.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Scene scene = new Scene(pane);
        Stage stage = App.getStage();
        stage.setScene(scene);
        stage.show();
//        Stage stage1 = App.getStage();
//        stage1.close();
    }

    private String SumOfCardPowers() {
        int power = 0;
        for (Card card : User.getCurrentUser().getDeck().getCardsInDeck()) {
            if (card.getInitialPower() != null) {
                power += card.getInitialPower();
            }
        }
        return String.valueOf(power);
    }

    private void showDeck() {
        BorderPane pane = new BorderPane();
        HBox topCards = new HBox();
        HBox middleCards = new HBox();
        HBox bottomCards = new HBox();
        topCards.setAlignment(Pos.CENTER);
        middleCards.setAlignment(Pos.CENTER);
        bottomCards.setAlignment(Pos.CENTER);
        topCards.setSpacing(50);
        middleCards.setSpacing(50);
        bottomCards.setSpacing(50);
        VBox cards = new VBox();
        cards.setSpacing(30);
        cards.setAlignment(Pos.CENTER);
        cards.getChildren().addAll(topCards, middleCards, bottomCards);
        pane.setCenter(cards);

        Button back = new Button("Back");
        pane.setLeft(back);
        back.setOnAction(actionEvent -> App.getStage().setScene(App.getPreGameMenu()));

        pane.setId("pane");
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/1.css")).toExternalForm());
        int counter = 0;
        HBox hBox = topCards;
//        System.out.println(User.getCurrentUser().getDeck().getCardsInDeck().get(0).getName());
        for (Card card : User.getCurrentUser().getDeck().getCardsInDeck()) {
            if (card != null) {
                if (counter == 7) {
                    hBox = middleCards;
                }
                if (counter == 14) {
                    hBox = bottomCards;
                }
                ImageView cardImage = new ImageView();
                cardImage.setFitHeight(200);
                cardImage.setFitWidth(120);
                cardImage.setImage(CardImageLoader.loadImage(card));//ToDO make all images jpg
                hBox.getChildren().add(cardImage);
                counter++;
            }
        }
        Stage stage = App.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void showDeckCards(){
        Button back = new Button("Back");
        back.setOnAction(actionEvent -> App.getStage().setScene(App.getPreGameMenu()));

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(back
        );
        ScrollPane pane = new ScrollPane();
        GridPane gridPane = makeGridPane();
        setUpGridPane(gridPane);
        setCardsInGridPane(gridPane);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setContent(gridPane);
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10),
                new Insets(20,20,20,20))));
        pane.setFitToHeight(true);
        pane.setFitToWidth(true);
        borderPane.setCenter(pane);
        gridPane.setAlignment(Pos.CENTER);
        Stage stage = App.getStage();
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setCardsInGridPane(GridPane gridPane) {
        int counter = 1;
        for (Card card : User.getCurrentUser().getDeck().getCardsInDeck()) {
            ImageView imageView = new ImageView();
            gridPane.add(imageView,(counter-1)%3,(counter-1)/3);
            imageView.setImage(CardImageLoader.loadImage(card));
//            imageView.setImage(new Image(getClass().getResource("/IMAGES/" +  + ".jpg").toExternalForm()));
            imageView.setFitHeight(360);
            imageView.setFitWidth(220);
            counter++;
        }
    }

    private void setUpGridPane(GridPane gridPane) {
        for (int i = 0; i < 3; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setFillWidth(true);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < (User.getCurrentUser().getDeck().getCardsInDeck().size()/3) + 1; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setFillHeight(true);
            gridPane.getRowConstraints().add(rowConstraints);
        }
    }

    private GridPane makeGridPane(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(14);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20,20,20,20));
        return gridPane;
    }

    private void showCards(URL... rootDirectoryUrls) {
        BorderPane pane = new BorderPane();
        HBox cards = new HBox();

        Button leftButton = new Button("Previous");
        Button rightButton = new Button("Next");
        ImageView card = new ImageView();

        Button back = new Button("Back");
        back.setOnAction(actionEvent -> App.getStage().setScene(App.getPreGameMenu()));
        pane.setLeft(back);
//        card.setId("imageView");
//
//        Rectangle rectangle = new Rectangle(card.getFitWidth(),card.getFitHeight());
//        rectangle.setArcHeight(30);
//        rectangle.setArcWidth(30);


//        StackPane image = new StackPane();
//        image.getChildren().add(card);
//        image.setId("imageView");
        cards.getChildren().addAll(leftButton, card, rightButton);
        cards.setSpacing(50);
        cards.setAlignment(Pos.CENTER);
        pane.setCenter(cards);

//        Rectangle clip = new Rectangle(330, 610);
        Rectangle clip = new Rectangle(410, 775);
        clip.setArcHeight(80);
        clip.setArcWidth(80);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
//        WritableImage image = imageView.snapshot(parameters, null);
        card.setClip(clip);
        ArrayList<Image> images = new ArrayList<>();
        for (URL url : rootDirectoryUrls) {
            String rootDirectoryPath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
            File file = new File(rootDirectoryPath);
            for (File file1 : Objects.requireNonNull(file.listFiles())) {
                images.add(new Image(String.valueOf(file1)));
            }
        }
        int[] counter = new int[1];
        leftButton.setOnAction(actionEvent -> {
            counter[0] = (counter[0] + images.size() - 1) % images.size();
            card.setImage(images.get(counter[0]));
            WritableImage image = card.snapshot(parameters, null);
            card.setImage(image);
//            pattern.set(new ImagePattern(images.get(counter[0])));
//            rectangle.setFill(pattern.get());

        });
        rightButton.setOnAction(actionEvent -> {
            counter[0] = (counter[0] + images.size() + 1) % images.size();
            card.setImage(images.get(counter[0]));
            WritableImage image = card.snapshot(parameters, null);
            card.setImage(image);
//            pattern.set(new ImagePattern(images.get(counter[0])));
//            rectangle.setFill(pattern.get());
//            Rectangle clip = new Rectangle(card.getFitWidth(),card.getFitHeight());
//            clip.setArcHeight(20);
//            clip.setArcWidth(20);
//            card.setClip(clip);
//            SnapshotParameters parameters = new SnapshotParameters();
//            parameters.setFill(Color.TRANSPARENT);
//            WritableImage image = card.snapshot(parameters, (WritableImage) card.getImage());
//            card.setClip(null);
//            card.setEffect(new DropShadow(20,Color.BLACK));
//            card.setImage(image);

        });
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/1.css")).toExternalForm());
        pane.setId("black");
        pane.setCenter(cards);
        card.setImage(images.get(counter[0]));
        WritableImage image = card.snapshot(parameters, null);
        card.setImage(image);
//        pattern.set(new ImagePattern(images.get(counter[0])));
//        rectangle.setFill(pattern.get());
        Stage stage = App.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void showNonLeaderCards() {
        String factionDirectoryPath = "/IMAGES/" + User.getCurrentUser().getFaction().getName() + "/";
        URL factionDirectoryUrl = Objects.requireNonNull(getClass().getResource(factionDirectoryPath));
        URL neutralFactionUrl = Objects.requireNonNull(getClass().getResource("/IMAGES/neutral/"));
        showCards(factionDirectoryUrl, neutralFactionUrl);
    }

    private void changeUserFaction() {
        BorderPane pane = new BorderPane();
        HBox factions = new HBox();
        factions.setAlignment(Pos.CENTER);
        factions.setSpacing(50);
        pane.setCenter(factions);
        pane.setId("pane");
        pane.setBackground(null);

        Button back = new Button("Back");
        pane.setLeft(back);
        back.setOnAction(actionEvent -> App.getStage().setScene(App.getPreGameMenu()));

        Scene scene = new Scene(pane);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/1.css")).toExternalForm());
        for (int i = 0; i < 5; i++) {
            ImageView faction = new ImageView();
            faction.setFitHeight(200);
            faction.setFitWidth(120);
            URL factionImageUrl = getClass().getResource("/IMAGES/Faction" + (i + 1) + ".jpg");
            faction.setImage(new Image(Objects.requireNonNull(factionImageUrl).toExternalForm()));
            factions.getChildren().add(faction);
            int finalI = i;
            faction.setOnMouseClicked(mouseEvent -> setUserFaction(String.valueOf(finalI)));
        }
        Stage stage = App.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void setUserFaction(String number) {
        switch (number) {
            case "0" -> User.getCurrentUser().setFaction("monsters");
            case "1" -> User.getCurrentUser().setFaction("nilfgaard");
            case "2" -> User.getCurrentUser().setFaction("skellige");
            case "3" -> User.getCurrentUser().setFaction("scoiatael");
            case "4" -> User.getCurrentUser().setFaction("realms");
        }
    }

    private void showFactions() {
        BorderPane pane = new BorderPane();
        HBox factions = new HBox();
        factions.setSpacing(100);
        factions.setAlignment(Pos.CENTER);
        pane.setCenter(factions);
        pane.setId("pane");
        pane.setBackground(null);

        Button back = new Button("Back");
        pane.setLeft(back);
        back.setOnAction(actionEvent -> App.getStage().setScene(App.getPreGameMenu()));

        Label cardDescription = new Label();
        Insets insets = new Insets(10);

        HBox text = new HBox();
        text.getChildren().add(cardDescription);
        text.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        scene.setFill(new Color(0.90, 0.90, 0.90, 0.1));

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/1.css")).toExternalForm());
//        factions.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        pane.setBottom(text);
        BorderPane.setMargin(text, insets);
        cardDescription.setAlignment(Pos.CENTER);
        for (int i = 0; i < 5; i++) {
            ImageView faction = new ImageView();
            faction.setFitHeight(200);
            faction.setFitWidth(120);
            URL factionImageUrl = getClass().getResource("/IMAGES/Faction" + (i + 1) + ".jpg");
            faction.setImage(new Image(Objects.requireNonNull(factionImageUrl).toExternalForm()));
            faction.setId("faction");
            int finalI = i;
            faction.setOnMouseClicked(mouseEvent -> {
                FactionCardAnimation animation = null;
                boolean valid = false;
                if (faction.getFitHeight() == 200 && checkOtherFactionCards(factions, cardDescription)) {
                    animation = new FactionCardAnimation(faction, 200, 120, false);
                    checkOtherFactionCards(factions, cardDescription);
                    valid = true;
                } else if (faction.getFitHeight() == 300) {
                    animation = new FactionCardAnimation(faction, 300, 180, true);
                    valid = true;
                }
                if (valid) {
                    animation.play();
                    writeFactionInfo(finalI, cardDescription);
                }
//                cardDescription.setText("some text here");

                cardDescription.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 30));
                cardDescription.setTextFill(Color.color(0.9, 0.9, 0.9));
            });
            factions.getChildren().add(faction);
        }
//        Stage stage = new Stage();
        Stage stage = App.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void writeFactionInfo(int i, Label cardDescription) {
        switch (i) {
            case 0:
                cardDescription.setText("monsters");
                break;
            case 1:
                cardDescription.setText("nilfgaard");
                break;
            case 2:
                cardDescription.setText("skellige");
                break;
            case 3:
                cardDescription.setText("scoiatael");
                break;
            case 4:
                cardDescription.setText("realms");
                break;
        }
    }

    private boolean checkOtherFactionCards(HBox factions, Label description) {
        int counter = 0;
        for (Node child : factions.getChildren()) {
            if (((ImageView) child).getFitHeight() == 200) {
                counter++;
            } else {
                if (((ImageView) child).getFitHeight() == 300) {
                    FactionCardAnimation animation = new FactionCardAnimation(((ImageView) child), 300, 180, true);
                    animation.play();
                    description.setText("");
                }
            }
        }
        return counter == 5;
    }

    private void startNewGame() {
        if (User.getCurrentUser().getDeck().getNumberOfCardsInDeck() < 22) {//TODO check for 10 special cards
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Less than 22 cards in your deck.");
            alert.show();
        } else if (User.getCurrentUser().getDeck().getCurrentLeaderCard() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The leader card hasn't been chosen yet.");
            alert.show();
        } else {
            GameMenuGraphic gameMenuGraphic = new GameMenuGraphic();
            GameController gameController = (GameController) Menu.GameMenu.getMenuController();
            gameController.setStartStatus(User.getCurrentUser());
            try {
                gameMenuGraphic.start(null);
            } catch (Exception e) {
                App.LOGGER.log(Level.SEVERE, "Can't start the game", e);
            }
        }
    }
}
