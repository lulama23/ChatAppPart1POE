package com.mycompany.chatapppart1poe;

import java.util.Scanner;

public class MainApp {
    private static Registration registeredUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Welcome! =====");
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
    }
}

// Updated for commit #4