package org.yourcompany.yourproject;

import javax.swing.JOptionPane;

public class Login {
    // Variables to store user details and login status
    private String username;
    private String password;
    private String name;
    private String lastName;
    private boolean loginStatus = false;

    /**
     * Method to check if the username meets the required format.
     * @param username The username entered by the user.
     * @return True if the username contains an underscore and is no more than 5 characters.
     */
    public boolean checkUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    /**
     * Method to check if the password meets the complexity requirements.
     * @param password The password entered by the user.
     * @return True if the password is at least 8 characters long and contains a capital letter, a number, and a special character.
     */
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$");
    }

    /**
     * Method to register a user, checking username and password format.
     * @return A message indicating the result of the registration.
     */
    public String registerUser(String userName, String userLastName, String userUsername, String userPassword) {
        // Check username
        if (!checkUsername(userUsername)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        // Check password
        if (!checkPasswordComplexity(userPassword)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

        // Store user information if the registration is successful
        this.username = userUsername;
        this.password = userPassword;
        this.name = userName;
        this.lastName = userLastName;

        return "User successfully registered!";
    }

    /**
     * Method to log in the user interactively using JOptionPane, checking their credentials.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        // Check if the entered username and password match the registered credentials
        if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            loginStatus = true;
            JOptionPane.showMessageDialog(null, "Login successful. Welcome, " + this.username, "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            loginStatus = false;
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Method to return the current login status.
     * @return True if the user is logged in, otherwise false.
     */
    public boolean returnLoginStatus() {
        return loginStatus;
    }

    // Getter methods for testing
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
