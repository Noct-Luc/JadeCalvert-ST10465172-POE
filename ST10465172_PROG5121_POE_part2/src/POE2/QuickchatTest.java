package POE2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class QuickchatTest {

    // Simulated message storage
    private List<Message> messages;

    @Before
    public void setUp() {
        messages = new ArrayList<>();

        // Test data: 4 messages
        messages.add(new Message("MSG001", "+27123456789", "Did you get the cake?"));
        messages.add(new Message("MSG002", "+27838884567", "It is dinner time!"));
        messages.add(new Message("MSG003", "+27838884567", "Where are you? You are late! I have asked you to be on time."));
        messages.add(new Message("MSG004", "0838884567", "Ok, I am leaving without you."));
    }

    @Test
    public void testMessagesArrayContent() {
        // Test that messages 1 and 2 contain expected text
        assertEquals("Did you get the cake?", messages.get(0).getContent());
        assertEquals("It is dinner time!", messages.get(1).getContent());
    }

    @Test
    public void testDisplayLongestMessage() {
        // Find longest message content from messages 1-4
        String longestMessage = messages.stream()
                .map(Message::getContent)
                .max((m1, m2) -> Integer.compare(m1.length(), m2.length()))
                .orElse("");
        assertEquals("Where are you? You are late! I have asked you to be on time.", longestMessage);
    }

    @Test
    public void testSearchMessageById() {
        // Search message with ID MSG004 ("0838884567" recipient)
        Message found = messages.stream()
                .filter(m -> m.getMessageId().equals("MSG004"))
                .findFirst()
                .orElse(null);

        assertNotNull(found);
        assertEquals("Ok, I am leaving without you.", found.getContent());
    }

    @Test
    public void testSearchMessagesByRecipient() {
        // Search all messages for recipient +27838884567
        List<Message> foundMessages = messages.stream()
                .filter(m -> m.getRecipient().equals("+27838884567"))
                .collect(Collectors.toList());

        assertEquals(2, foundMessages.size());
        List<String> expectedContents = List.of(
                "It is dinner time!",
                "Where are you? You are late! I have asked you to be on time."
        );
        for (Message m : foundMessages) {
            assertTrue(expectedContents.contains(m.getContent()));
        }
    }

    @Test
    public void testDeleteMessageByHash() {
        // Let's say Message 3 has hash "MSG003HASH" for example
        // Simulate assigning hash
        messages.get(2).setHash("MSG003HASH");

        // Delete message by hash "MSG003HASH"
        boolean removed = messages.removeIf(m -> "MSG003HASH".equals(m.getHash()));

        assertTrue(removed);

        // Check message 3 is no longer in list
        assertFalse(messages.stream()
                .anyMatch(m -> "MSG003HASH".equals(m.getHash())));

        // Confirm message content was "Where are you? You are late! I have asked you to be on time."
    }

    @Test
    public void testDisplayReport() {
        // Simulate report output: list message hash, recipient, content
        StringBuilder report = new StringBuilder();
        for (Message m : messages) {
            report.append("Hash: ").append(m.getHash() != null ? m.getHash() : "NoHash")
                    .append(", Recipient: ").append(m.getRecipient())
                    .append(", Message: ").append(m.getContent())
                    .append("\n");
        }

        String reportStr = report.toString();

        // Check report contains all message info
        for (Message m : messages) {
            assertTrue(reportStr.contains(m.getRecipient()));
            assertTrue(reportStr.contains(m.getContent()));
        }
    }

    // Stub Message class for test purpose
    static class Message {
        private final String messageId;
        private final String recipient;
        private final String content;
        private String hash;

        public Message(String messageId, String recipient, String content) {
            this.messageId = messageId;
            this.recipient = recipient;
            this.content = content;
            this.hash = null;
        }

        public String getMessageId() {
            return messageId;
        }

        public String getRecipient() {
            return recipient;
        }

        public String getContent() {
            return content;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }
}
