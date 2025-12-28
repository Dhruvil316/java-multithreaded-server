
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    
    public Runnable getRunnable() {

    return () -> {
        Socket socket = null;
        PrintWriter toSocket = null;
        BufferedReader fromSocket = null;

        try {
            socket = new Socket("localhost", 8010);

            toSocket = new PrintWriter(socket.getOutputStream(), true);
            fromSocket = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            toSocket.println("Hello from Client " + socket.getLocalSocketAddress());

            String line = fromSocket.readLine();
            System.out.println("Response from Server " + line);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fromSocket != null) fromSocket.close();
                if (toSocket != null) toSocket.close();
                if (socket != null) socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}

    
    public static void main(String[] args){
        Client client = new Client();
        for(int i=0; i<100; i++){
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }catch(Exception ex){
                return;
            }
        }
        return;
    }
}
