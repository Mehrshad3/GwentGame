package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;

public class PreGameMenuGraphic extends Application {
    private HBox topButtons;
    private HBox bottomButtons;
    private HBox middleButtons;
    private VBox buttons;
    @Override
    public void start(Stage stage) throws Exception {

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
    }

    private void setButtons() {
        Button StartNewGame = new Button("New Game");
        StartNewGame.setOnMouseClicked(mouseEvent -> startNewGame());
        topButtons.getChildren().add(StartNewGame);

        Button ShowFraction = new Button("Fractions");
        ShowFraction.setOnMouseClicked(mouseEvent -> showFactions());
        topButtons.getChildren().add(ShowFraction);

        Button ChangeFraction = new Button("Change Fraction");
        ChangeFraction.setOnMouseClicked(mouseEvent -> changeUserFraction());
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

        middleButtons.getChildren().addAll(ShowLeaders,SelectLeader);

        for (Node child : topButtons.getChildren()) {
            ((Button)child).setMinWidth(60);
            ((Button)child).setMinHeight(40);
        }
        for (Node child : bottomButtons.getChildren()) {
            ((Button)child).setMinWidth(60);
            ((Button)child).setMinHeight(40);
        }
    }

    private void selectLeader() {

    }

    private void showLeaders() {

    }

    private void loadDeckByName() {

    }

    private void loadDeckFromFile() {

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

    }

    private void showDeck() {

    }

    private void showCards() {

    }

    private void changeUserFraction() {

    }

    private void showFactions() {
        BorderPane pane = new BorderPane();
        HBox factions = new HBox();
        factions.setSpacing(50);
        factions.setAlignment(Pos.CENTER);
        pane.setCenter(factions);
        pane.setId("pane");
        pane.setBackground(null);

        Scene scene = new Scene(pane);
        scene.setFill(new Color(0.90,0.90,0.90,0.1));
        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        factions.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        for (int i = 0; i < 5; i++) {
            ImageView faction = new ImageView();
            faction.setFitHeight(200);
            faction.setFitWidth(120);
            faction.setImage(new Image(getClass().getResource("/IMAGES/Faction" + (i + 1) + ".jpg").toExternalForm()));
            faction.setId("faction");

            factions.getChildren().add(faction);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void startNewGame() {

    }
}
