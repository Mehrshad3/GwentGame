package Server;

import java.io.*;
import java.net.Socket;

public class ViewerHandler {
    Socket socket;
    BufferedReader reader;
    BufferedWriter writer;

    public ViewerHandler(Socket socket){
        this.socket = socket;
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {

        try{
            final boolean[] isWatching = {true};
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(reader.readLine().equals("exit")){
                            isWatching[0] = false;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            while (isWatching[0]){
                System.out.println("watching");
            }
            writer.write("you are watching game\n");
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

