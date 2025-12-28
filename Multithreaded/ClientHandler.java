import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;



class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader fileReader = null;
        PrintWriter out = null;

        try {
            fileReader = new BufferedReader(new FileReader("data.txt"));

            out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = fileReader.readLine()) != null) {
                out.println(line);
            }

            out.println("EOF");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close everything
            try {
                if (fileReader != null) fileReader.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
