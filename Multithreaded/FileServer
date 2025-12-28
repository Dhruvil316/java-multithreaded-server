import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    public static void main(String[] args) {

        int port = 8010;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                // 1️⃣ Wait for client
                Socket clientSocket = serverSocket.accept();

                // 2️⃣ Create Runnable task
                Runnable task = new ClientHandler(clientSocket);

                // 3️⃣ Start thread
                Thread thread = new Thread(task);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
