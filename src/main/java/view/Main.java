package view;

import javafx.application.Application;
import javafx.stage.Stage;
import view.AppView;

public class Main extends Application {
    public ShowingLoginMenu menu;
    public static void main(String[] args) {
        launch(args);
//        AppView.getAppViewObject().run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        menu=new ShowingLoginMenu();
//        stage.setResizable(false);
//        stage.setHeight(500);
//        stage.setWidth(1000);
//        stage.setMinHeight(800);
//        stage.setMinWidth(1400);
        menu.start(stage);
    }
}
