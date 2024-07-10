package Server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{

    private Socket socket;
    private ClientHandler enemyHandler;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;
    private boolean isAvailable = true;

    public ClientHandler(Socket socket){
        this.socket = socket;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.println("here");
            username = reader.readLine();
//            System.out.println("not here");
        }catch (Exception e){
            close();
        }
    }

    public void run() {
        String input;
        while (socket.isConnected()) {// && enemyHandler.playerSocket.isConnected()
            try {
                input = reader.readLine();
                checkCommand(input);
                //send to other player
                enemyHandler.sendMassageToClient(input);
                String output = enemyHandler.receiveMassageFromClient();
                writer.write(output + "\n");//might need .trim
                writer.flush();
            } catch (IOException e) {
                break;
            }
        }
    }

    private void checkCommand(String input) {
        switch (input){
            case "play random game":

        }
    }

    public void sendMassageToClient(String massage){
        try {
            writer.write(massage.trim() + "\n");//needed???
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMassageFromClient(){
        String massage = null;
        try{
            massage = reader.readLine();
        }catch (Exception e){
            e.printStackTrace();
        }
        return massage;
    }
    public boolean isAvailable(){
        return isAvailable;
    }

    public void setEnemyHandler(ClientHandler clientHandler){
        this.enemyHandler = clientHandler;
    }
    private void close(){
        try{
            writer.close();
            reader.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getUsername(){
        return this.username;
    }
    public Socket getSocket(){
        return this.socket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }
}
