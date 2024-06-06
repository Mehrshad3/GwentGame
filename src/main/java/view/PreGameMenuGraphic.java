package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.App;
import model.Deck;
import model.GsonReaderWriter;
import model.User;
import model.faction.Card;
import model.faction.NonCommanderCardAbility;
import view.Animation.FactionCardAnimation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class PreGameMenuGraphic extends Application {
    private HBox topButtons;
    private HBox bottomButtons;
    private HBox middleButtons;
    private VBox buttons;
    @Override
    public void start(Stage stage) throws Exception {

        User.setCurrentUser(GsonReaderWriter.loadFromFile(User.getRelativePathToFile("Mehrshad"),User.class));
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

        buttons.getChildren().addAll(topButtons,bottomButtons,middleButtons);

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

        Button ChangeFraction = new Button("Change Faction");
        ChangeFraction.setOnMouseClicked(mouseEvent -> changeUserFaction());
        topButtons.getChildren().add(ChangeFraction);

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

        middleButtons.getChildren().addAll(ShowLeaders,SelectLeader,AddCardToDeck);

        for (Node child : topButtons.getChildren()) {
            ((Button)child).setMinWidth(60);
            ((Button)child).setMinHeight(40);
        }
        for (Node child : bottomButtons.getChildren()) {
            ((Button)child).setMinWidth(60);
            ((Button)child).setMinHeight(40);
        }
    }

    private void addCardToDeck() {
        TextInputDialog card = new TextInputDialog();
        card.setContentText("Enter Card Name");
        Optional<String> name = card.showAndWait();
        if(name.isPresent() && !name.get().equals("")){
            System.out.println(name.get());
            //TODO
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
        if(name.isPresent() && !name.get().equals("")){
            Deck deck = GsonReaderWriter.getGsonReaderWriter().loadDeckByName(name.get());
            Alert alert = new Alert(Alert.AlertType.NONE);
            if(deck == null){
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No such Deck");
                alert.show();
            }else {
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

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("All Files","*.*");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        System.out.println(file.getAbsolutePath());
    }

    private void saveDeckToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All Files", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
    }

    private void saveDeckByName() {

    }

    private void showInfo() {
        BorderPane pane = new BorderPane();
        Label info = new Label();
        VBox box = new VBox(info);
        pane.setCenter(box);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        info.setTextFill(Color.color(0,0,0));
        info.setText("""
                name:\s
                password:\s
                nickname:\s
                email:\s
                numberOfWins:\s
                numberOfLoses:\s
                faction:\s
                """);
        info.setFont(Font.font("Arial",FontWeight.LIGHT,FontPosture.ITALIC,20));
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
//        Stage stage1 = App.getStage();
//        stage1.close();
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
        cards.getChildren().addAll(topCards,middleCards,bottomCards);
        pane.setCenter(cards);

        pane.setId("pane");
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        int counter = 0;
        HBox hBox = topCards;
        for (Card card : User.getCurrentUser().getDeck().getInHandCards()) {
            if (card != null) {
                if (counter == 3){
                    hBox = middleCards;
                }
                if(counter == 7){
                    hBox = bottomCards;
                }
                ImageView cardImage = new ImageView();
                cardImage.setFitHeight(200);
                cardImage.setFitWidth(120);
                cardImage.setImage(new Image(getClass().getResource("/IMAGES/" + card.getName() + ".png").toExternalForm()));
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
        cards.getChildren().addAll(leftButton,card,rightButton);
        cards.setSpacing(50);
        cards.setAlignment(Pos.CENTER);
        pane.setCenter(cards);

        ArrayList<Image> images = new ArrayList<>();
        File file = new File(getClass().getResource("/IMAGES/monsters/").getFile());
        int[] counter = new int[1];
        for(File file1 : file.listFiles()){
            images.add(new Image(String.valueOf(file1)));
        }
        leftButton.setOnMouseClicked(mouseEvent -> {
            counter[0] = (counter[0] + images.size() - 1)%images.size();
            card.setImage(images.get(counter[0]));

        });
        rightButton.setOnMouseClicked(mouseEvent -> {
            counter[0] = (counter[0] + images.size() + 1)% images.size();
            card.setImage(images.get(counter[0]));

        });
        Scene scene = new Scene(pane);
        pane.setCenter(cards);
        card.setImage(images.get(counter[0]));
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
            faction.setImage(new Image(getClass().getResource("/IMAGES/Faction" + (i+1) + ".jpg").toExternalForm()));
            factions.getChildren().add(faction);
            int finalI = i;
            faction.setOnMouseClicked(mouseEvent -> setUserFaction(String.valueOf(finalI)));
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void setUserFaction(String number) {
        if(number.equals("0")){
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
        scene.setFill(new Color(0.90,0.90,0.90,0.1));
        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
//        factions.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        pane.setBottom(text);
        BorderPane.setMargin(text,insets);
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
                if(faction.getFitHeight() == 200 && checkOtherFactionCards(factions,cardDescription)) {
                    animation = new FactionCardAnimation(faction,200,120,false);
                    checkOtherFactionCards(factions,cardDescription);
                    valid = true;
                } else if (faction.getFitHeight() == 300) {
                    animation = new FactionCardAnimation(faction,300,180,true);
                    valid = true;
                }
                if(valid){
                    animation.play();
                    writeFactionInfo(finalI,cardDescription);
                }
//                cardDescription.setText("some text here");

                cardDescription.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC,30));
                cardDescription.setTextFill(Color.color(0.9,0.9,0.9));
            });
            factions.getChildren().add(faction);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void writeFactionInfo(int i,Label cardDescription) {
        switch (i){
            case 0: cardDescription.setText("monsters");
            break;
            case 1: cardDescription.setText("nilfgaard");
            break;
            case 2: cardDescription.setText("skellige");
            break;
            case 3: cardDescription.setText("scoiatael");
            break;
            case 4: cardDescription.setText("realms");
            break;
        }
    }

    private boolean checkOtherFactionCards(HBox factions,Label description){
        int counter = 0;
        for (Node child : factions.getChildren()) {
            if(((ImageView)child).getFitHeight() == 200){
                counter++;
            }else {
                if(((ImageView)child).getFitHeight() == 300) {
                    FactionCardAnimation animation = new FactionCardAnimation(((ImageView) child), 300, 180, true);
                    animation.play();
                    description.setText("");
                }
            }
        }
        return counter == 5;
    }

    private void startNewGame() {
        BorderPane pane = new BorderPane();
        pane.setId("pane");
        pane.getStylesheets().add(getClass().getResource("/CSS/GameTable.css").toExternalForm());
        Scene scene = new Scene(pane);
        Stage stage = App.getStage();

        stage.setScene(scene);
        App.getStage().close();
        stage.show();
        stage.setMinHeight(800);
        stage.setMinWidth(1200);
        stage.centerOnScreen();//TODO make background
//        stage.setFullScreen(true);
    }
}
