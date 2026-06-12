package com.mycompany.chatapppart1poe;

import java.util.Scanner;
import java.util.List;

public class MainApp {
    private static Registration registeredUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Chat App - Part 3 =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerUser(scanner);
                case 2 -> loginUser(scanner);
                case 3 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (choice != 3);
        scanner.close();
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter username (underscore, max 5 chars): ");
        String username = scanner.nextLine();
        System.out.print("Enter password (min 8 chars, 1 capital, 1 number, 1 special): ");
        String password = scanner.nextLine();
        System.out.print("Enter SA cell number (e.g., +27831234567): ");
        String cell = scanner.nextLine();

        Registration reg = new Registration(firstName, lastName, username, password, cell);
        String result = reg.registerUser();
        System.out.println(result);

        if (result.equals("User successfully registered.")) {
            registeredUser = reg;
        }
    }

    private static void loginUser(Scanner scanner) {
        if (registeredUser == null) {
            System.out.println("No user registered yet. Please register first.");
            return;
        }
        System.out.print("Enter username: ");
        String enteredUser = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPass = scanner.nextLine();

        Login login = new Login(registeredUser.getUsername(), registeredUser.getPassword());
        boolean success = login.loginUser(enteredUser, enteredPass);
        String message = login.returnLoginStatus(success,
                registeredUser.getFirstName(),
                registeredUser.getLastName());
        System.out.println(message);

        if (success) {
            showChatMenu(scanner);
        }
    }

    private static void showChatMenu(Scanner scanner) {
        System.out.println("\nWelcome to QuickChat.");
        int option;
        do {
            System.out.println("\n--- Chat Menu ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages (Coming Soon)");
            System.out.println("3. Stored Messages (Reports)");
            System.out.println("4. Quit");
            System.out.print("Choose: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> sendMessages(scanner);
                case 2 -> System.out.println("Coming Soon.");
                case 3 -> storedMessagesMenu(scanner);
                case 4 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 4);
    }

    private static void sendMessages(Scanner scanner) {
        System.out.print("How many messages do you want to enter? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();

        int totalSent = 0;
        for (int i = 1; i <= numMessages; i++) {
            System.out.println("\n--- Message " + i + " ---");
            System.out.print("Enter recipient cell number (e.g., +27831234567): ");
            String recipient = scanner.nextLine();
            String recipientCheck = Message.checkRecipientCell(recipient);
            if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                System.out.println(recipientCheck);
                continue;
            }

            System.out.print("Enter message (max 250 characters): ");
            String text = scanner.nextLine();
            if (text.length() > 250) {
                int excess = text.length() - 250;
                System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
                continue;
            } else {
                System.out.println("Message ready to send.");
            }

            Message msg = new Message(recipient, text, i);
            System.out.println("Message ID generated: " + msg.getMessageID());

            System.out.println("Choose option: 1) Send Message  2) Store Message  3) Disregard Message");
            int action = scanner.nextInt();
            scanner.nextLine();
            String result = msg.sendMessageOption(action);
            System.out.println(result);

            String status;
            if (action == 1) {
                status = "SENT";
                totalSent++;
                System.out.println(msg.printMessage());
            } else if (action == 2) {
                status = "STORED";
            } else {
                status = "DISREGARDED";
                System.out.println("Message discarded.");
            }
            MessageStore.addMessage(text, msg.getMessageHash(), msg.getMessageID(), recipient, status);
        }
        System.out.println("\nTotal number of messages sent (this session): " + totalSent);
        System.out.println("Overall total messages sent (across all runs): " + Message.returnTotalMessages());

        try {
            MessageStore.loadStoredMessagesFromJSON();
            System.out.println("Stored messages loaded from file.");
        } catch (Exception e) {
            System.out.println("Could not load stored messages: " + e.getMessage());
        }
    }

    private static void storedMessagesMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n===== STORED MESSAGES MENU =====");
            System.out.println("1. Display sender & recipient of all stored messages");
            System.out.println("2. Display longest stored message");
            System.out.println("3. Search by Message ID");
            System.out.println("4. Search all messages for a recipient");
            System.out.println("5. Delete a message by Message Hash");
            System.out.println("6. Display full report");
            System.out.println("7. Back to Chat Menu");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    MessageStore.displaySenderAndRecipient(registeredUser.getFirstName(), registeredUser.getLastName());
                    break;
                case 2:
                    String longest = MessageStore.getLongestStoredMessage();
                    System.out.println("Longest stored message: " + (longest != null ? longest : "None"));
                    break;
                case 3:
                    System.out.print("Enter Message ID: ");
                    String id = scanner.nextLine();
                    String result = MessageStore.searchByMessageID(id);
                    if (result != null) System.out.println(result);
                    else System.out.println("Message ID not found.");
                    break;
                case 4:
                    System.out.print("Enter recipient cell number: ");
                    String phone = scanner.nextLine();
                    List<String> msgs = MessageStore.searchByRecipient(phone);
                    if (msgs.isEmpty()) System.out.println("No messages for that recipient.");
                    else {
                        System.out.println("Messages for " + phone + ":");
                        for (String m : msgs) System.out.println("- " + m);
                    }
                    break;
                case 5:
                    System.out.print("Enter Message Hash to delete: ");
                    String hash = scanner.nextLine();
                    boolean deleted = MessageStore.deleteByMessageHash(hash);
                    System.out.println(deleted ? "Message successfully deleted." : "Hash not found.");
                    break;
                case 6:
                    MessageStore.displayFullReport();
                    break;
                case 7:
                    System.out.println("Returning to Chat Menu.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 7);
    }
}