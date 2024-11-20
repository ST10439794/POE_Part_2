package org.yourcompany.yourproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationandLoginTest {

    private Login login;

    // Setup method to initialize the Login object before each test
    @BeforeEach
    public void setUp() {
        login = new Login();  // Adjust this based on how your Login class is structured
    }

    /**
     * Test the checkUsername() method for various scenarios.
     */
    @Test
    public void testCheckUsername() {
        assertTrue(login.checkUsername("kyl_1"), "Expected valid username with underscore and <= 5 characters.");
        assertFalse(login.checkUsername("user123"), "Expected invalid username as it does not contain an underscore.");
        assertFalse(login.checkUsername("username_"), "Expected invalid username as it exceeds 5 characters.");
    }

    /**
     * Test the checkPasswordComplexity() method for various password formats.
     */
    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"), "Expected valid password with required complexity.");
        assertFalse(login.checkPasswordComplexity("password"), "Expected invalid password as it lacks capital letter, number, and special character.");
        assertFalse(login.checkPasswordComplexity("P@ss"), "Expected invalid password as it is too short.");
        assertFalse(login.checkPasswordComplexity("Password"), "Expected invalid password as it lacks a special character and number.");
    }

    @Test
    public void testRegisterUserWithInvalidUsername() {
        String result = login.registerUser ("Kyle", "Renner", "userlongname", "Ch&&sec@ke99!");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", result);
    }

    @Test
    public void testRegisterUserWithInvalidPassword() {
        String result = login.registerUser ("Kyle", "Renner", "kyl_1", "password");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", result);
    }

    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser ("Kyle", "Renner", "kyl_1", "Ch&&sec@ke99!");
        assertEquals("User successfully registered!", result);
        assertEquals("kyl_1", login.getUsername(), "Username should match the registered value!");
    }

    @Test
    public void testLoginUser () {
        // Register a user first
        login.registerUser ("Kyle", "Renner", "kyl_1", "Ch&&sec@ke99!");

        // Test successful login
        assertTrue(login.loginUser ("kyl_1", "Ch&&sec@ke99!"), "Login should succeed with correct credentials");

        // Test unsuccessful login with wrong password
        assertFalse(login.loginUser ("kyl_1", "password"), "Login should fail with incorrect password");
    }

    @Test
    public void testReturnLoginStatus() {
        // Register and login a user
        login.registerUser ("Kyle", "Renner", "kyl_1", "Ch&&sec@ke99!");
        login.loginUser ("kyl_1", "Ch&&sec@ke99!");

        // Check if the login status is correct
        assertTrue(login.returnLoginStatus(), "Login status should be true after successful login");
    }

    @Test
    public void testFailedLoginStatus() {
        // Attempt to login without registering
        assertFalse(login.loginUser ("nonexistent_user", "Ch&&sec@ke99!"), "Login should fail for unregistered user");
    }
}