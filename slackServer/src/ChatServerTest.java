import static org.junit.Assert.*;
import org.junit.*;

import java.io.*;
import java.net.*;

public class ChatServerTest {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8282;

    @Test
    public void testServerConnection() {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            assertNotNull(socket);
            assertNotNull(in);
            assertNotNull(out);

            assertTrue(socket.isConnected());

            String serverResponse = in.readLine();
            assertNotNull(serverResponse);
            assertEquals("Connected to the chat server. Enter your username:", serverResponse);

        } catch (IOException e) {
            fail("Failed to connect to the server: " + e.getMessage());
        }
    }

    // Add more test cases as needed...
}
