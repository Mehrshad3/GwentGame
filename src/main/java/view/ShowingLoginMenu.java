package view;

import controller.LoginMenuController;
import controller.ProfileMenuController;
import controller.RegisterMenuController;
import controller.Validator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.App;
import model.GsonReaderWriter;
import model.User;

import java.util.Optional;


public class ShowingLoginMenu extends Application {
    public LoginMenu loginmenu;
    public TextField Username;
    public TextField Password;
    public TextField RepeatPassword;
    public TextField Email;
    public TextField Nickname;
    private Stage stage;
    private BorderPane pane;
    private VBox vBox;
    private LoginMenuController loginMenuController;
    private RegisterMenuController registerMenuController;
    private Validator validator;
    private Label Instruction;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxWidth(200);
        vBox.setMaxHeight(300);

        Instruction = new Label();
        Instruction.setText("""
                 1. For login just enter Username and Password
                                \s
                 2. For Random Password Enter Random at Password And RepeatPassword
                \s""");
        Instruction.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 15));
        Instruction.setTextFill(Color.GAINSBORO);

        loginmenu = new LoginMenu();
        registerMenuController = new RegisterMenuController();
        loginMenuController = new LoginMenuController();
        validator = Validator.getValidator();
        pane = new BorderPane();
        pane.setCenter(vBox);
        pane.setBottom(Instruction);
        pane.setId("pane");

//        vBox.setAlignment(Pos.CENTER);
        addPaneChildren();
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("/CSS/LoginStyle.css").toExternalForm());
//        scene.setCursor(new ImageCursor(new Image(getClass().getResource("/IMAGES/cursor.png").toExternalForm())));
        App.setLoginMenu(scene);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMinHeight(500);
        stage.setMinWidth(300);
        stage.show();
    }

    private void addPaneChildren() {
        Username = new TextField();
        Username.setPromptText("Username");
        Username.setLayoutX(stage.getWidth() / 2 - 100);
        Username.setLayoutY(stage.getHeight() / 2);
        vBox.getChildren().add(Username);

        Password = new TextField();//TODO passwordField
        Password.setPromptText("Password");
        Password.setLayoutX(stage.getWidth() / 2 - 100);
        Password.setLayoutY(stage.getHeight() / 2 + 40);
        vBox.getChildren().add(Password);

        RepeatPassword = new TextField();
        RepeatPassword.setPromptText("Repeat Password");
        RepeatPassword.setLayoutX(stage.getHeight() / 2 - 100);
        RepeatPassword.setLayoutY(stage.getHeight() / 2 + 60);
        vBox.getChildren().add(RepeatPassword);

        Email = new TextField();
        Email.setPromptText("Email");
        Email.setLayoutX(stage.getWidth() / 2 - 100);
        Email.setLayoutY(stage.getHeight() / 2 + 60);
        vBox.getChildren().add(Email);

        Nickname = new TextField();
        Nickname.setPromptText("Nickname");
        Nickname.setLayoutX(stage.getWidth() / 2 - 100);
        Nickname.setLayoutY(stage.getHeight() / 2 + 80);
        vBox.getChildren().add(Nickname);

        Button LoginButton = new Button("Login");
        LoginButton.setOnAction(actionEvent -> login());
        LoginButton.setLayoutX(stage.getWidth() / 2 + 60);
        LoginButton.setLayoutY(stage.getHeight() / 2 + 20);
        LoginButton.setMinWidth(75);
        vBox.getChildren().add(LoginButton);

        Button RegisterButton = new Button("Register");
        RegisterButton.setOnAction(actionEvent -> register());
        RegisterButton.setLayoutX(stage.getWidth() / 2 - 100);
        RegisterButton.setLayoutY(stage.getHeight() + 40);
        RegisterButton.setMinWidth(75);
        vBox.getChildren().add(RegisterButton);

        Button ForgotPassword = new Button("Forgot Password");
        ForgotPassword.setOnAction(actionEvent -> changeForgottenPassword());
        ForgotPassword.setLayoutX(stage.getWidth() / 2 - 100);
        ForgotPassword.setLayoutY(stage.getHeight() + 60);
        ForgotPassword.setMinWidth(75);
        vBox.getChildren().add(ForgotPassword);
    }

    private void changeForgottenPassword() {
        TextInputDialog Username = new TextInputDialog();
        Username.setHeaderText("Username");
        Username.setContentText("Enter Your Username");
        Optional<String> username = Username.showAndWait();

        if (username.isPresent()) {
            if (!validator.isUsernameDuplicate(username.get())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("username doesn't exists");
                alert.show();
            } else {
                TextInputDialog answer = new TextInputDialog();
                answer.setTitle("Change Password");
                answer.setHeaderText("Security Question");
                answer.setContentText("Enter Your Security Answer You Set Before");
                Optional<String> securityAnswer = answer.showAndWait();
                if (securityAnswer.isPresent()) {
                    //TODO check if correct then change password
                    User user = GsonReaderWriter.getGsonReaderWriter().loadUser(username.get());
                    if (user.getSecurityAnswer().equalsIgnoreCase(securityAnswer.get())) {
                        TextInputDialog newPassword = new TextInputDialog();
                        newPassword.setContentText("Enter new password");
                        newPassword.setHeaderText("New Password");
                        Optional<String> text = newPassword.showAndWait();
                        if (text.isPresent()) {
                            Alert alert = new Alert(Alert.AlertType.NONE);
                            if (!validator.validatePassword(text.get())) {
                                User.setCurrentUser(user);
                                ProfileMenuController controller = new ProfileMenuController();
                                controller.changePassword(text.get(), user.getPassword());
                                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                                alert.setContentText("password changed");
                                alert.show();
                                User.setCurrentUser(null);
                            } else {
                                alert.setAlertType(Alert.AlertType.ERROR);
                                alert.setContentText("Wrong password format");
                                alert.show();
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Wrong answer");
                        alert.show();
                    }
                }
            }
//        Optional s = answer.showAndWait();
//        answer.getDialogPane().getButtonTypes().add(ButtonType.OK);

//        Button okButton = (Button) answer.getDialogPane().lookupButton(ButtonType.OK);

//        Button ok = (Button)
//        Button op = (Button) answer.getDialogPane().getButtonTypes().get(0).getText();

//        TextField input = answer.getEditor();
//        System.out.println(ok.isPressed());
//        System.out.println(okButton.isPressed());
//        BooleanBinding isCorrect = Bindings.createBooleanBinding(() -> isSecurityAnswerCorrect(input.getText()),input.textProperty());
//        okButton.setOnAction(actionEvent -> isSecurityAnswerCorrect(securityAnswer.get()));


//        System.out.println(securityAnswer.get());
        }
    }

    private boolean isSecurityAnswerCorrect(String text) {
        return false;
    }

    private void register() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (validator.isUsernameDuplicate(Username.getText())) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("This Username is already exists.");
            alert.show();
        } else if (!validator.validateUsername(Username.getText())) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Username is not valid");
            alert.show();
        } else if (!validator.validateEmail(Email.getText())) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Email is invalid");
            alert.show();
        } else if (!validator.validatePassword(Password.getText()) && !Password.getText().equals("Random")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Password isn't valid");
            alert.show();
        } else if (validator.isPasswordWeak(Password.getText()) && !Password.getText().equals("Random")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Weak Password");
            alert.show();
        } else if (!Password.getText().equals(RepeatPassword.getText())) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Passwords don't match");
            alert.show();
        } else if (Nickname.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Nickname");
            alert.setContentText("Enter a nickname");
            alert.show();

        } else if (Password.getText().equals("Random") && RepeatPassword.getText().equals("Random")) {
            String suggestedPassword = registerMenuController.generateRandomPassword();
            alert.setHeaderText(suggestedPassword);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to continue with this password?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
//                registerMenuController.createNewUser(Username.getText(),Password.getText(),RepeatPassword.getText(), Nickname.getText(),Email.getText());
//                alert.setAlertType(Alert.AlertType.INFORMATION);
//                alert.setContentText("Your account successfully created");
//                alert.show();
                String[] questions = {"DayOfBirth", "FavoriteMovie", "YourFavoriteSchoolTeacher"};
                ChoiceDialog securityQuestions = new ChoiceDialog(questions[0], questions);
                securityQuestions.showAndWait();
                if (!securityQuestions.isShowing()) {

                    System.out.println(securityQuestions.getSelectedItem());
                    TextInputDialog answer = new TextInputDialog();
                    answer.setHeaderText("Your Answer");
                    Optional<String> securityAnswer = answer.showAndWait();
                    System.out.println(securityAnswer.get());

                    User user = User.create(Username.getText(), Password.getText(), Email.getText(), Nickname.getText(),
                            securityAnswer.get(), (String) securityQuestions.getSelectedItem());
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Success");
                    alert.setContentText("Register Completed");
                    alert.show();
                }
//                System.out.println(res.get());
            }
        } else {
            String[] questions = {"DayOfBirth", "FavoriteMovie", "YourFavoriteSchoolTeacher"};
            ChoiceDialog<String> securityQuestions = new ChoiceDialog<>(questions[0], questions);
            securityQuestions.showAndWait();
            if (!securityQuestions.isShowing()) {

                System.out.println(securityQuestions.getSelectedItem());
                TextInputDialog answer = new TextInputDialog();
                answer.setHeaderText("Your Answer");
                Optional<String> securityAnswer = answer.showAndWait();
                System.out.println(securityAnswer.get());
                User user = User.create(Username.getText(), Password.getText(), Email.getText(), Nickname.getText(), securityAnswer.get(), securityQuestions.getSelectedItem());
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Register Completed");
                alert.show();
            }
        }
    }

    private void login() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        User user = GsonReaderWriter.getGsonReaderWriter().loadUser(Username.getText());
        if (user == null) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("This username doesn't exist yet.");
            alert.show();
        } else if (!validator.isPasswordCorrect(user, Password.getText())) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Password is not correct.");
            alert.show();
        } else {
            ShowProfileMenu showMainMenu = new ShowProfileMenu();
            MainMenuGraphic mainMenu = new MainMenuGraphic();
            try {
                User.setCurrentUser(user);
                User.getCurrentUser().setFaction("monsters");
//                System.out.println(User.getCurrentUser().getFaction());
//                showMainMenu.start(stage);
                mainMenu.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
