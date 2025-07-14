import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public void run() throws IOException, UnknownHostException {
        int serverPort = 8010;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        serverSocket.setSoTimeout(20000); // 20-second timeout

        while (true) {
            System.out.println("Server is listening on port: " + serverPort);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to " + clientSocket.getRemoteSocketAddress());

            PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outputToClient.println("Hello World from the server");
        }
    }

    public static void main(String[] args) {
        Server serverInstance = new Server();
        try {
            serverInstance.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
