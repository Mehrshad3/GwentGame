package model;

import enums.ServerResponse;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;
    private boolean isTurn;

    public static void main(String[] args) {
        Client client = new Client("hamid");
        Scanner scanner = new Scanner(System.in);


        while (!client.socket.isClosed()) {
            String string = scanner.nextLine();
            try {
                client.writer.write(string + "\n");
                client.writer.flush();
                System.out.println(string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Client(String username) {
        try {
            this.socket = new Socket("localhost", 150);//change ip
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(username + "\n");
            writer.flush();
            checkForServerMassage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkForServerMassage(Client client) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Matcher matcher;
                while (!client.socket.isClosed()) {
                    try {
                        String response = client.reader.readLine();
                        if ((matcher = ServerResponse.FriendRequest.getMatcher(response)) != null) {
                            Matcher finalMatcher = matcher;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Stage stage = new Stage();
                                    BorderPane pane = new BorderPane();
                                    setFriendRequestPopUpNodes(finalMatcher, pane, stage);
                                    Scene scene = new Scene(pane, 500, 300);
                                    scene.getStylesheets().add(getClass().getResource("/CSS/PopUpStyle.css").toExternalForm());
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.initStyle(StageStyle.UNDECORATED);
                                    stage.setScene(scene);
                                    stage.show();
                                }
                            });

                        } else if ((matcher = ServerResponse.ClientNotOnline.getMatcher(response)) != null) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText(response);
                                    alert.setHeaderText("Server Response");
                                    alert.show();
                                }
                            });
                        } else if ((matcher = ServerResponse.ClientNotAvailable.getMatcher(response)) != null) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText(response);
                                    alert.setHeaderText("Server Response");
                                    alert.show();
                                }
                            });
                        } else if ((matcher = ServerResponse.FriendRequestSent.getMatcher(response)) != null) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText(response);
                                    alert.setHeaderText("Server Response");
                                    alert.show();
                                }
                            });
                        } else if ((matcher = ServerResponse.Wait.getMatcher(response)) != null) {
                            System.out.println("wait for second player");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ProgressIndicator progressIndicator = new ProgressIndicator();
                                    BorderPane pane = new BorderPane();
                                    pane.setCenter(progressIndicator);
                                    //TODO make an exit button
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(pane,500,300));
                                    stage.initStyle(StageStyle.UNDECORATED);
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.centerOnScreen();
                                    stage.show();
                                }
                            });
                        }
                        System.out.println(response);
                    } catch (Exception e) {
                        try {
                            client.socket.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void sendMassage(String massage) {
        try {
            writer.write(massage.trim() + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            String turn = reader.readLine();
            System.out.println(turn);
            isTurn = !turn.equals("second");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Client client = this;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (client.socket.isConnected()) {
                    if (client.isTurn) {
                        try {
                            System.out.println("writing");
                            String input = scanner.nextLine();
                            client.writer.write(input + "\n");
                            client.writer.flush();
                            client.isTurn = false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            System.out.println("waiting");
                            String game = client.reader.readLine();
                            System.out.println(game);
                            client.isTurn = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }

    public void chat(String massage) {
        VBox chatBox = new VBox();
        Text text = new Text(massage);
        chatBox.getChildren().add(text);
        Stage stage = new Stage();
        Scene scene = new Scene(chatBox);
        stage.setScene(scene);
        stage.show();
    }

    private void setFriendRequestPopUpNodes(Matcher finalMatcher, BorderPane pane, Stage stage) {
        Label request = new Label("friend request from " + finalMatcher.group("username"));
        request.setFont(new Font("Garamond", 20));
        Button accept = new Button("Accept");
        accept.setId("accept");
        accept.setFont(new Font("Garamond", 15));
        Button Reject = new Button("Reject");
        Reject.setId("reject");
        Reject.setFont(new Font("Garamond", 15));
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(50);
        buttons.getChildren().addAll(accept, Reject);
        vBox.getChildren().addAll(request, buttons);
        pane.setCenter(vBox);
        setButtonsAction(finalMatcher.group("username"), accept, Reject, stage);//
    }

    private void setButtonsAction(String username, Button accept, Button reject, Stage stage) {
        accept.setOnAction(actionEvent -> {
            FriendRequest friendRequest = new FriendRequest(username, "Accept");
            User.getCurrentUser().getFriendRequests().add(friendRequest);
            User sender = GsonReaderWriter.getGsonReaderWriter().loadUser(username);
            User.getCurrentUser().addFriend(sender);//TODO might need to change to String
            sender.addFriend(User.getCurrentUser());
            stage.close();
        });
        reject.setOnAction(actionEvent -> {
            FriendRequest friendRequest = new FriendRequest(username,"Reject");
            User.getCurrentUser().getFriendRequests().add(friendRequest);
            stage.close();
        });
    }

    public void sendFriendRequest(String username) {
        try {
            writer.write("send friend request to " + username + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
