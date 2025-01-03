import java.io.IOException;
import java.util.Scanner;

public class ChatClientUI {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;

        try (Scanner scanner = new Scanner(System.in)) {
            ChatClient client = new ChatClient(serverAddress, serverPort);

            // Ask for the user's name once and set it
            System.out.print("Enter your name (or press Enter to use default 'User'): ");
            String userName = scanner.nextLine().trim();
            userName = client.setUserName(userName); // Set or generate default name

            // Connect to the server and set up message handler
            client.connect(message -> {
                // This block will run when a message is received from the server
                System.out.println(message); // Print server messages
            });

            // Send the name to the server after establishing the connection, only once
            client.sendUserNameOnce(userName);

            // After connecting, print instructions
            System.out.println("Connected to the chat server. Type your messages below:");
            System.out.println("You can change your name anytime by typing '/name <new_name>'.");
            System.out.println("Type '/disconnect' to disconnect from the chat.");
            System.out.println("Type '/exit' to quit the chat application.");

            // Main loop to send messages to the server
            String userMessage;
            while (scanner.hasNextLine()) {
                userMessage = scanner.nextLine().trim();

                // Handle commands
                if (userMessage.startsWith("/name ")) {
                    // Change name command
                    String newName = userMessage.substring(6).trim();
                    if (!newName.isEmpty()) {
                        // Send the request to change name to the server first
                        System.out.println("Trying to change your name to " + newName + "...");
                        client.sendNameChange(newName);
                    } else {
                        System.out.println("Please provide a valid name.");
                    }
                } else if (userMessage.equals("/disconnect") || userMessage.equals("/exit")) {
                    // Disconnect or exit
                    client.sendRegularMessage("/disconnect");
                    System.out.println("Disconnecting from the chat...");
                    client.disconnect();
                    break;
                } else if (client.isValidMessage(userMessage)) {
                    // Send regular message if valid
                    client.sendRegularMessage(userMessage);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to connect to the server: " + e.getMessage());
        }
    }
}
