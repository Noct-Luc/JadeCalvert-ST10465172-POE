package POE2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    // Create a test user
    Login user = new Login("kyl_1", "FFXV@10cs");

    @Test
    void testgetLoginpass() {
        assertEquals("FFXV@10cs", user.getLoginpass());
    }

    @Test
    void testgetLoginUser() {
        assertEquals("kyl_1", user.getLoginUser());
    }

    @Test
    void testlogin() {
        // Since the static Login method is empty, we just check it doesn't throw anything
        Login.Login("kyl_1", "FFXV@10cs");
    }

    @Test
    void testcheckLogin_Success() {
        assertTrue(user.checkLogin("kyl_1", "FFXV@10cs"));
    }

    @Test
    void testcheckLogin_Failure() {
        assertFalse(user.checkLogin("wrongUser", "wrongPass"));
    }
}
