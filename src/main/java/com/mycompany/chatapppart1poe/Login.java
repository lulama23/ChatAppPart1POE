package com.mycompany.chatapppart1poe;

public class Login {
    private String storedUsername;
    private String storedPassword;

    public Login(String storedUsername, String storedPassword) {
        this.storedUsername = storedUsername;
        this.storedPassword = storedPassword;
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return storedUsername.equals(enteredUsername) && storedPassword.equals(enteredPassword);
    }

    public String returnLoginStatus(boolean success, String firstName, String lastName) {
        if (success) {
            return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

// Updated for commit #3