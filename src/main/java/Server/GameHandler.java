package Server;

import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;

public class GameHandler{
    private Socket socket;
    private GameHandler enemyGameHandler;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean isTurn;
    private int numberOfPassedRoundWithoutAction = 0;

    public GameHandler(Socket socket){
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            close();
        }
    }

    public void run(){
        String string;
        Matcher matcher;
        if(!socket.isClosed()){
            try {
                writer.write("first\n");
                writer.flush();
                isTurn = true;
                enemyGameHandler.isTurn = false;
            }catch (Exception e){
                close();
                e.printStackTrace();
            }
        }
        if(!enemyGameHandler.socket.isClosed()){
            try {
                enemyGameHandler.writer.write("second\n");
                enemyGameHandler.writer.flush();
            }catch (Exception e){
                close();
                e.printStackTrace();
            }
        }
        while (!socket.isClosed() && !enemyGameHandler.socket.isClosed()){
            if(this.isTurn){
//                try {
                    if(numberOfPassedRoundWithoutAction == 2){
                        //TODO check who wins and
                        break;
                    }
                    string = receiveMassageFromClient();
                    if((matcher =  ServerCommands.PlayCard.getMatcher(string)) != null){
                        enemyGameHandler.sendMassageToClient("opponent placed card " + matcher.group("cardName") + " on row " + matcher.group("rowNumber"));
                    } else if ((matcher = ServerCommands.sendMassageToOpponent.getMatcher(string)) != null) {
                        enemyGameHandler.sendMassageToClient("opponent say: " + matcher.group("massage"));
                    }
//                    enemyGameHandler.sendMassageToClient(string.trim());
                    if(string.equals("pass")){
                        isTurn = false;
                        enemyGameHandler.isTurn = true;
//                        enemyGameHandler.sendMassageToClient("your turn");
                        enemyGameHandler.sendMassageToClient("pass");
                    }
                    if(string.equals("passed")){
                        isTurn = false;
                        enemyGameHandler.isTurn = true;
//                        enemyGameHandler.sendMassageToClient("your turn");
                        numberOfPassedRoundWithoutAction++;
                        enemyGameHandler.sendMassageToClient("passed");
                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }else {
                if(numberOfPassedRoundWithoutAction == 2){
                    //TODO check who wins and
                    break;
                }
                string = enemyGameHandler.receiveMassageFromClient();
                if((matcher =  ServerCommands.PlayCard.getMatcher(string)) != null){
                    this.sendMassageToClient("opponent placed card " + matcher.group("cardName") + " on row " + matcher.group("rowNumber"));
                }else if ((matcher = ServerCommands.sendMassageToOpponent.getMatcher(string)) != null) {
                    this.sendMassageToClient("opponent say: " + matcher.group("massage"));
                }
//                this.sendMassageToClient(string.trim());
                if(string.equals("pass")){
                    enemyGameHandler.isTurn = false;
//                    this.sendMassageToClient("your turn");
                    this.sendMassageToClient("pass");
                    this.isTurn = true;
                }
                if (string.equals("passed")){
                    enemyGameHandler.isTurn = false;
//                    this.sendMassageToClient("your turn");
                    this.sendMassageToClient("passed");
                    this.isTurn = true;
                    numberOfPassedRoundWithoutAction++;
                }
            }
        }
    }

    public void close(){
        try {
            this.writer.close();
            this.reader.close();
            this.socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setEnemyGameHandler(GameHandler handler){
        this.enemyGameHandler = handler;
    }

    public GameHandler getEnemyGameHandler() {
        return enemyGameHandler;
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
            System.out.println("waiting");

        }catch (Exception e){
            e.printStackTrace();
        }
        return massage;
    }
    private void changeTurn(){
        isTurn = !isTurn;
        enemyGameHandler.isTurn = !isTurn;
    }

    public Socket getSocket() {
        return socket;
    }
}
