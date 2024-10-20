package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Main class for handling user registration, login, and task management.
 */
public class RegistrationandLogin {
    private boolean isLoggedIn = false;
    private List<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {
        // Create a Login object to handle user registration and login
        Login login = new Login();
        RegistrationandLogin app = new RegistrationandLogin(); // Main application instance

        // Loop to continuously display the main menu until the user chooses to exit
        while (true) {
            String menu = "1. Register\n2. Login\n3. Check Login Status\n4. Add Tasks (You must be logged in)\n5. Show Report (Coming Soon)\n6. Exit";
            String choiceStr = JOptionPane.showInputDialog(null, menu, "Main Menu", JOptionPane.QUESTION_MESSAGE);

            // Convert user input to an integer; handle invalid input gracefully
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 6.", "Error", JOptionPane.ERROR_MESSAGE);
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
                        app.manageTasks();
                    } else {
                        JOptionPane.showMessageDialog(null, "You must be logged in to add tasks.", "Access Denied", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 5:
                    // Show Report (Coming Soon)
                    JOptionPane.showMessageDialog(null, "Feature Coming Soon!", "Show Report", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 6:
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

    // Method to manage adding tasks with user interaction
    private void manageTasks() {
        String input = JOptionPane.showInputDialog("How many tasks would you like to add?");
        int numTasks;

        // Validate input for number of tasks
        try {
            numTasks = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter the task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter the task description (max 50 characters):");
            String developerDetails = JOptionPane.showInputDialog("Enter the developer's first and last name:");
            String durationStr = JOptionPane.showInputDialog("Enter the task duration in hours:");

            int taskDuration;
            try {
                taskDuration = Integer.parseInt(durationStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid duration. Please enter a valid number of hours.", "Error", JOptionPane.ERROR_MESSAGE);
                continue; // Skip this iteration
            }

            String[] statusOptions = {"To Do", "Done", "Doing"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Choose the task status:", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            Task newTask = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);

            // Only add the task if the description is valid
            if (newTask.checkTaskDescription(taskDescription)) {
                newTask.printTaskDetails();
                tasksList.add(newTask);
            }
        }

        Task.displayTotalHours();
    }
}
