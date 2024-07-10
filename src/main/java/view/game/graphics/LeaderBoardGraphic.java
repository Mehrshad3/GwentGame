package view.game.graphics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.App;
import model.SimpleUser;
import model.User;

import java.util.*;

public class LeaderBoardGraphic extends Application {
    boolean isUserInThisPage = false;


    ArrayList<SimpleUser> users;

    public LeaderBoardGraphic(ArrayList<SimpleUser> users) {
        this.users = users;
    }

    @Override
    public void start(Stage stage) throws Exception {
        isUserInThisPage = true;
        ShowLeaderBoard(stage);
    }

    private void ShowLeaderBoard(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(5);
//        gridPane.setBorder(new Border(new BorderImage(new Image(getClass().getResource("/IMAGES/back.jpeg").toExternalForm()),
//                new BorderWidths(100),new Insets(10),BorderWidths.FULL,false,BorderRepeat.REPEAT,BorderRepeat.SPACE)));
        setGridPane(gridPane);
        setHeader(gridPane);
        setUserInfo(gridPane);
        BorderPane pane = setBasePanes(gridPane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private BorderPane setBasePanes(GridPane gridPane) {
        BorderPane pane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(gridPane);
        pane.setCenter(scrollPane);
        return pane;
    }

    private void setGridPane(GridPane gridPane) {
        for (int i = 0; i < 3; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            gridPane.getColumnConstraints().add(columnConstraints);
            columnConstraints.setFillWidth(true);
        }

        RowConstraints rowConstraintsInfo = new RowConstraints();
        rowConstraintsInfo.setFillHeight(true);
        gridPane.getRowConstraints().add(rowConstraintsInfo);

        for (int i = 0; i < users.size() ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setFillHeight(true);
            gridPane.getRowConstraints().add(rowConstraints);
        }
    }

    private void setUserInfo(GridPane gridPane) {
        int counter = 2;
        sortUsers();
        for (SimpleUser user :users) {
            Label username = new Label(user.getUsername());
            Label wins = new Label(String.valueOf(user.getScore()));
            Label isOnline = new Label();
            if(user.isOnline()){
                isOnline.setText("online");
            }else {
                isOnline.setText("offline");
            }
            username.setAlignment(Pos.CENTER); wins.setAlignment(Pos.CENTER); isOnline.setAlignment(Pos.CENTER);
            gridPane.add(username,0,counter);
            gridPane.add(wins,1,counter);
            gridPane.add(isOnline,2,counter);
            counter++;
        }
    }

    private void setHeader(GridPane gridPane) {
        Label username = new Label("Username");
        username.setAlignment(Pos.CENTER);
        Label wins = new Label("wins");
        wins.setAlignment(Pos.CENTER);
        gridPane.add(username,0,1);
        gridPane.add(wins,1,1);
        setBackButton(gridPane);
    }

    private static void setBackButton(GridPane gridPane) {
        Button back =new Button("Back");
        gridPane.add(back,0,0);
        back.setOnAction(actionEvent -> {
            App.setLeaderBoardGraphic(null);
            Stage stage = App.getStage();
            stage.setScene(App.getMainMenu());
            stage.show();
        });
    }

    public void updateTable(ArrayList<SimpleUser> users){
        this.users = users;
        sortUsers();
        if(isUserInThisPage) {
            Stage stage = App.getStage();
            ShowLeaderBoard(stage);// to dar to nemishe?
        }
    }
    private void sortUsers(){
//        List<Map.Entry<SimpleUser,Double>> list = new LinkedList<>(users.entrySet());
        Collections.sort(users, new Comparator<SimpleUser>() {
            @Override
            public int compare(SimpleUser o1, SimpleUser o2) {
                return (int) (o1.getScore() - o2.getScore());
            }
        });
//        HashMap<SimpleUser,Double> sorted = new LinkedHashMap<>();
//        for (Map.Entry<SimpleUser,Double> entry:list){
//            sorted.put((SimpleUser) (entry.getKey()),entry.getValue());
//        }
//        this.users = sorted;
    }
}
