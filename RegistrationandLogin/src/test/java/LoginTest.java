import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yourcompany.yourproject.Login;

public class LoginTest {
    private Login loginSystem;

    @BeforeEach
    public void setUp() {
        loginSystem = new Login();
    }

    @Test
    public void testCheckUsername_Valid() {
        assertTrue(loginSystem.checkUsername("user_1"), "Username should be valid");
    }

    @Test
    public void testCheckUsername_Invalid_NoUnderscore() {
        assertFalse(loginSystem.checkUsername("username"), "Username should be invalid without an underscore");
    }

    @Test
    public void testCheckUsername_Invalid_TooLong() {
        assertFalse(loginSystem.checkUsername("user_name_long"), "Username should be invalid for being too long");
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(loginSystem.checkPasswordComplexity("Password1!"), "Password should be valid");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_TooShort() {
        assertFalse(loginSystem.checkPasswordComplexity("Pass1!"), "Password should be invalid for being too short");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoUpperCase() {
        assertFalse(loginSystem.checkPasswordComplexity("password1!"), "Password should be invalid without uppercase letters");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoDigit() {
        assertFalse(loginSystem.checkPasswordComplexity("Password!"), "Password should be invalid without a digit");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid_NoSpecialChar() {
        assertFalse(loginSystem.checkPasswordComplexity("Password1"), "Password should be invalid without a special character");
    }
    
    @Test
    public void testRegisterUser_Valid() {
        loginSystem.registerUser("John", "Doe", "user_1", "Password1!");
        assertEquals("user_1", loginSystem.returnLoginStatus(), "User should be successfully registered");
    }

    @Test
    public void testLoginUser_Valid() {
        loginSystem.registerUser("John", "Doe", "user_1", "Password1!");
        boolean result = loginSystem.loginUser("user_1", "Password1!");
        assertTrue(result, "Login should be successful with valid credentials");
    }

    @Test
    public void testLoginUser_InvalidUsername() {
        loginSystem.registerUser("John", "Doe", "user_1", "Password1!");
        boolean result = loginSystem.loginUser("wrong_username", "Password1!");
        assertFalse(result, "Login should fail with invalid username");
    }

    @Test
    public void testLoginUser_InvalidPassword() {
        loginSystem.registerUser("John", "Doe", "user_1", "Password1!");
        boolean result = loginSystem.loginUser("user_1", "wrong_password");
        assertFalse(result, "Login should fail with invalid password");
    }
}
