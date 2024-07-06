package view;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.App;
import model.GsonReaderWriter;
import model.User;
import view.game.graphics.LeaderBoardGraphic;
import view.game.graphics.PreGameMenuGraphic;

import java.util.ArrayList;
import java.util.logging.Level;

public class MainMenuGraphic extends Application {
    @Override
    public void start(Stage stage) {
        PreGameMenuGraphic preGameMenu = new PreGameMenuGraphic();
        BorderPane pane = new BorderPane();
        Button PreGameButton = new Button("PreGame");
        PreGameButton.setMinWidth(70);
        PreGameButton.setMinHeight(45);
        Button ProfileButton = new Button("Profile");
        ProfileButton.setMinHeight(45);
        PreGameButton.setMinWidth(70);
        Button LeaderBoard = new Button("Leader Board");
        LeaderBoard.setMinHeight(45);
        LeaderBoard.setMinWidth(70);
        Button logout = new Button("log out");
        logout.setMinWidth(70);
        logout.setMinHeight(45);
        logout.setOnAction(actionEvent -> {
            User.setCurrentUser(null);
            stage.setScene(App.getLoginMenu());
            stage.show();
        });
        PreGameButton.setOnAction(actionEvent -> {
            try {
                preGameMenu.start(stage);
            } catch (Exception e) {
                App.LOGGER.log(Level.SEVERE, "Can't enter the pre-game menu!", e);
            }
        });
        ShowProfileMenu profileMenu = new ShowProfileMenu();
        ProfileButton.setOnAction(actionEvent -> {
            try {
                profileMenu.start(stage);
            } catch (Exception e) {
                App.LOGGER.log(Level.SEVERE, "Can't enter the profile menu!", e);
            }
        });
        LeaderBoard.setOnAction(actionEvent -> {
            ArrayList<User> users = new ArrayList<>();//TODO get from server
            users.add(GsonReaderWriter.getGsonReaderWriter().loadUser("Amin1"));
            users.add(GsonReaderWriter.getGsonReaderWriter().loadUser("Nima1"));
            LeaderBoardGraphic leaderBoardGraphic = new LeaderBoardGraphic(users);
            try {
                leaderBoardGraphic.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        HBox box = new HBox();
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(ProfileButton, PreGameButton, LeaderBoard, logout);
        pane.setCenter(box);
        pane.setId("pane");
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/CSS/MainMenuStyle.css").toExternalForm());
        App.setMainMenu(scene);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(800);
        stage.setMinWidth(1200);
        stage.centerOnScreen();
    }
}
