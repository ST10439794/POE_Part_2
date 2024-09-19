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
        return password.length() >= 8 && password.matches(".*\\d.*");
    }

    // Register a new user
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
            System.out.println("Username must be at least 5 characters long and must contain an underscore.");
            return;
        }

        System.out.print("Enter password: ");
        String password = input.next();
        if (!checkPasswordComplexity(password)) {
            System.out.println("Password must be at least 8 characters long and contain a number.");
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

    // User login
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
            System.out.println("Invalid username or password.");
        }
        System.out.println(" ");
        System.out.println("*************************************************************************");
    }

    // Return the login status
    public boolean returnLoginStatus() {
        return loginStatus;
    }
}
