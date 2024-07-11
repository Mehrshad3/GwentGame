package controller;

import model.GsonReaderWriter;
import model.User;
import view.ShowingLoginMenu;

import java.util.regex.Matcher;

public class LoginMenuController extends LoginOrRegisterMenuController {
    private Validator validator;
    private ShowingLoginMenu showingLoginMenu;

    public void setShowingLoginMenu(ShowingLoginMenu showingLoginMenu) {
        this.showingLoginMenu = showingLoginMenu;
        this.validator = Validator.getValidator();
    }

    public void login(String username, String password, Boolean stayLoggedIn) {
        // TODO
    }

    public void handleForgottenPassword() {
        String username = showingLoginMenu.askUserForUsername();

        if (username != null) {
            if (!Validator.getValidator().isUsernameDuplicate(username)) {
                showingLoginMenu.showWarningAlert("username doesn't exist");
                return;
            }
            String securityAnswerEntered = showingLoginMenu.askForSecurityPasswordSetBefore();
            if (securityAnswerEntered == null) return;
            //TODO check if correct then change password
            User user = GsonReaderWriter.getGsonReaderWriter().loadUser(username);
            assert user != null;
            if (!user.getSecurityAnswer().equalsIgnoreCase(securityAnswerEntered)) {
                showingLoginMenu.showWarningAlert("Wrong answer");
                return;
            }
            String text = showingLoginMenu.askForNewPassword();
            if (text == null) return;
            if (!validator.validatePassword(text)) {
                showingLoginMenu.showWarningAlert("Wrong password format");
            } else if (!validator.validatePassword(text)) {
                User.setCurrentUser(user);
                ProfileMenuController controller = new ProfileMenuController();
                controller.changePassword(text, user.getPassword());
                showingLoginMenu.alertPasswordChangedSuccessfully();
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

    @Override
    protected boolean getSecurityQuestionAnswer(Matcher matcher) {
        return false; //TODO
    }

    public void changePassword(String newPassword) {
        // TODO
    }
}
