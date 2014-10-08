package kg.nurolopher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

    private String name;
    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            while (true) {
                out.println("SUBMITNAME");
                name = in.readLine();
                if (name == null) {
                    return;
                }

                synchronized (ChatServer.names) {
                    if (!ChatServer.names.contains(name)) {
                        ChatServer.names.add(name);
                        break;
                    }
                }
            }
            out.println("NAMEACCEPTED");
            ChatServer.writers.add(out);

            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                for (PrintWriter writer : ChatServer.writers) {
                    writer.println("MESSAGE " + name + ": " + input);
                }
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (name != null) {
                ChatServer.names.remove(name);
            }
            if (out != null) {
                ChatServer.writers.remove(out);
            }
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

}
