package view;

import controller.GameController;
import enums.Menu;
import enums.card.CardName;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private VBox buttons;

    @Override
    public void start(Stage stage) {
//        User.setCurrentUser(GsonReaderWriter.getGsonReaderWriter().loadUser("Mehrshad"));
//        User.getCurrentUser().setFaction("monsters");
//        User.setCurrentUser(GsonReaderWriter.loadFromFile(User.getRelativePathToFile("Mehrshad"),User.class));
//        User.getCurrentUser().getDeck().addCardToDeck(new Card("man",false, NonCommanderCardAbility.SPY,10));
//        User.getCurrentUser().getDeck().addCardToDeck(new Card("mon",false,NonCommanderCardAbility.SPY,10));
//        User.getCurrentUser().getDeck().addCardToDeck(new Card("min",false,NonCommanderCardAbility.SPY,10));
        buttons = new VBox();
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
        scene.setFill(new ImagePattern(new Image(getClass().getResource("/IMAGES/back.jpeg").toExternalForm())));
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
        ShowCards.setOnAction(actionEvent -> showCards());
        topButtons.getChildren().add(ShowCards);

        Button ShowDeck = new Button("Show Deck");
        ShowDeck.setOnAction(actionEvent -> showDeck());
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
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
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
            //TODO check if it has 4 same card
            int counter = 0;
            for (Card cardInDeck : User.getCurrentUser().getDeck().getCardsInDeck()) {
                if (cardInDeck.getName().equalsIgnoreCase(name.get())) {
                    counter++;
                }
            }
            if (counter == 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("maximum number of " + name.get() + " in deck");
                alert.show();
            } else {
                Card newCard = CardName.getCardByName(name.get());
                Alert alert = new Alert(Alert.AlertType.NONE);
                if (newCard == null) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("No such card");
                    alert.show();
                } else if (false) {
                    //TODO if faction is right

                } else if (User.getCurrentUser().getDeck().getCardsInDeck().size() == 12 && false) { // Deck has to have at least 22 cards.
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Your deck in already full");
                    alert.show();
                } else {
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Card " + name.get() + " Added to Your deck");
                    alert.show();
                    User.getCurrentUser().getDeck().addCardToDeck(newCard);
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
            if (CardName.getCardByName(leader.get()) == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No such Leader");
                alert.show();
            } else {
                User.getCurrentUser().getDeck().setCurrentLeaderCard(CardName.getCardByName(leader.get()));
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Leader selected");
                alert.show();
            }
        }
    }

    private void showLeaders() {
        BorderPane pane = new BorderPane();
        HBox cards = new HBox();

        Button leftButton = new Button("Previous");
        Button rightButton = new Button("Next");
        ImageView card = new ImageView();

        Button back = new Button("Back");
        back.setOnAction(actionEvent -> App.getStage().setScene(App.getPreGameMenu()));
        pane.setLeft(back);

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

        card.setClip(clip);
        ArrayList<Image> images = new ArrayList<>();

        URL leadersDirectoryURL = Objects.requireNonNull(getClass().getResource("/IMAGES/leaders/" + User.getCurrentUser().getFaction().getName() + "/"));
        String DirectoryPath = URLDecoder.decode(leadersDirectoryURL.getFile(), StandardCharsets.UTF_8);
        File file = new File(DirectoryPath);
        int[] counter = new int[1];
        for (File file1 : Objects.requireNonNull(file.listFiles())) {
            images.add(new Image(String.valueOf(file1)));
        }
        leftButton.setOnAction(actionEvent -> {
            counter[0] = (counter[0] + images.size() - 1) % images.size();
            card.setImage(images.get(counter[0]));
            WritableImage image = card.snapshot(parameters, null);
            card.setImage(image);
        });
        rightButton.setOnAction(actionEvent -> {
            counter[0] = (counter[0] + images.size() + 1) % images.size();
            card.setImage(images.get(counter[0]));
            WritableImage image = card.snapshot(parameters, null);
            card.setImage(image);
        });
        Scene scene = new Scene(pane);
        pane.setCenter(cards);
        card.setImage(images.get(counter[0]));
        WritableImage image = card.snapshot(parameters, null);
        card.setImage(image);

        Stage stage = App.getStage();
        stage.setScene(scene);
        stage.show();
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
                System.out.println(User.getCurrentUser().getDeck().getCardsInDeck());
                GsonReaderWriter.getGsonReaderWriter().saveDeckByName(User.getCurrentUser().getDeck(), name.get());
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
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
                String CardName = card.getName().replaceAll(" ", "_");
                cardImage.setImage(new Image(getClass().getResource("/IMAGES/" + User.getCurrentUser().getFaction() + "/" + User.getCurrentUser().getFaction() + "_" + CardName + ".jpg").toExternalForm()));//ToDO make all images jpg
                hBox.getChildren().add(cardImage);
                counter++;
            }
        }
        Stage stage = App.getStage();
        stage.setScene(scene);
        stage.show();
    }

    private void showCards() {
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

        URL monstersDirectoryURL = Objects.requireNonNull(getClass().getResource("/IMAGES/" + User.getCurrentUser().getFaction().getName() + "/"));
        String monstersDirectoryPath = URLDecoder.decode(monstersDirectoryURL.getFile(), StandardCharsets.UTF_8);
        File file = new File(monstersDirectoryPath);
        int[] counter = new int[1];
        for (File file1 : Objects.requireNonNull(file.listFiles())) {
            images.add(new Image(String.valueOf(file1)));
        }
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
            faction.setImage(new Image(getClass().getResource("/IMAGES/Faction" + (i + 1) + ".jpg").toExternalForm()));
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
            faction.setImage(new Image(getClass().getResource("/IMAGES/Faction" + (i + 1) + ".jpg").toExternalForm()));
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
        if (User.getCurrentUser().getDeck().getNumberOfCardsInDeck() != 22) {//TODO check for 10 special cards
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Less than 22 cards in your deck.");
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
