package org.yourcompany.yourproject;

import javax.swing.JOptionPane;

public class Login {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private boolean loginStatus;

    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[@$!%*?&].*");
    }

    public String registerUser(String name, String lastName, String username, String password) {
        if (!checkUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }

        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

        return "User successfully registered!";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername.equals(this.username) && enteredPassword.equals(this.password)) {
            loginStatus = true;
            JOptionPane.showMessageDialog(null, "Welcome " + name + " " + lastName + ", it is great to see you again.");
            return true;
        } else {
            loginStatus = false;
            JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean returnLoginStatus() {
        return loginStatus;
    }

    public String getUsername() {
        return username; // Return the stored username
    }

    public void logout() {
        loginStatus = false; // Reset login status on logout
    }
}
