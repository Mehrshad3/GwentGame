package model;


import java.io.Serializable;
import java.util.LinkedList;

public class User extends Player implements Serializable {
    private static User CurrentUser = null;
    LinkedList<GameHistory> histories;
    private String password;

    private String email;
    private int rank;
    private int numberOfGamesPlayed;
    private int numberOfWins;
    private int numberOfLoses;
    private int highestScore;
    private String securityAnswer;
    private String securityQuestion;

    private User(String name, String password, String email, String nickname, String securityAnswer, String securityQuestion) {
        super(name, nickname);
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.securityAnswer = securityAnswer;
        this.securityQuestion = securityQuestion;
    }

    public static User create(String name, String password, String email, String nickName, String securityAnswer,
                              String securityQuestion) {
        User newUser = new User(name, password, email, nickName, securityAnswer, securityQuestion);
        GsonReaderWriter.getGsonReaderWriter().saveUser(newUser);
        return newUser;
    }

    public static User getCurrentUser() {
        return CurrentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void saveUser() {
        // TODO
    }

    public int getWins() {
        return numberOfWins;
    }

    public int getNumberOfLoses() {
        return numberOfLoses;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
}

