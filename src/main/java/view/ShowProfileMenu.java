package view;

import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;

import java.util.Optional;

public class ShowProfileMenu extends Application {
    private Stage stage;
    private HBox info;//VBox
    private HBox changingButtons;
    private Label Username;
    private Label Email;
    private Label NickName;
    private Label Password;
    private ProfileMenuController profileMenuController;
    @Override
    public void start(Stage stage) throws Exception {
        User user = new User("nima","123","nima@gmail.com","n.ooo","","");
        User.setCurrentUser(user);
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
//        pane.setLeft(info);
        pane.setTop(info);
        pane.setBottom(changingButtons);
        setInfoAndButtonsNodes();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.show();
    }

    private void setInfoAndButtonsNodes(){
        Username = new Label("Username: " + User.getCurrentUser().getName());
        Username.setMinWidth(User.getCurrentUser().getName().length()*40);
        Username.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC,20));
        info.getChildren().add(Username);

        NickName = new Label("Nickname: " + User.getCurrentUser().getNickname());
        NickName.setMinWidth(User.getCurrentUser().getNickname().length()*40);
        NickName.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC,20));
        info.getChildren().add(NickName);

        Email = new Label("Email: " + User.getCurrentUser().getEmail());
        Email.setMinWidth(User.getCurrentUser().getEmail().length()*40);
        Email.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC,20));
        info.getChildren().add(Email);

        Button ChangeUsernameButton = new Button("Change Username");
        ChangeUsernameButton.setOnMouseClicked(mouseEvent -> changeUsername());
        changingButtons.getChildren().add(ChangeUsernameButton);

        Button ChangePasswordButton = new Button("Change Password");
        ChangePasswordButton.setOnMouseClicked(mouseEvent -> changePassword());
        changingButtons.getChildren().add(ChangePasswordButton);

        Button ChangeNickNameButton = new Button("Change Nickname");
        ChangeNickNameButton.setOnMouseClicked(mouseEvent -> changeNickname());
        changingButtons.getChildren().add(ChangeNickNameButton);

        Button ChangeEmailButton = new Button("Change Email");
        ChangeEmailButton.setOnMouseClicked(mouseEvent -> changeEmail());
        changingButtons.getChildren().add(ChangeEmailButton);

        Button UserInfo = new Button("Info");
        UserInfo.setOnMouseClicked(mouseEvent -> showUserInfo());
        changingButtons.getChildren().add(UserInfo);

        Button History = new Button("History");
        History.setOnMouseClicked(mouseEvent -> showGameHistory());
        changingButtons.getChildren().add(History);
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
        email.ifPresent(s -> profileMenuController.changeEmail(s,User.getCurrentUser().getEmail()));
    }

    private void changeNickname() {
        TextInputDialog newNickname = new TextInputDialog();
        newNickname.setHeaderText("Change Nickname");
        newNickname.setContentText("Enter your new Nickname");
        Optional<String> nickname = newNickname.showAndWait();
        nickname.ifPresent(s -> profileMenuController.changeNickname(s,User.getCurrentUser().getNickname()));
    }

    private void changePassword() {
        TextInputDialog newPassword = new TextInputDialog();
        newPassword.setContentText("Enter your new password");
        newPassword.setHeaderText("Change Password");
        Optional<String> password = newPassword.showAndWait();
        password.ifPresent(s -> profileMenuController.changePassword(s,User.getCurrentUser().getPassword()));
    }

    private void changeUsername() {
        TextInputDialog newUsername = new TextInputDialog();
        newUsername.setContentText("Enter your new Username");
        newUsername.setHeaderText("Change Username");
        Optional<String> username = newUsername.showAndWait();
        username.ifPresent(s -> profileMenuController.changeUsername(s));
    }
}
