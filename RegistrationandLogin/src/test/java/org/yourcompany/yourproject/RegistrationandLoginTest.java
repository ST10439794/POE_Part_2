/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.yourcompany.yourproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    /**
     * Test the registerUser() method with valid inputs.
     */
    @Test
    public void testRegisterUserValid() {
        String result = login.registerUser("John", "Doe", "user_", "Passw0rd!");
        assertEquals("User successfully registered!", result, "Expected successful registration with valid inputs.");
    }

    /**
     * Test the registerUser() method with invalid username.
     */
    @Test
    public void testRegisterUserInvalidUsername() {
        String result = login.registerUser("John", "Doe", "user123", "Passw0rd!");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", result, "Expected registration failure due to invalid username.");
    }

    /**
     * Test the registerUser() method with invalid password.
     */
    @Test
    public void testRegisterUserInvalidPassword() {
        String result = login.registerUser("John", "Doe", "user_", "password");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", result, "Expected registration failure due to invalid password.");
    }

    /**
     * Test the loginUser() method with correct credentials.
     */
    @Test
    public void testLoginUserValid() {
        // Register the user first
        login.registerUser("John", "Doe", "user_", "Passw0rd!");

        // Now test the login with correct credentials
        boolean loginResult = login.loginUser("user_", "Passw0rd!");
        assertTrue(loginResult, "Expected successful login with correct credentials.");
    }

    /**
     * Test the loginUser() method with incorrect credentials.
     */
    @Test
    public void testLoginUserInvalid() {
        // Register the user first
        login.registerUser("John", "Doe", "user_", "Passw0rd!");

        // Test the login with incorrect credentials
        boolean loginResult = login.loginUser("user_", "WrongPassword");
        assertFalse(loginResult, "Expected failed login due to incorrect password.");
    }

    /**
     * Test the returnLoginStatus() method.
     */
    @Test
    public void testReturnLoginStatus() {
        // Register and login the user
        login.registerUser("John", "Doe", "user_", "Passw0rd!");
        login.loginUser("user_", "Passw0rd!");

        // Check the login status
        assertTrue(login.returnLoginStatus(), "Expected login status to be true after successful login.");

        // Now login with wrong credentials and check status
        login.loginUser("user_", "WrongPassword");
        assertFalse(login.returnLoginStatus(), "Expected login status to be false after failed login.");
    }
}

