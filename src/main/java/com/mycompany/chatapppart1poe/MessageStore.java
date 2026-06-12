package com.mycompany.chatapppart1poe;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageStore {
    // Parallel arrays
    private static List<String> sentMessages = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();
    private static List<String> recipients = new ArrayList<>(); // Store recipient per message

    // Add a message to appropriate array based on its status
    public static void addMessage(String messageText, String hash, String id, String recipient, String status) {
        messageHashes.add(hash);
        messageIDs.add(id);
        recipients.add(recipient);
        
        switch (status) {
            case "SENT":
                sentMessages.add(messageText);
                break;
            case "STORED":
                storedMessages.add(messageText);
                break;
            case "DISREGARDED":
                disregardedMessages.add(messageText);
                break;
            default:
                // not stored in any special array
        }
    }

    // Read stored messages from JSON file and populate storedMessages array
    public static void loadStoredMessagesFromJSON() throws IOException, ParseException {
        storedMessages.clear(); // clear previous
        File file = new File("messages.json");
        if (!file.exists()) return;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            JSONParser parser = new JSONParser();
            while ((line = reader.readLine()) != null) {
                JSONObject obj = (JSONObject) parser.parse(line);
                String status = (String) obj.get("status");
                if ("STORED".equals(status)) {
                    String messageText = (String) obj.get("messageText");
                    String hash = (String) obj.get("messageHash");
                    String id = (String) obj.get("messageID");
                    String recipient = (String) obj.get("recipient");
                    storedMessages.add(messageText);
                    // Also add to other parallel arrays for completeness
                    messageHashes.add(hash);
                    messageIDs.add(id);
                    recipients.add(recipient);
                }
            }
        }
    }

    // Display sender (current logged-in user) and recipient of all stored messages
    public static void displaySenderAndRecipient(String currentUserFirstName, String currentUserLastName) {
        System.out.println("Stored Messages - Sender and Recipient:");
        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println((i+1) + ". Sender: " + currentUserFirstName + " " + currentUserLastName +
                               ", Recipient: " + recipients.get(i));
        }
    }

    // Find and return the longest stored message text
    public static String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) return null;
        String longest = storedMessages.get(0);
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    // Search by message ID – returns recipient and message (or null)
    public static String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return "Recipient: " + recipients.get(i) + "\nMessage: " + 
                       (i < sentMessages.size() ? sentMessages.get(i) : 
                        i < storedMessages.size() ? storedMessages.get(i) : 
                        i < disregardedMessages.size() ? disregardedMessages.get(i) : "Unknown");
            }
        }
        return null;
    }

    // Search all messages for a particular recipient (cell number)
    public static List<String> searchByRecipient(String phoneNumber) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(phoneNumber)) {
                String msg = (i < sentMessages.size() ? sentMessages.get(i) :
                              i < storedMessages.size() ? storedMessages.get(i) :
                              i < disregardedMessages.size() ? disregardedMessages.get(i) : "?");
                result.add(msg);
            }
        }
        return result;
    }

    // Delete a message by its message hash (remove from all parallel arrays)
    public static boolean deleteByMessageHash(String hash) {
        int index = -1;
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                index = i;
                break;
            }
        }
        if (index == -1) return false;
        
        // Remove from all parallel arrays
        messageHashes.remove(index);
        messageIDs.remove(index);
        recipients.remove(index);
        if (index < sentMessages.size()) sentMessages.remove(index);
        else if (index - sentMessages.size() < storedMessages.size()) 
            storedMessages.remove(index - sentMessages.size());
        else 
            disregardedMessages.remove(index - sentMessages.size() - storedMessages.size());
        return true;
    }

    // Display full report of all stored messages
    public static void displayFullReport() {
        System.out.println("\n===== STORED MESSAGES REPORT =====");
        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println("Message #" + (i+1));
            System.out.println("Hash: " + messageHashes.get(i));
            System.out.println("Recipient: " + recipients.get(i));
            System.out.println("Message: " + storedMessages.get(i));
            System.out.println("------------------------------");
        }
    }

    // Getters for arrays (for testing)
    public static List<String> getSentMessages() { return sentMessages; }
    public static List<String> getStoredMessages() { return storedMessages; }
    public static List<String> getDisregardedMessages() { return disregardedMessages; }
    public static List<String> getMessageHashes() { return messageHashes; }
    public static List<String> getMessageIDs() { return messageIDs; }
    public static List<String> getRecipients() { return recipients; }
}
//Complete MessageStorage