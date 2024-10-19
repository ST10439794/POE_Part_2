package org.yourcompany.yourproject;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationandLoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    @AfterEach
    public void tearDown() {
        login = null;
    }

    /**
     * Test the checkUsername() method for various scenarios.
     */
    @Test
    public void testCheckUsername() {
        assertTrue(login.checkUsername("user_"), "Expected valid username with underscore and <= 5 characters.");
        assertFalse(login.checkUsername("user123"), "Expected invalid username as it does not contain an underscore.");
        assertFalse(login.checkUsername("username_"), "Expected invalid username as it exceeds 5 characters.");
    }

    /**
     * Test the checkPasswordComplexity() method for various password formats.
     */
    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity("Passw0rd!"), "Expected valid password with required complexity.");
        assertFalse(login.checkPasswordComplexity("password"), "Expected invalid password as it lacks capital letter, number, and special character.");
        assertFalse(login.checkPasswordComplexity("P@ss"), "Expected invalid password as it is too short.");
        assertFalse(login.checkPasswordComplexity("Password"), "Expected invalid password as it lacks a special character and number.");
    }

    @Test
    public void testRegisterUserWithInvalidUsername() {
        String result = login.registerUser("John", "Doe", "userlongname", "Password1!");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", result);
    }

    @Test
    public void testRegisterUserWithInvalidPassword() {
        String result = login.registerUser("John", "Doe", "usr_1", "password");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", result);
    }

    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser("John", "Doe", "usr_1", "Password1!");
        assertEquals("User successfully registered!", result);
        assertEquals("usr_1", login.getUsername(), "Username should match the registered value.");
    }

    @Test
    public void testLoginUser() {
        // Register a user first
        login.registerUser("Jane", "Doe", "usr_2", "Password1!");

        // Test successful login
        assertTrue(login.loginUser("usr_2", "Password1!"), "Login should succeed with correct credentials");

        // Test unsuccessful login with wrong password
        assertFalse(login.loginUser("usr_2", "WrongPass"), "Login should fail with incorrect password");
    }

    @Test
    public void testReturnLoginStatus() {
        // Register and login a user
        login.registerUser("Alice", "Smith", "usr_3", "Password1!");
        login.loginUser("usr_3", "Password1!");

        // Check if the login status is correct
        assertTrue(login.returnLoginStatus(), "Login status should be true after successful login");
    }
}
