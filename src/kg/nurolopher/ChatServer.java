package kg.nurolopher;

import javax.swing.*;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;

public class ChatServer {

    private static int PORT;

    public static HashSet<PrintWriter> writers = new HashSet<>();

    public static HashSet<String> names = new HashSet<>();

    public static void main(String[] args) throws Exception {

        ChatServer.getPortNumber();
        System.out.println("Running on port: " + PORT);
        ServerSocket socket = new ServerSocket(PORT);
        try {
            while (true) {
                new ServerThread(socket.accept()).start();
            }
        } finally {
            socket.close();
        }

    }

    public static void getPortNumber() {
        String port = JOptionPane.showInputDialog("Server port number", "5555");
        while (!(port.matches("[0-9]+") && port.length() > 1)) {
            getPortNumber();
        }
        PORT = Integer.parseInt(port);
    }
}
