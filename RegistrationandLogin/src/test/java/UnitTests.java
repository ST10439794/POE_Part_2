import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.yourcompany.yourproject.Login;

public class UnitTests {
    @Test
    public void testUsername_ValidUsername() {
        Login login = new Login();
        assertTrue(login.checkUsername("kyl_1"));
        // assertTrue(login.checkUsername("kyl!!!!!!!"));
    }

    @Test
    public void testPasswordComplexity(){
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99"));
        // assertTrue(login.checkPasswordComplexity("password"));
    }

    /* 
    @Test
    public void testLoginUser_ValidCredentials() {
        Login login = new Login();

        // Register user first
        login.registerUser("Resego", "Mmolawa", "Ree_7", "Password1@");

        // Test valid login
        login.loginUser("Ree_7", "Password1@");
        assertTrue(login.returnLoginStatus(), "Login should be successful");
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        Login login = new Login();

        // Register user first
        login.registerUser("Resego", "Mmolawa", "Ree_7", "Password1@");

        // Test invalid login
        login.loginUser("Re_8", "wrongPassword");
        assertFalse(login.returnLoginStatus(), "Login should fail with incorrect password");
    }
    */
}
