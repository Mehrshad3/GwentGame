package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String[] input = new String[1];
        Thread commandLine = new Thread(() -> {
            while (true){
                input[0] = scanner.nextLine();
                System.out.println(input[0]);
            }
        });
        commandLine.setDaemon(true);
        commandLine.start();
        try {
            ServerSocket serverSocket = new ServerSocket(150);
            Server server = new Server(serverSocket);
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
