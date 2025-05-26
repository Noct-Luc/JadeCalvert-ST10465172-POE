package POE2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {

    Register user = new Register("john_d", "P@ssw0rd!", "+27123456789");

    @Test
    void testGetUsername() {
        assertEquals("kyl_1", user.getUsername());
    }

    @Test
    void testGetPassword() {
        assertEquals("FFXV@10cs!", user.getPassword());
    }

    @Test
    void testGetPhonenumber() {
        assertEquals("+27606668901", user.getPhonenumber());
    }

    @Test
    void testIsRegisterPasswordTrue() {
        assertTrue(Register.isRegisteredPassword("P@ssw0rd!", user));
    }

    @Test
    void testIsRegisterPasswordFalse() {
        assertFalse(Register.isRegisteredPassword("WrongPassword", user));
    }
}
