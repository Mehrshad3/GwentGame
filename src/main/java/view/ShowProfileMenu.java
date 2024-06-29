package view;

import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.App;
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
    private Button back = new Button("Back");
    private ProfileMenuController profileMenuController;

    @Override
    public void start(Stage stage) throws Exception {

//        User user = User.create("nima", "123", "nima@gmail.com", "n.ooo", "", "");
//        User.setCurrentUser(user);

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

        Insets insets = new Insets(30);
        BorderPane.setMargin(changingButtons, insets);
        BorderPane.setMargin(info, insets);
        BorderPane.setMargin(back, new Insets(0));

        pane.setBottom(changingButtons);
        setInfoAndButtonsNodes();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.show();
    }

    private void setInfoAndButtonsNodes() {

        back.setMinWidth(40);
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
