package POE3;
import java.io.FileWriter; // allows data to be saved as a text file
import java.io.IOException;
import java.util.Random; // allows for random number generation
import java.util.Scanner; // Allows user input

public class Quickchat {

    private static int messageCount = 0;
    public static void startChat(String sender) {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Welcome to Quick Chat");
            System.out.println("Select transaction:");
            System.out.println("Option 1 - Select Chat options (in order to use chat options, you have to send a message first)");
            System.out.println("Option 2 - Send Quickchat");
            System.out.println("Option 3 - Quit");

            System.out.print("Enter your choice (1, 2, or 3): ");
            int choice = scanner.nextInt();

            String recipient = "";

            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("You selected: Chat options");
                    System.out.println("1: View sent and received messages");
                    System.out.println("2: View the longest sent message");
                    System.out.println("3: Search for message ID");
                    System.out.println("4: Search for messages sent to a certain member");
                    System.out.println("5: Delete messages");
                    System.out.println("6: View message report");
                    System.out.println("7: Return to main menu");

                    int sub_sub_Choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (sub_sub_Choice) {
                        case 1:
                            Manager.displaySendersAndRecipients();
                            break;
                        case 2:
                            Manager.displayLongestMessage();
                            break;
                        case 3:
                            System.out.print("Enter Message ID to search: ");
                            String id = scanner.nextLine();
                            Manager.searchByMessageId(id);
                            break;
                        case 4:
                            System.out.print("Enter Recipient number to search messages for: ");
                            String searchRecipient = scanner.nextLine();
                            Manager.searchMessagesByRecipient(searchRecipient);
                            break;
                        case 5:
                            System.out.print("Enter Message Hash to delete: ");
                            String hash = scanner.nextLine();
                            Manager.deleteMessageByHash(hash);
                            break;
                        case 6:
                            Manager.displayFullReport();
                            break;
                        case 7:
                            System.out.println("Returning to main menu");
                            break;
                        default:
                            System.out.println("Invalid sub-option. Please select between 1 and 7.");
                    } break;



                case 2:
                    System.out.println("You selected: Send Quickchat");


                    do {
                        System.out.print("Enter your number (must start with +27 and be exactly 12 characters): ");
                        recipient = scanner.nextLine();
                    } while (!(recipient.startsWith("+27") && recipient.length() == 12));

                    System.out.print("Enter your Quickchat (must be 250 characters or less): ");
                    String message = scanner.nextLine();

                    if (message.length() > 250) {
                        System.out.println("Please enter a Quickchat of less than 250 characters.");
                        return;
                    }

                    String messageId = generateMessageId();
                    int currentMessageCount = ++messageCount;
                    String messageHash = generateMessageHash(messageId, currentMessageCount, message);

                    System.out.println("Message Hash: " + messageHash);

                    System.out.println("Choose an option:");
                    System.out.println("Option 1 - Send Quickchat");
                    System.out.println("Option 2 - Disregard Quickchat");
                    System.out.println("Option 3 - Store Quickchat to send later");

                    int subChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (subChoice) {
                        case 1:

                            Manager.addMessage( sender, messageId, recipient, message, messageHash);
                            System.out.println("Message sent and stored in system.");
                            break;
                        case 2:
                            System.out.println("Message discarded");
                            break;
                        case 3:
                            storeMessageToTextFile(messageId, recipient, message, messageHash);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;

                case 3:
                    System.out.println("Quitting... Goodbye!");
                    return;


                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }

        }
    }
    // allows for unique identification
    private static String generateMessageId() {
        Random random = new Random();
        String id = "";

        for (int i = 0; i < 10; i++) {
            id += random.nextInt(10);
        }
        return id;
    }
    // allows for unique identification
    private static String generateMessageHash(String messageId, int count, String message) {
        String[] words = message.trim().split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;

        return messageId.substring(0, 2) + ":" + count + ":" + first.toUpperCase() + last.toUpperCase();
    }

    // Saves the messages as a text file
    private static void storeMessageToTextFile(String id, String recipient, String message, String hash) {
        try (FileWriter file = new FileWriter("stored_message.txt", true)) {
            file.write("Message ID: " + id + "\n");
            file.write("Recipient: " + recipient + "\n");
            file.write("Message: " + message + "\n");
            file.write("Hash: " + hash + "\n");
            file.write("-----\n"); // Separator
            System.out.println("Message stored successfully in text file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}