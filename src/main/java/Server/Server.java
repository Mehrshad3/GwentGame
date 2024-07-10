package Server;

import com.google.gson.Gson;
import model.Client;
import model.SimpleUser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;

public class Server {
    private ServerSocket serverSocket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Socket firstSocket;
    private Socket secondSocket;
    private Socket socket;
    private static ArrayList<Socket> sockets = new ArrayList<>();
    private static ArrayList<Socket> waitingPrivateRandomGameSockets = new ArrayList<>();
    private static ArrayList<Socket> waitingPublicRandomGameSockets = new ArrayList<>();
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private static ArrayList<GameHandler> gameHandlers = new ArrayList<>();
    private static ArrayList<String> clientsNames = new ArrayList<>();

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                try {
                    socket = serverSocket.accept();

                    ClientHandler clientHandler = new ClientHandler(socket);//TODO several clients cant connect
                    sockets.add(socket);
                    clientHandlers.add(clientHandler);
                    System.out.println(clientHandler.getUsername());
                    checkForClientCommands(socket);
                    System.out.println("new connection");

                } catch (IOException e) {
                    firstSocket.close();
                    secondSocket.close();
                    firstSocket = null;
                    secondSocket = null;
                }
            }
        } catch (Exception e) {
            closeServer();
        }
    }

    private void checkForClientCommands(Socket socket) {
        final Matcher[] matcher = new Matcher[1];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedWriter bufferedWriter = getClientHandlerRelatedToSocket(socket).getWriter();
                    BufferedReader bufferedReader = getClientHandlerRelatedToSocket(socket).getReader();
                    String string;
                    while (!socket.isClosed()) {
                        try {
                            string = bufferedReader.readLine();
                            if ((matcher[0] = ServerCommands.watchGame.getMatcher(string)) != null) {
                                watchGame(socket);
                            } else if ((matcher[0] = ServerCommands.sendFriendRequest.getMatcher(string)) != null) {
                                sendFriendRequest(matcher[0].group("username"), getClientHandlerRelatedToSocket(socket));
                                System.out.println("sending friend request");
                            } else if ((matcher[0] = ServerCommands.register.getMatcher(string)) != null) {
                                register(socket);
                            } else if ((matcher[0] = ServerCommands.startPrivateRandomGame.getMatcher(string)) != null) {
                                startPrivateRandomGame(bufferedWriter);
                            } else if ((matcher[0] = ServerCommands.sendMassageToOpponent.getMatcher(string)) != null) {
                                if(!sendInGameMassage(matcher[0].group("massage"),socket)){
                                    bufferedWriter.write("you are not in a game");
                                    bufferedWriter.flush();
                                }
                            } else if ((matcher[0] = ServerCommands.startPublicRandomGame.getMatcher(string)) != null) {

                                startPublicRandomGame(bufferedWriter,socket);
                            } else if ((matcher[0] = ServerCommands.PlayCard.getMatcher(string)) != null) {
                                sendPlayedCard(matcher[0],socket);
                            } else if ((matcher[0] = ServerCommands.LeaderBoard.getMatcher(string)) != null) {
                                showLeaderBoard(socket);
                            } else {
                                System.out.println(string);
                                bufferedWriter.write("invalid command\n");
                                bufferedWriter.flush();
                            }
                        } catch (Exception e) {
                            socket.close();
                            sockets.remove(socket);//e.print
                            removeClientHandler(socket);
                            bufferedWriter.close();
                            bufferedReader.close();
                        }
                    }
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void sendMassage() {

    }

    private void sendPlayedCard(Matcher matcher, Socket socket){
//        ClientHandler clientHandler = getClientHandlerRelatedToSocket(socket);
        GameHandler gameHandler = getGameHandlerRelatedToSocket(socket);
        gameHandler.getEnemyGameHandler().sendMassageToClient("opponent placed card " + matcher.group("cardName") + " on row " + matcher.group("rowNumber"));
//        clientHandler.sendMassageToClient("opponent placed card " + matcher.group("cardName") + " on row " + matcher.group("rowNumber"));
    }

    private void showLeaderBoard(Socket socket){
        ClientHandler clientHandler = getClientHandlerRelatedToSocket(socket);
        clientHandler.sendMassageToClient("leaderBoard:");
        Gson gson = new Gson();
//        System.out.println("1");
        ArrayList<SimpleUser> users = new ArrayList<>();//TODO
//        System.out.println("2");
        users.add(new SimpleUser("Nima",false));
//        System.out.println("3");
        users.add(new SimpleUser("Amin",true));
//        System.out.println("4");
//        System.out.println(users);
//        System.out.println("5");
//        System.out.println(gson.toJson(users));
//        System.out.println("6");
        clientHandler.sendMassageToClient(gson.toJson(users));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clientHandler.sendMassageToClient("update LeaderBoard:");
                users.add(new SimpleUser("Ali",true));
                users.get(0).setOnline(true);
                clientHandler.sendMassageToClient(gson.toJson(users));
            }
        }, 7000);
    }

    private void startPrivateRandomGame(BufferedWriter bufferedWriter) {
        if (waitingPrivateRandomGameSockets.isEmpty()) {
            waitingPrivateRandomGameSockets.add(socket);
            try {
                bufferedWriter.write("wait\n");
                bufferedWriter.flush();
            }catch (Exception e){
                //TODO ???????
            }
        } else{
//            GameHandler gameHandler = new GameHandler(socket);
//            GameHandler enemyGameHandler = new GameHandler(waitingPrivateRandomGameSockets.get(0));
//            waitingPrivateRandomGameSockets.remove(0);//TODO can remove object(socket)
//            gameHandler.setEnemyGameHandler(enemyGameHandler);
//            enemyGameHandler.setEnemyGameHandler(gameHandler);
//            gameHandler.sendMassageToClient("game started\n");
//            enemyGameHandler.sendMassageToClient("game started\n");
//            gameHandler.start();
        }
    }
    private void startPublicRandomGame(BufferedWriter writer,Socket socket){
        if(waitingPublicRandomGameSockets.isEmpty()){
//            System.out.println("new game");
            waitingPublicRandomGameSockets.add(socket);
            try {
                writer.write("wait\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();//TODO
            }

        }else {
            ClientHandler clientHandler = getClientHandlerRelatedToSocket(socket);
            GameHandler gameHandler = new GameHandler(socket,clientHandler.getWriter(),clientHandler.getReader());
            ClientHandler enemyClientHandler = getClientHandlerRelatedToSocket(waitingPublicRandomGameSockets.get(0));
            GameHandler enemyGameHandler = new GameHandler(waitingPublicRandomGameSockets.get(0),enemyClientHandler.getWriter()
            ,enemyClientHandler.getReader());
            waitingPublicRandomGameSockets.remove(0);//TODO waht auto closable means????
            gameHandler.setEnemyGameHandler(enemyGameHandler);
//            enemyGameHandler.setEnemyGameHandler(gameHandler);
            gameHandler.sendMassageToClient("game started\n");
            enemyGameHandler.sendMassageToClient("game started\n");
//            gameHandler.start();
            gameHandler.run();
        }
    }

    public void watchGame(Socket socket) {
        ViewerHandler viewerHandler = new ViewerHandler(socket);
        viewerHandler.run();
    }
    private boolean sendInGameMassage(String massage,Socket socket){
        GameHandler gameHandler = getGameHandlerRelatedToSocket(socket);
        GameHandler enemyGameHandler = gameHandler.getEnemyGameHandler();
        if(enemyGameHandler == null){
            return false;
        }
        enemyGameHandler.sendMassageToClient(massage);
        return true;
    }

    private void sendFriendRequest(String receiverUsername, ClientHandler sender) {
        //TODO check if exists such user
        boolean isOnline = false;
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.getUsername().equals(receiverUsername)) {
                if(clientHandler.isAvailable()) {
                    clientHandler.sendMassageToClient("friend request from " + sender.getUsername());
                }else {
                    sender.sendMassageToClient(receiverUsername + " is not available right now");
                }
//                sender.sendMassageToClient(clientHandler.receiveMassageFromClient());
                isOnline = true;
                break;
            }
        }
        if(!isOnline){
            sender.sendMassageToClient(receiverUsername + " is not online");
        }
    }
    private ClientHandler getClientHandlerByName(String username){
        for (ClientHandler clientHandler : clientHandlers) {
            if(clientHandler.getUsername().equals(username)){
                return clientHandler;
            }
        }
        return null;
    }
    private void register(Socket socket){
//        if()
    }

    private void removeClientHandler(Socket socket) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.getSocket().equals(socket)) {
                clientHandlers.remove(clientHandler);
                break;
            }
        }
    }

    private ClientHandler getClientHandlerRelatedToSocket(Socket socket) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.getSocket().equals(socket)) {//TODO might need == instead of equals
                return clientHandler;
            }
        }
        return null;
    }
    private GameHandler getGameHandlerRelatedToSocket(Socket socket){
        for (GameHandler handler : gameHandlers) {
            if(handler.getSocket().equals(socket)){
                return handler;
            }
        }
        return null;
    }

    private void closeServer() {
        try {
            reader.close();
            writer.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
