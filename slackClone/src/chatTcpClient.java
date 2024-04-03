import java.io.*;
import java.net.*;

public class chatTcpClient {
    private Socket csock;
    private final String SERVER_ADDRESS = "localhost";
    private final int PORT = 8282;

    public chatTcpClient() {
        try {
            csock = new Socket(SERVER_ADDRESS, PORT);
            System.out.println("Connected to the chat server.");

            // Start a new thread to listen for messages from the server
            Thread listenThread = new Thread(this::listenMsgFromServer);
            listenThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listenMsgFromServer() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(csock.getInputStream()));
            String serverResponse;
            while (true) {
                // Check if there is data available to be read
                if (in.ready()) {
                    serverResponse = in.readLine();
                    if (serverResponse != null) {
                        System.out.println(serverResponse); // Print messages received from server
                    }
                } else {
                    // Sleep for a short period to avoid busy-waiting
                    Thread.sleep(1000); // Adjust the sleep duration as needed
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String sz_msg) {
        try {
            PrintWriter out = new PrintWriter(csock.getOutputStream(), true);
            out.println(sz_msg); // Send message to server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
