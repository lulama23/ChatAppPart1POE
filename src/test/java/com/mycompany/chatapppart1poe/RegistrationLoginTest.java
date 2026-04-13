package com.mycompany.chatapppart1poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationLoginTest {

    @Test
    public void testUsernameCorrectlyFormatted_assertEquals() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        Login login = new Login(reg.getUsername(), reg.getPassword());
        boolean success = login.loginUser("kyl_1", "Ch&sec@ke99!");
        String message = login.returnLoginStatus(success, reg.getFirstName(), reg.getLastName());
        assertEquals("Welcome John Doe it is great to see you again.", message);
    }

    @Test
    public void testUsernameIncorrectlyFormat_assertEquals() {
        Registration reg = new Registration("John", "Doe", "kyle!!!!!!!", "Ch&sec@ke99!", "+27838968976");
        String message = reg.getUsernameStatusMessage();
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", message);
    }

    @Test
    public void testPasswordMeetsComplexity_assertEquals() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        String message = reg.getPasswordStatusMessage();
        assertEquals("Password successfully captured.", message);
    }

    @Test
    public void testPasswordDoesNotMeetComplexity_assertEquals() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "password", "+27838968976");
        String message = reg.getPasswordStatusMessage();
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", message);
    }

    @Test
    public void testCellPhoneCorrectlyFormatted_assertEquals() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        String message = reg.getCellPhoneStatusMessage();
        assertEquals("Cell phone number successfully added.", message);
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted_assertEquals() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "08966553");
        String message = reg.getCellPhoneStatusMessage();
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", message);
    }

    @Test
    public void testLoginSuccessful() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        Login login = new Login(reg.getUsername(), reg.getPassword());
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        Login login = new Login(reg.getUsername(), reg.getPassword());
        assertFalse(login.loginUser("wrong", "wrong"));
    }

    @Test
    public void testUsernameCorrectlyFormatted_assertTrue() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertTrue(reg.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted_assertFalse() {
        Registration reg = new Registration("John", "Doe", "kyle!!!!!!!", "Ch&sec@ke99!", "+27838968976");
        assertFalse(reg.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity_assertTrue() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertTrue(reg.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity_assertFalse() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "password", "+27838968976");
        assertFalse(reg.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted_assertTrue() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertTrue(reg.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted_assertFalse() {
        Registration reg = new Registration("John", "Doe", "kyl_1", "Ch&sec@ke99!", "08966553");
        assertFalse(reg.checkCellPhoneNumber());
    }
}

// Updated for commit #5