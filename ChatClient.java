import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class ChatClient {
    private final String serverAddress;
    private final int serverPort;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Consumer<String> messageHandler;
    private boolean hasSentName = false; // Track if the name has already been sent

    public ChatClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect(Consumer<String> messageHandler) throws IOException {
        this.messageHandler = messageHandler;
        socket = new Socket(serverAddress, serverPort);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Start a thread to listen for messages from the server
        new Thread(() -> {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    // Ignore the "Enter your name" message if the name has already been sent
                    if (hasSentName || !message.contains("Enter your name")) {
                        messageHandler.accept(message); // Print server messages
                    }
                }
            } catch (IOException e) {
                messageHandler.accept("Disconnected from the server.");
            }
        }).start();
    }

    public void sendRegularMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    public void sendNameChange(String newName) {
        if (!newName.isEmpty()) {
            sendRegularMessage("/name " + newName); // Send name change request
        } else {
            System.out.println("Please provide a valid name.");
        }
    }

    public boolean isValidMessage(String message) {
        if (message.trim().isEmpty()) {
            System.out.println("Cannot send an empty message.");
            return false;
        } else if (message.length() > 200) {
            System.out.println("Message too long. Keep it under 200 characters.");
            return false;
        }
        return true;
    }

    public void disconnect() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close(); // Properly close the socket connection
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public String setUserName(String userName) {
        if (userName.isEmpty()) {
            return "User" + (int) (Math.random() * 1000); // Default name if empty
        }
        return userName;
    }

    public boolean isSocketClosed() {
        return socket == null || socket.isClosed();
    }

    // Ensure the username is sent only once
    public void sendUserNameOnce(String userName) {
        if (!hasSentName) {
            sendRegularMessage(userName); // Send name only once
            hasSentName = true; // Mark that the name has been sent
        }
    }
}
