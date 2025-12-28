import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    public static void main(String[] args) {

        int port = 8010;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            serverSocket.setSoTimeout(70000);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                Runnable task = new ClientHandler(clientSocket);

                Thread thread = new Thread(task);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}

// main() accepts the connection
// ClientHandler uses socket
// ClientHandler close krega
