package kg.nurolopher;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;

public class ChatServer {

    private static final int PORT = 9001;

    public static HashSet<PrintWriter> writers = new HashSet<>();

    public static HashSet<String> names = new HashSet<>();

    public static void main(String[] args) throws Exception {

        System.out.println("The chat server is running");
        ServerSocket socket = new ServerSocket(PORT);
        try {
            while (true) {
                new ServerThread(socket.accept()).start();
            }
        } finally {
            socket.close();
        }

    }
}
