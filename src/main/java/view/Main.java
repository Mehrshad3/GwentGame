package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.App;

import java.util.logging.Level;

public class Main extends Application {
    public static final Level loggingLevel = Level.FINER;
    public ShowingLoginMenu menu;

    public static void main(String[] args) {
        App.LOGGER.setLevel(loggingLevel);
        runTerminal();
        launch(args);
    }

    private static void runTerminal() {
        Thread terminal = new Thread(AppView.getAppViewObject());
        terminal.setDaemon(true);
        terminal.start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        menu = new ShowingLoginMenu();
//        stage.setResizable(false);
//        stage.setHeight(500);
//        stage.setWidth(1000);
//        stage.setMinHeight(500);
//        stage.setMinWidth(500);
        menu.start(stage);
        App.setStage(stage);
    }
}
