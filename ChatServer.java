// ChatServer.java
import java.io.*;
import java.net.*;
import java.util.*;

// The ChatServer class manages the server-side functionality of the chat application.
// It handles incoming client connections and message broadcasting.
public class ChatServer {
    private static final int PORT = 12345; // The port number the server will listen on.
    private static Map<String, PrintWriter> clients = new HashMap<>(); // Stores connected clients and their output streams.

    public static void main(String[] args) {
        System.out.println("Chat server is running...");

        // Start the server and listen for client connections.
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Create a new thread for each incoming client connection.
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            // Handle errors related to the server socket.
            System.out.println("Server encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // The ClientHandler class handles communication with a single client.
    private static class ClientHandler extends Thread {
        private Socket socket; // The socket for client communication.
        private String clientId; // A unique ID assigned to the client.
        private PrintWriter out; // The output stream to send messages to the client.

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader in = new BufferedReader(isr);
            ) {
                out = new PrintWriter(socket.getOutputStream(), true);

                // Assign a unique user ID and add the client to the list.
                synchronized (clients) {
                    clientId = "User" + (clients.size() + 1);
                    clients.put(clientId, out);
                }

                // Notify all clients that a new user has joined.
                broadcast(clientId + " has joined the chat.");

                // Read and broadcast messages from the client.
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.trim().isEmpty()) {
                        out.println("Empty messages are not allowed.");
                        continue;
                    }
                    if (message.length() > 200) {
                        out.println("Message is too long. Keep it under 200 characters.");
                        continue;
                    }
                    broadcast(clientId + ": " + message);
                }
            } catch (IOException e) {
                System.out.println(clientId + " disconnected.");
            } finally {
                // Remove the client from the list and notify others.
                synchronized (clients) {
                    clients.remove(clientId);
                }
                broadcast(clientId + " has left the chat.");
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket for " + clientId + ": " + e.getMessage());
                }
            }
        }

        // Broadcast a message to all connected clients.
        private void broadcast(String message) {
            System.out.println(message); // Log the message on the server console.
            synchronized (clients) {
                for (PrintWriter writer : clients.values()) {
                    writer.println(message);
                }
            }
        }
    }
}