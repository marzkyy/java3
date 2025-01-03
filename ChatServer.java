import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345; // The port on which the server listens for incoming connections
    private static Map<String, PrintWriter> clients = new HashMap<>(); // Map to store the connected clients with their usernames and output writers

    public static void main(String[] args) {
        System.out.println("Chat server is running...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start(); // Accept client connections and start a new thread for each
            }
        } catch (IOException e) {
            System.out.println("Server encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket; 
        private String userName;       
        private PrintWriter out;       
        private BufferedReader in;     

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Ask for the client's username only if it has not been sent already
                out.println("Enter your name (or press Enter to use default 'User'):");
                userName = in.readLine().trim();

                // If the name is empty, assign a default name (you can modify this if needed)
                if (userName.isEmpty()) {
                    userName = "User" + (int) (Math.random() * 1000); 
                }

                // Ensure the username is unique
                while (clients.containsKey(userName)) {
                    out.println("The name '" + userName + "' is already taken. Please choose another name:");
                    userName = in.readLine().trim();
                }

                // Add the client to the list of connected clients
                synchronized (clients) {
                    clients.put(userName, out);
                }

                // Notify all clients that the new user has joined
                broadcast(userName + " has joined the chat.");

                // Handle incoming messages from the client
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.trim().isEmpty()) {
                        out.println("Empty messages are not allowed.");
                        continue;
                    }

                    // Handle name change command
                    if (message.startsWith("/name ")) {
                        String newName = message.substring(6).trim();
                        if (!newName.isEmpty() && !clients.containsKey(newName)) {
                            String oldName = userName;
                            userName = newName;
                            // Update the username in the clients map
                            synchronized (clients) {
                                clients.put(userName, clients.remove(oldName));
                            }
                            out.println("Your name has been changed to " + userName);
                            broadcast(oldName + " has changed their name to " + userName);
                        } else {
                            out.println("Please provide a valid name or the name is already taken.");
                        }
                    } else if (message.equals("/disconnect") || message.equals("/exit")) {
                        out.println("You have disconnected.");
                        break;
                    } else if (message.length() > 200) {
                        out.println("Message is too long. Keep it under 200 characters.");
                    } else {
                        broadcast(userName + ": " + message);
                    }
                }
            } catch (IOException e) {
                System.out.println(userName + " disconnected.");
            } finally {
                // Remove the client from the list when they disconnect
                synchronized (clients) {
                    clients.remove(userName);
                }
                broadcast(userName + " has left the chat.");
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket for " + userName + ": " + e.getMessage());
                }
            }
        }

        private void broadcast(String message) {
            System.out.println(message); 
            synchronized (clients) {
                for (PrintWriter writer : clients.values()) {
                    writer.println(message); 
                }
            }
        }
    }
}
