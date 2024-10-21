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
            String menu = "1. Register\n2. Login\n3. Check Login Status\n4. Kanban Tasks";
            String choiceStr = JOptionPane.showInputDialog(null, menu, "Main Menu", JOptionPane.QUESTION_MESSAGE);

            // Convert user input to an integer; handle invalid input gracefully
            int choice;
            choice = Integer.parseInt(choiceStr);
            if (choice < 1 || choice > 4) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 4.", "Error", JOptionPane.ERROR_MESSAGE);
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
                    // User can enter Kanban
                    if(login.returnLoginStatus()){
                        app.displayKanbanMenu();
                    }else{
                        JOptionPane.showMessageDialog(null, "You must be logged in to access the Kanban system.", "Access Denied", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case 5:
                    //Exit the application
                    
                JOptionPane.showMessageDialog(null, "Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    // Handle invalid menu options
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //Method to display the Kanban menu after user login
    private void displayKanbanMenu(){
        //Display the welcome message for Easy Kanban
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        
        //Loop to continuously display the kanban menu until the user chooses to quit
        while(true){
            String kanbanMenu = "1. Add Tasks \n2. Show Report (Coming Soon)\n3. Quit";
            String kanbanChoiceStr = JOptionPane.showInputDialog(null, kanbanMenu, "EasyKanban Menu", JOptionPane.QUESTION_MESSAGE);
            
            int kanbanChoice;
            try {
                kanbanChoice = Integer.parseInt(kanbanChoiceStr);
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 3.", "Error", JOptionPane.ERROR_MESSAGE);
                continue; //Restart the loop
            }
            
            switch(kanbanChoice){
                case 1:
                    //Manage adding tasks to the Kanban system
                    manageTasks();
                    break;
                case 2:
                    //Show Report (Coming Soon)
                    JOptionPane.showMessageDialog(null, "Feature Coming Soon!", "Show Report", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3:
                    //Return to the auth menu
                    JOptionPane.showMessageDialog(null, "Returning to main menu.", "Logout", JOptionPane.INFORMATION_MESSAGE);
                    return;
                default:
                    //Handle invalid menu options
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to manage adding tasks with user interaction
    private void manageTasks() {
        int numTasks = -1; // Initialize with an invalid value
        boolean validInput = false;

        while (!validInput) {
            String input = JOptionPane.showInputDialog("How many tasks would you like to add?");
            
            if (input == null) {
                // Handle cancel action
                return; // Exit or handle accordingly
            }

            if (input.matches("\\d+")) { // Regex to check if the input is a number
                numTasks = Integer.parseInt(input);
                validInput = true; // Input is valid, exit the loop
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter the task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter the task description (max 50 characters):");
            String developerDetails = JOptionPane.showInputDialog("Enter the developer's first and last name:");

            int taskDuration = -1;
            while (taskDuration < 0) {
                String durationStr = JOptionPane.showInputDialog("Enter the task duration in hours:");
            
                if (durationStr == null) {
                    // Handle cancel action
                    return; // Exit or handle accordingly
                }
            
                if (durationStr.matches("\\d+")) { // Check if input is a valid number
                    taskDuration = Integer.parseInt(durationStr); // Parse the valid input
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid duration. Please enter a valid number of hours.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
