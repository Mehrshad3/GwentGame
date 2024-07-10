package model;

public class SimpleUser {
    private String username;
    private boolean isOnline;
    private double score;

    public SimpleUser(String username, boolean isOnline) {
        this.username = username;
        this.isOnline = isOnline;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public double getScore() {
        return score;
    }
}
