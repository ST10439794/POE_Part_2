package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class RegistrationandLogin {
    private List<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {
        Login login = new Login();
        RegistrationandLogin app = new RegistrationandLogin();

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

    private void displayKanbanMenu() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban", "Welcome", JOptionPane.INFORMATION_MESSAGE);

        while (true) {
            String kanbanMenu = "1. Add Tasks\n2. Show Report\n3. Search Task\n4. Delete Task\n5. Find Longest Task\n6. Quit";
            String kanbanChoiceStr = JOptionPane.showInputDialog(null, kanbanMenu, "EasyKanban Menu", JOptionPane.QUESTION_MESSAGE);

            int kanbanChoice = Integer.parseInt(kanbanChoiceStr);
            if (kanbanChoice < 1 || kanbanChoice > 6) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 6.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            switch (kanbanChoice) {
                case 1:
                    manageTasks();
                    break;
                case 2:
                    Task.displayReport(tasksList);
                    break;
                case 3:
                    searchTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    findLongestTask();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Returning to main menu.", "Logout", JOptionPane.INFORMATION_MESSAGE);
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void manageTasks() {
        int numTasks = -1;
        boolean validInput = false;

        while (!validInput) {
            String input = JOptionPane.showInputDialog("How many tasks would you like to add?");
            if (input.matches("\\d+")) {
                numTasks = Integer.parseInt(input);
                validInput = true;
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
                String durationInput = JOptionPane.showInputDialog("Enter task duration (hours):");
                if (durationInput.matches("\\d+")) {
                    taskDuration = Integer.parseInt(durationInput);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for task duration.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            String[] statusOptions = {"To Do", "Done", "Doing"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Choose the task status:", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

            Task newTask = new Task(taskName, i + 1, taskDescription, developerDetails, taskDuration, taskStatus);
            tasksList.add(newTask);
        }
    }

    private void searchTask() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to search:");
        Task task = Task.searchTaskByName(tasksList, taskName);
        if (task != null) {
            task.printTaskDetails();
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:");
        boolean isDeleted = Task.deleteTaskByName(tasksList, taskName);
        if (isDeleted) {
            JOptionPane.showMessageDialog(null, "Task deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findLongestTask() {
        Task longestTask = Task.findLongestTask(tasksList);
        if (longestTask != null) {
            JOptionPane.showMessageDialog(null, "Longest Task: " + longestTask.getTaskID() + " with duration of " + longestTask.getTaskDuration() + " hours.", "Longest Task", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
