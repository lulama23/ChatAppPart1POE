package com.mycompany.chatapppart1poe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class Part3Test {
    private static MessageStore store;

    @BeforeAll
    static void setUp() {
        store = new MessageStore();
        // Simulate adding test data from the spec (messages 1-4)
        MessageStore.addMessage("Did you get the cake?", "hash1", "id1", "+27834557896", "SENT");
        MessageStore.addMessage("Where are you? You are late! I have asked you to be on time.", "hash2", "id2", "+27838884567", "STORED");
        MessageStore.addMessage("Yohoooo, I am at your gate.", "hash3", "id3", "+27834484567", "DISREGARDED");
        MessageStore.addMessage("It is dinner time !", "hash4", "id4", "0838884567", "SENT");
        MessageStore.addMessage("Ok, I am leaving without you.", "hash5", "id5", "+27838884567", "STORED");
    }

    @Test
    public void testSentMessagesArrayContainsCorrectTexts() {
        List<String> sent = MessageStore.getSentMessages();
        assertTrue(sent.contains("Did you get the cake?"));
        assertTrue(sent.contains("It is dinner time !"));
    }

    @Test
    public void testLongestStoredMessage() {
        String longest = MessageStore.getLongestStoredMessage();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchByMessageID() {
        String result = MessageStore.searchByMessageID("id4");
        assertNotNull(result);
        assertTrue(result.contains("It is dinner time !"));
    }

    @Test
    public void testSearchByRecipient() {
        List<String> msgs = MessageStore.searchByRecipient("+27838884567");
        assertEquals(2, msgs.size());
        assertTrue(msgs.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(msgs.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByMessageHash() {
        boolean deleted = MessageStore.deleteByMessageHash("hash2");
        assertTrue(deleted);
        // Now check that the message is gone
        assertNull(MessageStore.searchByMessageID("id2"));
    }

    // Note: Display report test would require capturing console output; we skip for brevity.
}