package kg.nurolopher;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Acer on 04.10.2014.
 */
public class ChatServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java ChatServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                new ServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
