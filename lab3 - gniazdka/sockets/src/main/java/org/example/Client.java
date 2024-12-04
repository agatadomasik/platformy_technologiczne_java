package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9999)){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter username: ");
            String username = scanner.next();
            out.writeObject(username);

            String serverResponse = (String) in.readObject();
            System.out.println("Server: " + serverResponse);

            //System.out.println("Enter number of messages: ");
            //int n = scanner.nextInt();
            Random rand = new Random();
            int n = rand.nextInt(15);
            System.out.println("liczba wiadomości: " + n);
            out.write(n);
            out.flush();

            serverResponse = (String) in.readObject();
            System.out.println("Server: " + serverResponse);

            for (int i = 0; i<n; i++){
                //System.out.println("Enter message number " + (i+1) + ": ");
                int s = rand.nextInt(20);
                System.out.println("wiadomosc " + i + ": " + s);
                String content = Integer.toString(s);
                //String content = scanner.next();
                Message message = new Message(i+1, content);
                out.writeObject(message);
                out.flush();
            }

            serverResponse = (String) in.readObject();
            System.out.println("Server: " + serverResponse);

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
