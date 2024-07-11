package view;

import controller.*;
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
import model.Client;
import model.GsonReaderWriter;
import model.User;

import java.util.Optional;
import java.util.logging.Level;


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
        loginMenuController.setShowingLoginMenu(this);
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
        vBox.getChildren().add(Username);

        Password = new PasswordField();
        Password.setPromptText("Password");
        vBox.getChildren().add(Password);

        RepeatPassword = new PasswordField();
        RepeatPassword.setPromptText("Repeat Password");
        vBox.getChildren().add(RepeatPassword);

        Email = new TextField();
        Email.setPromptText("Email");
        vBox.getChildren().add(Email);

        Nickname = new TextField();
        Nickname.setPromptText("Nickname");
        vBox.getChildren().add(Nickname);

        Button LoginButton = new Button("Login");
        LoginButton.setOnAction(actionEvent -> login());
        LoginButton.setMinWidth(75);
        vBox.getChildren().add(LoginButton);

        Button RegisterButton = new Button("Register");
        RegisterButton.setOnAction(actionEvent -> register());
        RegisterButton.setMinWidth(75);
        vBox.getChildren().add(RegisterButton);

        Button ForgotPassword = new Button("Forgot Password");
        ForgotPassword.setOnAction(actionEvent -> changeForgottenPassword());
        ForgotPassword.setMinWidth(75);
        vBox.getChildren().add(ForgotPassword);
    }

    private void changeForgottenPassword() {
        String username = askUserForUsername();

        if (username != null) {
            if (!validator.isUsernameDuplicate(username)) {
                showWarningAlert("username doesn't exist");
                return;
            }
            String securityAnswerEntered = askForSecurityPasswordSetBefore();
            if (securityAnswerEntered == null) return;
            //TODO check if correct then change password
            User user = GsonReaderWriter.getGsonReaderWriter().loadUser(username);
            assert user != null;
            if (!user.getSecurityAnswer().equalsIgnoreCase(securityAnswerEntered)) {
                showWarningAlert("Wrong answer");
                return;
            }
            String text = askForNewPassword();
            if (text == null) return;
            if (!validator.validatePassword(text)) {
                showWarningAlert("Wrong password format");
            } else if (!validator.validatePassword(text)) {
                User.setCurrentUser(user);
                ProfileMenuController controller = new ProfileMenuController();
                controller.changePassword(text, user.getPassword());
                alertPasswordChangedSuccessfully();
                User.setCurrentUser(null);
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
            showWarningAlert("This Username is already exists.");
        } else if (!validator.validateUsername(Username.getText())) {
            showWarningAlert("Username is not valid");
        } else if (!validator.validateEmail(Email.getText())) {
            showWarningAlert("Email is invalid");
        } else if (!validator.validatePassword(Password.getText()) && !Password.getText().equals("Random")) {
            showWarningAlert("Password isn't valid");
        } else if (validator.isPasswordWeak(Password.getText()) && !Password.getText().equals("Random")) {
            showWarningAlert("Weak Password");
        } else if (!Password.getText().equals(RepeatPassword.getText())) {
            showWarningAlert("Passwords don't match");
        } else if (Nickname.getText().isEmpty()) {
            alertNicknameNotEntered();
        } else if (Password.getText().equals("Random") && RepeatPassword.getText().equals("Random")) {
            String suggestedPassword = registerMenuController.generateRandomPassword();
            Boolean result = false;
            while (result != null && !result) result = confirmRandomlyGeneratedPassword(suggestedPassword);
            if (result == null) return;
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

                User user = User.create(Username.getText(), suggestedPassword, Email.getText(), Nickname.getText(),
                        securityAnswer.get(), (String) securityQuestions.getSelectedItem());
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Register Completed");
                alert.show();
            }
//                System.out.println(res.get());
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
            MainMenuGraphic mainMenu;
            try {
                User.setCurrentUser(user);
                User.getCurrentUser().setFaction("monsters");
                Client client = new Client(user.getName());
                ClientController controller = new ClientController(client);
                App.setClientController(controller);
//                client.sendMassage(User.getCurrentUser().getName());
//                System.out.println(User.getCurrentUser().getFaction());
//                showMainMenu.start(stage);
                mainMenu = new MainMenuGraphic();
                mainMenu.start(stage);
            } catch (Exception e) {
                App.LOGGER.log(Level.WARNING, "Exception in setting the client up", e);
            }
        }

    }


    private String askUserWithTextInputDialog(String title, String headerText, String contextText) {
        TextInputDialog Username = new TextInputDialog();
        if (title != null) Username.setTitle(title);
        Username.setHeaderText(headerText);
        Username.setContentText(contextText);
        Optional<String> optional = Username.showAndWait();
        return optional.orElse(null);
    }

    private String askUserWithTextInputDialog(String headerText, String contextText) {
        return askUserWithTextInputDialog(null, headerText, contextText);
    }

    public String askUserForUsername() {
        return askUserWithTextInputDialog("Username", "Enter Your Username");
    }

    public String askForSecurityPasswordSetBefore() {
        return askUserWithTextInputDialog("Change Password", "Security Question",
                "Enter Your Security Answer You Set Before");
    }

    public String askForNewPassword() {
        return askUserWithTextInputDialog("Enter new password", "New Password");
    }

    public Boolean confirmRandomlyGeneratedPassword(String suggestedPassword) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(suggestedPassword);
        alert.setContentText("Do you want to continue with this password?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()) return null;
        else return result.get() == ButtonType.OK;
    }


    public void showWarningAlert(String contextText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(contextText);
        alert.show();
    }

    public void alertPasswordChangedSuccessfully() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("password changed");
        alert.show();
    }

    public void alertNicknameNotEntered() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Nickname");
        alert.setContentText("Enter a nickname");
        alert.show();
    }
}
