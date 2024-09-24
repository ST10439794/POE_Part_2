package org.yourcompany.yourproject;
import java.util.Scanner;

public class Login {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private boolean loginStatus = false;
    private Scanner input = new Scanner(System.in);

    // Check if the username meets the criteria
    public boolean checkUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    // Check if the password meets the complexity requirements
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$");
    }

    // Method to register user with parameters
    public String registerUser(String name, String lastName, String username, String password) {
        if (!checkUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;

        return "User successfully registered!";
    }

    // Interactive method for registering the user
    public void registerUser() {
        System.out.println("********************************* REGISTER *******************************");
        System.out.println(" ");

        System.out.print("Enter your name: ");
        String name = input.next();

        System.out.print("Enter your surname: ");
        String lastName = input.next();

        System.out.print("Enter username: ");
        String username = input.next();
        if (!checkUsername(username)) {
            System.out.println("Username must: be at least 5 characters long and must contain an underscore.");
            return;
        }

        System.out.print("Enter password: ");
        String password = input.next();
        if (!checkPasswordComplexity(password)) {
            System.out.println("Password must: be 8 characters long, start with a capital letter, contain a number and special character");
            return;
        }

        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;

        System.out.println(" ");
        System.out.println("Registration successful. Welcome " + this.name + " " + this.lastName);
        System.out.println("**************************************************************************");
        System.out.println(" ");
    }

    // Method to log in user with parameters
    public boolean loginUser(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            loginStatus = true;
            return true;
        } else {
            loginStatus = false;
            return false;
        }
    }

    // Method for logging in the user
    public void loginUser() {
        System.out.println("******************************** LOGIN **********************************");
        System.out.print("Enter username: ");
        String username = input.next();
        System.out.print("Enter password: ");
        String password = input.next();

        if (this.username.equals(username) && this.password.equals(password)) {
            loginStatus = true;
            System.out.println("Login successful. Welcome " + this.username);
        } else {
            loginStatus = false;
            System.out.println("Invalid username or password. Try again");
        }
        System.out.println(" ");
        System.out.println("*************************************************************************");
    }

    // Return the login status
    public boolean returnLoginStatus() {
        return loginStatus;
    }
}
