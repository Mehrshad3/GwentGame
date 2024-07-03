package model;

public class FriendRequest {
    private String username;
    private String response;

    public FriendRequest(String username, String response) {
        this.username = username;
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
