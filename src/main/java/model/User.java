package model;

public class User {
    private static User CurrentUser = null;
    private String name;
    private String password;
    private String email;
    private String nickname;

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

    public String getName() {
        return name;
    }


}
