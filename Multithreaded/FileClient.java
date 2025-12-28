import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class FileClient {

    public static void main(String[] args) {

        FileClient client = new FileClient();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(client.getRunnable(i));
            thread.start();
        }
    }

    public Runnable getRunnable(int clientId) {

        return new Runnable() {
            @Override
            public void run() {

                Socket socket = null;
                BufferedReader in = null;

                try {

                    socket = new Socket("localhost", 8010);
                    System.out.println("Client " + clientId + " connected");

                    in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream())
                    );

                    String line;
                    while ((line = in.readLine()) != null) {

                        if (line.equals("EOF")) {
                            break;
                        }

                        System.out.println(
                            "Client " + clientId + " received: " + line
                        );
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (in != null) in.close();
                        if (socket != null) socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
