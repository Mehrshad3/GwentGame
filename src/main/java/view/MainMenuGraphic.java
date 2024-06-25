package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.App;

import java.util.logging.Level;

public class MainMenuGraphic extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        PreGameMenuGraphic preGameMenu = new PreGameMenuGraphic();
        BorderPane pane = new BorderPane();
        Button PreGameButton = new Button("PreGame");
        Button ProfileButton = new Button("Profile");
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
        HBox box = new HBox();
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(ProfileButton, PreGameButton);
        pane.setCenter(box);
        Scene scene = new Scene(pane);
        App.setMainMenu(scene);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(800);
        stage.setMinWidth(1200);
        stage.centerOnScreen();
    }
}
