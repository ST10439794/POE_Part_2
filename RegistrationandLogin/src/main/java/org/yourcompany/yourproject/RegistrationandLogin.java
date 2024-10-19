/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;
import javax.swing.JOptionPane;
/**
 *
 * @author reseg
 */
public class RegistrationandLogin {

    public static void main(String[] args) {
        // Create a Login object to handle user registration and login
        Login login = new Login();
        KhanbanTasks khanbanTasks = new KhanbanTasks(); // Create a KhanbanTasks object to manage tasks

        // Loop to continuously display the main menu until the user chooses to exit
        while (true) {
            String menu = "1. Register\n2. Login\n3. Check Login Status\n4. Add Tasks (You must be logged in)\n5. Exit";
            String choiceStr = JOptionPane.showInputDialog(null, menu, "Main Menu", JOptionPane.QUESTION_MESSAGE);

            // Convert user input to an integer; handle invalid input gracefully
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 5.", "Error", JOptionPane.ERROR_MESSAGE);
                continue; // Restart the loop
            }

            switch (choice) {
                case 1:
                    // Register a new user
                    String userName = JOptionPane.showInputDialog("Enter your name:");
                    String userLastName = JOptionPane.showInputDialog("Enter your surname:");
                    String userUsername = JOptionPane.showInputDialog("Enter username (max 5 characters and must contain an underscore):");
                    String userPassword = JOptionPane.showInputDialog("Enter password (min 8 characters, with a capital letter, a number, and a special character):");

                    // Call registerUser with the user input
                    login.registerUser(userName, userLastName, userUsername, userPassword);
                    break;
                case 2:
                    // Log in with existing user credentials
                    String loginUsername = JOptionPane.showInputDialog("Enter your username:");
                    String loginPassword = JOptionPane.showInputDialog("Enter your password:");

                    // Call loginUser with the user input
                    login.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    // Display the current login status
                    JOptionPane.showMessageDialog(null, "Login Status: " + (login.returnLoginStatus() ? "Logged in" : "Not logged in"));
                    break;
                case 4:
                    // Allow the user to add tasks only if they are logged in
                    if (login.returnLoginStatus()) {
                        khanbanTasks.manageTasks();
                    } else {
                        JOptionPane.showMessageDialog(null, "You must be logged in to add tasks.", "Access Denied", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 5:
                    // Exit the application
                    JOptionPane.showMessageDialog(null, "Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    // Handle invalid menu options
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
