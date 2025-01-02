// ChatClient.java
import java.io.*;
import java.net.*;
import java.util.*;

// The ChatClient class handles the client-side functionality of the chat application.
// It connects to the server, sends messages, and displays messages from other clients.
public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost"; // Server address (change if server is remote).
    private static final int SERVER_PORT = 12345; // Port number to connect to the server.

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); // Establish connection to the server.
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Output stream to send messages.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Input stream to receive messages.
            Scanner scanner = new Scanner(System.in); // Scanner for user input.
        ) {
            System.out.println("Connected to the chat server.");

            // Create a thread to listen for and display messages from the server.
            Thread listenerThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage); // Display server messages.
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from the server.");
                }
            });

            listenerThread.start();

            // Read user input and send it to the server.
            String userMessage;
            while (scanner.hasNextLine()) {
                userMessage = scanner.nextLine();
                if (userMessage.trim().isEmpty()) {
                    System.out.println("Cannot send an empty message.");
                    continue;
                }
                if (userMessage.length() > 200) {
                    System.out.println("Message too long. Keep it under 200 characters.");
                    continue;
                }
                out.println(userMessage);
            }
        } catch (IOException e) {
            // Handle errors related to the client socket.
            System.out.println("Unable to connect to the server: " + e.getMessage());
        }
    }
}
