import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    private static final int PORT = 8282;
    private static final Map<String, Socket> clients = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                Thread clientThread = new ClientHandler(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                out.println("Connected to the chat server. Enter your username:");
                username = in.readLine();
                clients.put(username, clientSocket);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(username + ": " + inputLine);
                    if (inputLine.startsWith("#@")) {
                        String[] parts = inputLine.split("\\s+", 2);
                        String recipient = parts[0].substring(2);
                        String message = parts[1];
                        sendMessageTo(recipient, username + ": " + message);
                    } else {
                        sendMessageToAll(username + ": " + inputLine);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendMessageTo(String recipient, String message) {
            Socket recipientSocket = clients.get(recipient);
            if (recipientSocket != null) {
                try {
                    PrintWriter recipientOut = new PrintWriter(recipientSocket.getOutputStream(), true);
                    recipientOut.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                out.println("User " + recipient + " is not connected.");
            }
        }

        private void sendMessageToAll(String message) {
            for (Socket clientSocket : clients.values()) {
                try {
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    writer.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
