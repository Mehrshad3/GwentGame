package view;

import controller.AppController;
import enums.Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.App;

import java.net.URL;
import java.util.Objects;

public class GameMenuGraphic extends Application {
    private GameGraphicController graphicController;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("/FXML/GameMenu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(url));
        BorderPane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        stage = App.getStage();

        stage.setScene(scene);
        App.getStage().close();
        stage.show();
        stage.centerOnScreen();

        graphicController = fxmlLoader.getController();
        graphicController.initializeWithStage(stage);
        AppController.getAppController().enterMenu(Menu.GameMenu);
    }
}
