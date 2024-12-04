package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.DoubleFunction;

public class Server {
    private static final int SERVER_PORT = 9999;

    private static class ClientHandler implements Runnable{
        private final Socket socket;
        public ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){

                String username = (String) in.readObject();

                out.writeObject("ready");
                out.flush();

                int n = in.read();
                out.writeObject("ready for messages");
                out.flush();

                for (int i=0; i<n; i++){
                    Message message = (Message) in.readObject();
                    System.out.println(username + ": " + message.getContent());
                }

                out.writeObject("finished");
                out.flush();

            } catch (IOException | ClassNotFoundException e ){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try(ServerSocket serverSocket = new ServerSocket(9999)){
            System.out.println("Server started.");

            while(true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ClientHandler(socket));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}