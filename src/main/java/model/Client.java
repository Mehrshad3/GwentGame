package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.GameController;
import enums.Menu;
import enums.ServerResponse;
import enums.card.CardName;
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
import model.faction.Card;
import model.faction.UnitCard;
import view.game.graphics.LeaderBoardGraphic;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.regex.Matcher;

public class Client {
    private static final String HOST_ADDRESS = "localhost";//change ip
    private static final int PORT_NUMBER = 150;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;
    private boolean isTurn;
    private boolean isInGame;

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
            this.socket = new Socket(HOST_ADDRESS, PORT_NUMBER);
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
                        if (!isInGame) {
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
                                        Button button = new Button("Cancel");
                                        Stage waitStage = App.getWaitStage();
                                        button.setOnAction(actionEvent -> waitStage.close());//TODO send cancel to server
                                        pane.setBottom(button);
                                        waitStage.setScene(new Scene(pane, 500, 300));
                                        waitStage.initStyle(StageStyle.UNDECORATED);
                                        waitStage.initModality(Modality.APPLICATION_MODAL);
                                        waitStage.centerOnScreen();
                                        waitStage.show();
                                        waitStage.setOnCloseRequest(windowEvent -> App.getStage().setScene(App.getPreGameMenu()));//bug
                                    }
                                });
                            } else if ((matcher = ServerResponse.MassageFromOpponent.getMatcher(response)) != null) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        //TODO app massage to scene

                                    }
                                });
                            } else if ((matcher = ServerResponse.PlayCardByOpponent.getMatcher(response)) != null) {
                                Card card = CardName.getCardByName(matcher.group("cardName").replaceAll("-"," "));
                                System.out.println("get");
                                Matcher finalMatcher1 = matcher;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        GameController gameController = (GameController) Menu.GameMenu.getMenuController();
//                                    gameController.playCard(card,7 - Integer.parseInt(finalMatcher1.group("rowNumber")));
                                        gameController.addSpellCardToTableLocally((UnitCard) card, 7 - Integer.parseInt(finalMatcher1.group("rowNumber")));
                                        System.out.println(card.getName());
                                        System.out.println(7 - Integer.parseInt(finalMatcher1.group("rowNumber")));
                                    }
                                });
                            } else if ((matcher = ServerResponse.GameStarted.getMatcher(response)) != null) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Stage waitStage = App.getWaitStage();
                                        waitStage.close();

                                    }
                                });
                                playGame();
                            } else if ((matcher = ServerResponse.LeaderBoard.getMatcher(response)) != null) {
                                String string = client.getServerResponse();
//                                System.out.println(string + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                                Gson gson = new Gson();
                                ArrayList<SimpleUser> users = new ArrayList<>();
                                users = gson.fromJson(string, new TypeToken<ArrayList<SimpleUser>>(){}.getType());
                                ArrayList<SimpleUser> finalUsers = users;
//                                System.out.println(users);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {

//                                        HashMap<SimpleUser,Double> users = gson.fromJson(string, HashMap.class);
//                                        System.out.println(users);
                                        LeaderBoardGraphic leaderBoardGraphic = new LeaderBoardGraphic(finalUsers);
                                        App.setLeaderBoardGraphic(leaderBoardGraphic);
                                        try {
                                            leaderBoardGraphic.start(App.getStage());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else if ((matcher = ServerResponse.UpdateLeaderBoard.getMatcher(response)) != null) {
                                String string = reader.readLine();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Gson gson = new Gson();
                                        ArrayList<SimpleUser> users = gson.fromJson(string, new TypeToken<ArrayList<SimpleUser>>(){}.getType());
                                        if(App.getLeaderBoardGraphic() != null){
                                            App.getLeaderBoardGraphic().updateTable(users);
                                        }
                                    }
                                });

                            }
                            System.out.println(response);
                        }//TODO if is in game added
                    }catch (Exception e) {
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
    public String getServerResponse(){
        String response = null;
        try {
            response = reader.readLine();
        } catch (IOException e) {
            System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            e.printStackTrace();
        }
        return response;
    }
    public void playGame(){
        try {
            System.out.println("playing game");
            String turn = reader.readLine();
            System.out.println(turn + " in playGAme");
            isTurn = !turn.equals("second");
            GameController controller = (GameController) Menu.GameMenu.getMenuController();
            controller.setIsMyTurn(isTurn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Matcher matcher;

        while (!socket.isClosed()){
            if(!isTurn){
                try {
                    System.out.println("opponent turn");
                    String response = reader.readLine();
                    System.out.println(response);
                    if ((matcher = ServerResponse.PlayCardByOpponent.getMatcher(response)) != null) {
                        Card card = CardName.getCardByName(matcher.group("cardName").replaceAll("-"," "));
                        System.out.println("get");
                        Matcher finalMatcher1 = matcher;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                GameController gameController = (GameController) Menu.GameMenu.getMenuController();
//                                    gameController.playCard(card,7 - Integer.parseInt(finalMatcher1.group("rowNumber")));
                                gameController.addSpellCardToTableLocally((UnitCard) card,7 - Integer.parseInt(finalMatcher1.group("rowNumber")));
                                System.out.println(card.getName());
                                System.out.println(7 - Integer.parseInt(finalMatcher1.group("rowNumber")));
                            }
                        });
                    } else if ((matcher = ServerResponse.Pass.getMatcher(response)) != null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                isTurn = true;
                                GameController controller = (GameController) Menu.GameMenu.getMenuController();
                                controller.setIsMyTurn(true);
                            }
                        });
                    } else if ((matcher = ServerResponse.Passed.getMatcher(response)) != null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                isTurn = true;
                                GameController controller = (GameController) Menu.GameMenu.getMenuController();
                                controller.setIsMyTurn(true);
                            }
                        });
                    } else if ((matcher = ServerResponse.MassageFromOpponent.getMatcher(response)) != null) {
                        Matcher finalMatcher = matcher;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                GameController gameController = (GameController) Menu.GameMenu.getMenuController();
                                Timer timer = new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        gameController.setChatLabelText("");
                                    }
                                },15000);
                               gameController.setChatLabelText(finalMatcher.group("massage"));
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                String response = null;
                try {
                    response = reader.readLine();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if((matcher = ServerResponse.MassageFromOpponent.getMatcher(response)) != null){
                    Matcher finalMatcher2 = matcher;
                    Platform.runLater(new Runnable() {
                        GameController gameController = (GameController) Menu.GameMenu.getMenuController();

                        @Override
                        public void run() {
                            gameController.setChatLabelText(finalMatcher2.group("massage"));
                        }
                    });
                }
            }
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
                            //TODO make changes mehrshad
                            String input = scanner.nextLine();
                            client.writer.write(input + "\n");
                            client.writer.flush();
//                            client.isTurn = false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            System.out.println("waiting");
                            String game = client.reader.readLine();
                            System.out.println(game);
//                            client.isTurn = true;
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
