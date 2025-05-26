package POE2;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testCheckPasswordComplexity_ValidPasswords() {
        assertTrue(Main.checkPasswordComplexity("Password1!"));
        assertTrue(Main.checkPasswordComplexity("Abcdefg1@"));
        assertTrue(Main.checkPasswordComplexity("A1b2C3d$"));
    }

    @Test
    public void testCheckPasswordComplexity_InvalidPasswords() {
        assertFalse(Main.checkPasswordComplexity(null));           // null password
        assertFalse(Main.checkPasswordComplexity(""));             // empty password
        assertFalse(Main.checkPasswordComplexity("short1!"));      // less than 8 chars
        assertFalse(Main.checkPasswordComplexity("alllowercase1!")); // no uppercase
        assertFalse(Main.checkPasswordComplexity("ALLUPPERCASE1!")); // no lowercase
        assertFalse(Main.checkPasswordComplexity("NoNumbers!"));    // no digits
        assertFalse(Main.checkPasswordComplexity("NoSpecial1"));    // no special chars
    }

    @Test
    public void testIsValidUserName_Valid() {
        assertTrue(Main.LoginSystem.isValidUserName("abc_"));
        assertTrue(Main.LoginSystem.isValidUserName("a_b"));
        assertTrue(Main.LoginSystem.isValidUserName("ab_c"));
        assertTrue(Main.LoginSystem.isValidUserName("_abc"));
        assertTrue(Main.LoginSystem.isValidUserName("a_bc"));
    }

    @Test
    public void testIsValidUserName_Invalid() {
        assertFalse(Main.LoginSystem.isValidUserName("abcdef"));  // more than 5 chars
        assertFalse(Main.LoginSystem.isValidUserName("abc"));     // no underscore
        assertFalse(Main.LoginSystem.isValidUserName("ab"));      // no underscore, less than 5 chars
        assertFalse(Main.LoginSystem.isValidUserName("a"));       // no underscore, less than 5 chars
        assertFalse(Main.LoginSystem.isValidUserName("abcde"));   // no underscore
    }

    @Test
    public void testIsValidPassword_Valid() {
        assertTrue(Main.LoginSystem.isValidPassword("Password1!"));
        assertTrue(Main.LoginSystem.isValidPassword("A1b2C3d$"));
    }

    @Test
    public void testIsValidPassword_Invalid() {
        assertFalse(Main.LoginSystem.isValidPassword("password"));
        assertFalse(Main.LoginSystem.isValidPassword("PASSWORD123"));
        assertFalse(Main.LoginSystem.isValidPassword("Passw1"));
        assertFalse(Main.LoginSystem.isValidPassword("Passw1!"));
    }

    // We don't test main() because it requires user input and is interactive
}
