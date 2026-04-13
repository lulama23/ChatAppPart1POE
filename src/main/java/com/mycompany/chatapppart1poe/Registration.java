package com.mycompany.chatapppart1poe;

import java.util.regex.Pattern;

public class Registration {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    public Registration(String firstName, String lastName, String username,
                        String password, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) return false;
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber() {
        String regex = "^\\+27[0-9]{9}$";
        return cellPhoneNumber != null && Pattern.matches(regex, cellPhoneNumber);
    }

    public String getUsernameStatusMessage() {
        return checkUserName() ? "Username successfully captured." 
            : "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    public String getPasswordStatusMessage() {
        return checkPasswordComplexity() ? "Password successfully captured." 
            : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    public String getCellPhoneStatusMessage() {
        return checkCellPhoneNumber() ? "Cell phone number successfully added." 
            : "Cell phone number incorrectly formatted or does not contain international code.";
    }

    public String registerUser() {
        if (!checkUserName()) return getUsernameStatusMessage();
        if (!checkPasswordComplexity()) return getPasswordStatusMessage();
        if (!checkCellPhoneNumber()) return getCellPhoneStatusMessage();
        return "User successfully registered.";
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}