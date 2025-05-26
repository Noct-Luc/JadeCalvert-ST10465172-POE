import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class mainTest {

    @Test
    void testValidUsername() {
        assertTrue(main.checkUsername("kyl_1"));
    }

    @Test
    void testInvalidUsername() {
        assertFalse(main.checkUsername("invalid_user"));
    }

    @Test

    void testValidCellphone() {
        assertTrue(main.checkCellphoneNumber("+27606668901"));
    }

    @Test
    void testInvalidCellphone(){
       assertFalse(main.checkCellphoneNumber("0606668901"));
    }

    @Test
    void testStrongPassword() {
        assertTrue(main.checkPasswordComplexity("FFXV@10cs!"));
    }

    @Test
    void testWeakPassword() {
        assertFalse(main.checkPasswordComplexity("rawr"));
    }
}