package view;

import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.App;
import model.FriendRequest;
import model.GsonReaderWriter;
import model.User;

import java.util.Optional;

public class ShowProfileMenu extends Application {
    private Stage stage;
    private HBox info;//VBox
    private HBox changingButtons;
    private HBox centerButtons;
    private Label Username;
    private Label Email;
    private Label NickName;
    private Label Password;
    private Button back = new Button("Back");
    private ProfileMenuController profileMenuController;

    @Override
    public void start(Stage stage) throws Exception {

        profileMenuController = new ProfileMenuController();
        this.stage = stage;
        info = new HBox();
        info.setSpacing(20);
        info.setMaxWidth(300);
        info.setMaxHeight(300);
        changingButtons = new HBox();
        changingButtons.setSpacing(20);
        changingButtons.setAlignment(Pos.CENTER);
        BorderPane pane = new BorderPane();

        pane.setTop(info);
        setCenterButtons();
        pane.setCenter(centerButtons);

        Insets insets = new Insets(30);
        BorderPane.setMargin(changingButtons, insets);
        BorderPane.setMargin(info, insets);
        BorderPane.setMargin(back, new Insets(0));



        pane.setBottom(changingButtons);
        setInfoAndButtonsNodes();
        pane.setId("pane");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        scene.getStylesheets().add(getClass().getResource("/CSS/ProfileMenuStyle.css").toExternalForm());
        stage.show();
    }

    private void setInfoAndButtonsNodes() {

        back.setMinWidth(70);
        back.setOnAction(actionEvent -> App.getStage().setScene(App.getMainMenu()));
        info.getChildren().add(back);

        Username = new Label("Username: " + User.getCurrentUser().getName());
        Username.setMinWidth(User.getCurrentUser().getName().length() * 30);
        Username.setMaxWidth(150);
        Username.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 17));
        info.getChildren().add(Username);

        NickName = new Label("Nickname: " + User.getCurrentUser().getNickname());
        NickName.setMinWidth(User.getCurrentUser().getNickname().length() * 30);
        NickName.setMaxWidth(150);
        NickName.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 17));
        info.getChildren().add(NickName);

        Email = new Label("Email: " + User.getCurrentUser().getEmail());
        Email.setMinWidth(User.getCurrentUser().getEmail().length() * 30);
        Email.setMaxWidth(150);
        Email.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 17));
        info.getChildren().add(Email);

        Button ChangeUsernameButton = new Button("Change Username");
        ChangeUsernameButton.setOnAction(actionEvent -> changeUsername());
        changingButtons.getChildren().add(ChangeUsernameButton);

        Button ChangePasswordButton = new Button("Change Password");
        ChangePasswordButton.setOnAction(actionEvent -> changePassword());
        changingButtons.getChildren().add(ChangePasswordButton);

        Button ChangeNickNameButton = new Button("Change Nickname");
        ChangeNickNameButton.setOnAction(actionEvent -> changeNickname());
        changingButtons.getChildren().add(ChangeNickNameButton);

        Button ChangeEmailButton = new Button("Change Email");
        ChangeEmailButton.setOnAction(actionEvent -> changeEmail());
        changingButtons.getChildren().add(ChangeEmailButton);

        Button UserInfo = new Button("Info");
        UserInfo.setOnAction(actionEvent -> showUserInfo());
        changingButtons.getChildren().add(UserInfo);

        Button History = new Button("History");
        History.setOnAction(actionEvent -> showGameHistory());
        changingButtons.getChildren().add(History);
    }

    private void setCenterButtons(){
        centerButtons = new HBox();
        Button sendFriendRequest = new Button("send friend request");
        setSendFriendRequestButtonAction(sendFriendRequest);
        Button friendRequestHistory = new Button("friend request history");
        setFriendRequestHistoryAction(friendRequestHistory);
        centerButtons.getChildren().addAll(sendFriendRequest,friendRequestHistory);

        centerButtons.setAlignment(Pos.CENTER);
        centerButtons.setSpacing(20);
    }

    private void setSendFriendRequestButtonAction(Button sendFriendRequest){
        sendFriendRequest.setOnAction(mouseEvent -> {
            TextInputDialog nameBox = new TextInputDialog();
            nameBox.setContentText("enter username");
            Optional<String> username = nameBox.showAndWait();
            if(username.isPresent()){
                Alert alert = new Alert(Alert.AlertType.NONE);
                User user = GsonReaderWriter.getGsonReaderWriter().loadUser(username.get());
                if(user == null){
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("no such user");
                    alert.show();
                } else if (false) {
                    //TODO check if user is online
                } else if (false) {
                    //TODO check if player isnt playing
                }else {
//                    user.addFriendRequest(new FriendRequest());
                    //TODO send for server for pop-up and get result and set request property
                    System.out.println(javafx.scene.text.Font.getFamilies());
                }
            }
        });
    }

    private void setFriendRequestHistoryAction(Button friendRequestHistory){
        friendRequestHistory.setOnAction(mouseEvent -> {
            ScrollPane scrollPane = new ScrollPane();
            //TODO check if more needed
            GridPane pane = new GridPane();
            pane.setBackground(Background.fill(Color.GAINSBORO));
            HBox buttonBox = new HBox();
            Button exit = new Button("Exit");
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.getChildren().add(exit);
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(scrollPane);
            scrollPane.setContent(pane);
            borderPane.setBottom(buttonBox);

            for (int i = 0; i < 2; i++) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                pane.getColumnConstraints().add(columnConstraints);
                columnConstraints.setFillWidth(true);
            }

            for (int i = 0; i < User.getCurrentUser().getFriendRequests().size(); i++) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setFillHeight(true);
                pane.getRowConstraints().add(rowConstraints);
            }

            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            pane.setHgap(30);
            pane.setVgap(10);
            pane.setAlignment(Pos.CENTER);
            pane.setPadding(new Insets(20,20,20,20));
            pane.setId("pane");

            Scene scene = new Scene(borderPane,500,300);
            scene.getStylesheets().add(getClass().getResource("/CSS/FriendRequestBackground.css").toExternalForm());
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.initModality(Modality.APPLICATION_MODAL);

            exit.setOnAction(mouseEvent1 -> stage1.close());
            int counter = 0;
            for (FriendRequest request : User.getCurrentUser().getFriendRequests()) {
                Label username = new Label(request.getUsername());username.setFont(new Font("Arial",20));
                username.setAlignment(Pos.CENTER);
                username.setStyle("-fx-border-color: black;" +
                        "-fx-border-width: 0.5px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-padding: 10px");
                pane.add(username,0,counter);
                Label response = new Label(request.getResponse());
                response.setAlignment(Pos.CENTER);
                response.setFont(new Font("Garamond",20));
                pane.add(response,1,counter);
                counter++;
            }
            stage1.show();
        });
    }

    private void showGameHistory() {

    }

    private void showUserInfo() {

    }

    private void changeEmail() {
        TextInputDialog newEmail = new TextInputDialog();
        newEmail.setHeaderText("Change Email");
        newEmail.setContentText("Enter your new Email");
        Optional<String> email = newEmail.showAndWait();
        email.ifPresent(s -> profileMenuController.changeEmail(s, User.getCurrentUser().getEmail()));
    }

    private void changeNickname() {
        TextInputDialog newNickname = new TextInputDialog();
        newNickname.setHeaderText("Change Nickname");
        newNickname.setContentText("Enter your new Nickname");
        Optional<String> nickname = newNickname.showAndWait();
        nickname.ifPresent(s -> profileMenuController.changeNickname(s, User.getCurrentUser().getNickname()));
    }

    private void changePassword() {
        TextInputDialog newPassword = new TextInputDialog();
        newPassword.setContentText("Enter your new password");
        newPassword.setHeaderText("Change Password");
        Optional<String> password = newPassword.showAndWait();
        password.ifPresent(s -> profileMenuController.changePassword(s, User.getCurrentUser().getPassword()));
    }

    private void changeUsername() {
        TextInputDialog newUsername = new TextInputDialog();
        newUsername.setContentText("Enter your new Username");
        newUsername.setHeaderText("Change Username");
        Optional<String> username = newUsername.showAndWait();
        username.ifPresent(s -> profileMenuController.changeUsername(s));
    }
}
