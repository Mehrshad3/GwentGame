package model;

import java.util.LinkedList;

public class User {
    private static User CurrentUser = null;
    LinkedList<GameHistory> histories;
    Deck deck;
    private String name;
    private String password;
    private String email;
    private String nickname;
    private int rank;
    private int numberOfGamesPlayed;
    private int numberOfWins;
    private int numberOfLoses;

    public User(String name, String password, String email, String nickname) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public static User getCurrentUser() {
        return CurrentUser;
    }

    public int getNumberOfSoldiers() {
        return 0; //TODO
    }
}
