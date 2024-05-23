package model;

import java.io.Serializable;
import java.nio.file.Path;
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

    public User(String name, String password, String email, String nickname) {
        super(name, nickname);
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        GsonReaderWriter.saveToFile(this, this.getRelativePathToFile());
    }

    public static User loadUser(String username) { // TODO: think whether this is the right place for this?
        return null; // TODO
    }

    public static User getCurrentUser() {
        return CurrentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public static Path getRelativePathToFile(String username) {
        return Path.of("Users", username, "profile.json");
    }

    Path getRelativePathToFile() {
        return User.getRelativePathToFile(this.name);
    }

    private void saveUser() {
        // TODO
    }

    public int getWins() {
        return numberOfWins;
    }
}
