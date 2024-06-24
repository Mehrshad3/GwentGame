package view;

import enums.card.CardName;
import controller.GameController;
import enums.Menu;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
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
import model.*;
import model.faction.Card;
//import model.faction.NonCommanderCardAbility;
import model.faction.Faction;
import view.Animation.FactionCardAnimation;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class PreGameMenuGraphic extends Application {
    private HBox topButtons;
    private HBox bottomButtons;
    private HBox middleButtons;
    private VBox buttons;

    @Override
    public void start(Stage stage) throws Exception {
//        User.setCurrentUser(GsonReaderWriter.getGsonReaderWriter().loadUser("Mehrshad"));
//        User.getCurrentUser().setFaction("monsters");
//        User.setCurrentUser(GsonReaderWriter.loadFromFile(User.getRelativePathToFile("Mehrshad"),User.class));
//        User.getCurrentUser().getDeck().addCardToHand(new Card("man",false, NonCommanderCardAbility.SPY,10));
//        User.getCurrentUser().getDeck().addCardToHand(new Card("mon",false,NonCommanderCardAbility.SPY,10));
//        User.getCurrentUser().getDeck().addCardToHand(new Card("min",false,NonCommanderCardAbility.SPY,10));
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
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(800);
        stage.setMinWidth(1200);
        stage.centerOnScreen();
    }

    private void setButtons() {
        Button StartNewGame = new Button("New Game");
        StartNewGame.setOnMouseClicked(mouseEvent -> startNewGame());
        topButtons.getChildren().add(StartNewGame);

        Button ShowFraction = new Button("Factions");
        ShowFraction.setOnMouseClicked(mouseEvent -> showFactions());
        topButtons.getChildren().add(ShowFraction);

        Button ChangeFaction = new Button("Change Faction");
        ChangeFaction.setOnMouseClicked(mouseEvent -> changeUserFaction());
        topButtons.getChildren().add(ChangeFaction);

        Button ShowCards = new Button("Show Cards");
        ShowCards.setOnMouseClicked(mouseEvent -> showCards());
        topButtons.getChildren().add(ShowCards);

        Button ShowDeck = new Button("Show Deck");
        ShowDeck.setOnMouseClicked(mouseEvent -> showDeck());
        topButtons.getChildren().add(ShowDeck);

        Button ShowInfo = new Button("Show Info");
        ShowInfo.setOnMouseClicked(mouseEvent -> showInfo());
        topButtons.getChildren().add(ShowInfo);

        Button SaveDeckByName = new Button("Save Deck By Name");
        SaveDeckByName.setOnMouseClicked(mouseEvent -> saveDeckByName());
        bottomButtons.getChildren().add(SaveDeckByName);

        Button SaveDeckToFile = new Button("Save To File");
        SaveDeckToFile.setOnMouseClicked(mouseEvent -> saveDeckToFile());
        bottomButtons.getChildren().add(SaveDeckToFile);

        Button LoadDeckFromFile = new Button("Load Deck From File");
        LoadDeckFromFile.setOnMouseClicked(mouseEvent -> loadDeckFromFile());
        bottomButtons.getChildren().add(LoadDeckFromFile);

//        ImageView imageView = new ImageView(new Image(getClass().getResource("/IMAGES/1.png").toExternalForm()));
//        bottomButtons.getChildren().add(imageView);
//        imageView.setOnMouseClicked(mouseEvent -> System.out.println("hi"));

        Button LoadDeckByName = new Button("Load Deck By Name");
        LoadDeckByName.setOnMouseClicked(mouseEvent -> loadDeckByName());
        bottomButtons.getChildren().add(LoadDeckByName);

        Button ShowLeaders = new Button("Leaders");
        ShowLeaders.setOnMouseClicked(mouseEvent -> showLeaders());

        Button SelectLeader = new Button("Select Leader");
        SelectLeader.setOnMouseClicked(mouseEvent -> selectLeader());

        Button AddCardToDeck = new Button("Add To Deck");
        AddCardToDeck.setOnMouseClicked(mouseEvent -> addCardToDeck());

        Button DeleteCardFromDeck = new Button("Delete From Deck");
        DeleteCardFromDeck.setOnMouseClicked(mouseEvent -> deleteFromDeck());

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
            for (Card inHandCard : User.getCurrentUser().getDeck().getInHandCards()) {
                if (inHandCard.getName().equals(name.get())) {
                    contains = true;
                }
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

    private void addCardToDeck() {
        TextInputDialog card = new TextInputDialog();
        card.setContentText("Enter Card Name");
        Optional<String> name = card.showAndWait();
        if (name.isPresent() && !name.get().equals("")) {
//            System.out.println(name.get());
            //TODO check if it has 4 same card
            Card newCard = CardName.getCardByName(name.get());
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (newCard == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No such card");
                alert.show();
            } else if (false) {
                //TODO if faction is right

            } else if (User.getCurrentUser().getDeck().getInHandCards().size() == 12) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Your hand in already full");
                alert.show();
            } else {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Card " + name.get() + " Added to Your deck");
                alert.show();
                User.getCurrentUser().getDeck().addCardToHand(newCard);
            }
        }
    }

    private void selectLeader() {

    }

    private void showLeaders() {

    }

    private void loadDeckByName() {
        TextInputDialog nameInput = new TextInputDialog();
        nameInput.setContentText("Enter deck Name");
        nameInput.setHeaderText("Load deck");
        Optional<String> name = nameInput.showAndWait();
        if (name.isPresent() && !name.get().equals("")) {
            Deck deck = GsonReaderWriter.getGsonReaderWriter().loadDeckByName(name.get());
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (deck == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No such Deck");
                alert.show();
            } else {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Deck Loaded");
                alert.show();
                User.getCurrentUser().getDeck().setInHandCards(deck.getInHandCards());
                User.getCurrentUser().getDeck().setCurrentLeaderCard(deck.getCurrentLeaderCard());
                User.getCurrentUser().getDeck().setDiscardCards(deck.getDiscardCards());
                User.getCurrentUser().getDeck().setLeaderCards(deck.getLeaderCards());
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
            System.out.println(file.getAbsolutePath());//TODO
        }
    }

    private void saveDeckToFile() {
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
        if (name.isPresent() && !name.get().equals("")) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (GsonReaderWriter.getGsonReaderWriter().loadDeckByName(name.get()) != null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("A deck already exists with this name");
                alert.show();
            } else {
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
        pane.setCenter(box);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        info.setTextFill(Color.color(0, 0, 0));
        info.setText("name: " + User.getCurrentUser().getName() + "\n\nnickname: " + User.getCurrentUser().getNickname() +
                "\n\nnumberOfWins: " + User.getCurrentUser().getWins() + "\n\nnumberOfLoses: " + User.getCurrentUser().getNumberOfLoses() +
                "\n\nfaction: " + User.getCurrentUser().getFaction() + "\n\nnumberOfCardsInDeck: " + User.getCurrentUser().getDeck().getInHandCards().size() +
                "\n\nnumberOfSoldiers: " + User.getCurrentUser().getNumberOfSoldiers() + "\n\nnumberOfSpecialCards: " +
                "\n\nnumberOfHeroCards: " + "" + "\n\nallCardsPower: " + SumOfCardPowers());
        info.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
//        Stage stage1 = App.getStage();
//        stage1.close();
    }

    private String SumOfCardPowers() {
        int power = 0;
        for (Card card : User.getCurrentUser().getDeck().getInHandCards()) {
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

        pane.setId("pane");
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        int counter = 0;
        HBox hBox = topCards;
        System.out.println(User.getCurrentUser().getDeck().getInHandCards().get(0).getName());
        for (Card card : User.getCurrentUser().getDeck().getInHandCards()) {
            if (card != null) {
                if (counter == 3) {
                    hBox = middleCards;
                }
                if (counter == 7) {
                    hBox = bottomCards;
                }
                ImageView cardImage = new ImageView();
                cardImage.setFitHeight(200);
                cardImage.setFitWidth(120);
                cardImage.setImage(new Image(getClass().getResource("/IMAGES/" + User.getCurrentUser().getFaction() + "/" + User.getCurrentUser().getFaction() + "_" + card.getName().toLowerCase() + ".jpg").toExternalForm()));//ToDO make all images jpg
                hBox.getChildren().add(cardImage);
                counter++;
            }
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void showCards() {
        BorderPane pane = new BorderPane();
        HBox cards = new HBox();

        Button leftButton = new Button("Previous");
        Button rightButton = new Button("Next");
        ImageView card = new ImageView();
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

        Rectangle clip = new Rectangle(330, 610);
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
        leftButton.setOnMouseClicked(mouseEvent -> {
            counter[0] = (counter[0] + images.size() - 1) % images.size();
            card.setImage(images.get(counter[0]));
            WritableImage image = card.snapshot(parameters, null);
            card.setImage(image);
//            pattern.set(new ImagePattern(images.get(counter[0])));
//            rectangle.setFill(pattern.get());

        });
        rightButton.setOnMouseClicked(mouseEvent -> {
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
        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        pane.setId("black");
        pane.setCenter(cards);
        card.setImage(images.get(counter[0]));
        WritableImage image = card.snapshot(parameters, null);
        card.setImage(image);
//        pattern.set(new ImagePattern(images.get(counter[0])));
//        rectangle.setFill(pattern.get());
        Stage stage = new Stage();
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

        Scene scene = new Scene(pane);

        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        for (int i = 0; i < 5; i++) {
            ImageView faction = new ImageView();
            faction.setFitHeight(200);
            faction.setFitWidth(120);
            faction.setImage(new Image(getClass().getResource("/IMAGES/Faction" + (i + 1) + ".jpg").toExternalForm()));
            factions.getChildren().add(faction);
            int finalI = i;
            faction.setOnMouseClicked(mouseEvent -> setUserFaction(String.valueOf(finalI)));
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void setUserFaction(String number) {
        if (number.equals("0")) {
            User.getCurrentUser().setFaction("monsters");
        } else if (number.equals("1")) {
            User.getCurrentUser().setFaction("nilfgaard");
        } else if (number.equals("2")) {
            User.getCurrentUser().setFaction("skellige");
        } else if (number.equals("3")) {
            User.getCurrentUser().setFaction("scoiatael");
        } else if (number.equals("4")) {
            User.getCurrentUser().setFaction("realms");
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

        Label cardDescription = new Label();
        Insets insets = new Insets(10);

        HBox text = new HBox();
        text.getChildren().add(cardDescription);
        text.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        scene.setFill(new Color(0.90, 0.90, 0.90, 0.1));

        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
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
        Stage stage = new Stage();
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
        GameMenuGraphic gameMenuGraphic = new GameMenuGraphic();
        GameController gameController = (GameController) Menu.GameMenu.getMenuController();
        gameController.setStartStatus(User.getCurrentUser());
        try {
            gameMenuGraphic.start(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
