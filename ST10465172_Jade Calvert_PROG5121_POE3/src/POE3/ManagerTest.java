package POE3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Helps to reset static fields
    private void resetManagerState() {
            try {
                java.lang.reflect.Field messageCount = Manager.class.getDeclaredField("messageCount");
                messageCount.setAccessible(true);
                messageCount.set(null, 0);
            } catch (Exception e) {
                throw new RuntimeException("Failed to reset Manager state", e);
            }
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        resetManagerState();

        // Message 1 - Sent
        Manager.addMessage("Developer", "msg001", "+27834557896", "Did you get the cake?", "hash001");

        // Message 2 - Stored
        Manager.addMessage("Developer", "msg002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "hash002");

        // Message 3 - Disregard

        // Message 4 - Sent
        Manager.addMessage("0838884567", "msg003", "Developer", "It is dinner time !", "hash003");

        // Message 5 - Stored
        Manager.addMessage("Developer", "msg004", "+27838884567", "Ok, I am leaving without you.", "hash004");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void testSentMessagesArrayContainsExpectedMessages() {
        outContent.reset();
        Manager.displaySendersAndRecipients();
        String output = outContent.toString();

        // Expect only 4 messages (message 3 deleted)
        assertTrue(output.contains("Sender: Developer, Recipient: +27834557896"));
        assertTrue(output.contains("Sender: Developer, Recipient: +27838884567"));
        assertTrue(output.contains("Sender: 0838884567, Recipient: Developer"));
        assertTrue(output.contains("Sender: Developer, Recipient: +27838884567"));
        assertFalse(output.contains("Yohoooo, I am at your gate."));
    }

    @Test
    void testDeveloperEntryOnlyReturnsSentMessages() {
        outContent.reset();
        Manager.displayFullReport();
        String output = outContent.toString();

        assertTrue(output.contains("Message: Did you get the cake?"));
        assertTrue(output.contains("Message: It is dinner time !"));
        assertFalse(output.contains("Yohoooo, I am at your gate."));
    }

    @Test
    void testLongestMessageReturnedCorrectly() {
        outContent.reset();
        Manager.displayLongestMessage();
        String output = outContent.toString().trim();
        assertTrue(output.contains("Longest Message: Where are you? You are late! I have asked you to be on time."));
    }

    @Test
    void testSearchByMessageId_Message4() {
        outContent.reset();
        Manager.searchByMessageId("msg003");
        String output = outContent.toString().trim();
        assertTrue(output.contains("Message: It is dinner time !"));
    }

    @Test
    void testSearchMessagesByRecipient_ParticularRecipient() {
        outContent.reset();
        Manager.searchMessagesByRecipient("+27838884567");
        String output = outContent.toString();
        assertTrue(output.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(output.contains("Ok, I am leaving without you."));
    }

    @Test
    void testDeleteMessageByHash_Message2() {
        outContent.reset();
        Manager.deleteMessageByHash("hash002");
        String output = outContent.toString();
        assertTrue(output.contains("Deleting message: Where are you? You are late! I have asked you to be on time."));

        // Confirm it's gone
        outContent.reset();
        Manager.searchMessagesByRecipient("+27838884567");
        String result = outContent.toString();
        assertFalse(result.contains("Where are you? You are late!"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    void testDisplayFullReportOnlyIncludesSentMessages() {
        outContent.reset();
        Manager.displayFullReport();
        String report = outContent.toString();

        // Should contain messages 1, 2, 4, 5 (message 3 deleted)
        assertTrue(report.contains("Message Hash: hash001") || report.contains("hash001"));
        assertTrue(report.contains("Recipient: +27834557896"));
        assertTrue(report.contains("Message: Did you get the cake?"));

        assertTrue(report.contains("Message: Where are you? You are late! I have asked you to be on time.") ||
                report.contains("Message: Ok, I am leaving without you."));

        assertTrue(report.contains("Message: It is dinner time !"));

        // Should not contain deleted message
        assertFalse(report.contains("Yohoooo, I am at your gate."));
    }
}
