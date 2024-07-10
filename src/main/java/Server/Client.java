package Server;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;
    private boolean isTurn;
    public static void main(String[] args){
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!client.socket.isClosed()){
                    try {
                        String response = client.reader.readLine();
                        System.out.println(response);
                    }catch (Exception e){
                        try {
                            client.socket.close();
                        }catch (Exception e1){
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        while (!client.socket.isClosed()){
            String string = scanner.nextLine();
            try {
                client.writer.write(string + "\n");
                client.writer.flush();
                System.out.println(string);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Client() {
        try {
            this.socket = new Socket("localhost", 150);//change ip
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendMassage(String massage){
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void play(){
        try {
            String turn = reader.readLine();
            System.out.println(turn);
            isTurn = !turn.equals("second");
        }catch (IOException e){
            e.printStackTrace();
        }
        Client client = this;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (client.socket.isConnected()){
                    if(client.isTurn){
                        try {
                            System.out.println("writing");
                            String input = scanner.nextLine();
                            client.writer.write(input + "\n");
                            client.writer.flush();
                            client.isTurn = false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            System.out.println("waiting");
                            String game = client.reader.readLine();
                            System.out.println(game);
                            client.isTurn = true;
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }

    public void chat(String massage){
        VBox chatBox = new VBox();
        Text text = new Text(massage);
        chatBox.getChildren().add(text);
        Stage stage = new Stage();
        Scene scene = new Scene(chatBox);
        stage.setScene(scene);
        stage.show();
    }

    public void sendFriendRequest(String username){
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
