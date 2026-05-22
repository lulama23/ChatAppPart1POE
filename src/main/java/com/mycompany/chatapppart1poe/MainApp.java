package com.mycompany.chatapppart1poe;

import java.util.Scanner;

public class MainApp {
    private static Registration registeredUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Chat App - Part 1 =====");
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

    private static void registerUser(Scanner scanner) { /* same as before */ }

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
            // After successful login, show chat menu
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
            System.out.println("3. Quit");
            System.out.print("Choose: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> sendMessages(scanner);
                case 2 -> System.out.println("Coming Soon.");
                case 3 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 3);
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
            // Validate recipient
            String recipientCheck = Message.checkRecipientCell(recipient);
            if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                System.out.println(recipientCheck);
                System.out.println("Skipping this message.");
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

            // Create message object (auto ID and hash)
            Message msg = new Message(recipient, text, i);
            System.out.println("Message ID generated: " + msg.getMessageID());

            // Ask user: send, store, or disregard
            System.out.println("Choose option: 1) Send Message  2) Store Message  3) Disregard Message");
            int action = scanner.nextInt();
            scanner.nextLine();
            String result = msg.sendMessageOption(action);
            System.out.println(result);

            if (action == 1) {
                totalSent++;
                System.out.println(msg.printMessage());
            } else if (action == 3) {
                System.out.println("Message discarded.");
            }
        }
        System.out.println("\nTotal number of messages sent: " + totalSent);
        System.out.println("Overall total messages sent (across all runs): " + Message.returnTotalMessages());
    }
}
//Extend Main with QuickChat menu, message loop, and send/store/disregard logic