package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class RegistrationandLogin {
    private final List<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {
        Login login = new Login();
        RegistrationandLogin app = new RegistrationandLogin();

        while (true) {
            String menu = "1. Register\n2. Login\n3. Check Login Status";
            String choice = JOptionPane.showInputDialog(null, menu, "Main Menu", JOptionPane.QUESTION_MESSAGE);

            // Handle Cancel button
            if (choice == null) {
                JOptionPane.showMessageDialog(null, "Exiting the application. Goodbye!");
                System.exit(0);
            }

            if (isValidNumericInput(choice)) {
                int option = Integer.parseInt(choice);

                switch (option) {
                    case 1:
                        String name = JOptionPane.showInputDialog("Enter your name:");
                        if (name == null) break;
                        
                        String lastName = JOptionPane.showInputDialog("Enter your surname:");
                        if (lastName == null) break;
                        
                        String username = JOptionPane.showInputDialog("Enter username (max 5 characters and must contain an underscore):");
                        if (username == null) break;
                        
                        String password = JOptionPane.showInputDialog("Enter password (min 8 characters, with a capital letter, number, and special character):");
                        if (password == null) break;

                        JOptionPane.showMessageDialog(null, login.registerUser(name, lastName, username, password));
                        break;

                    case 2:
                        String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
                        if (enteredUsername == null) break;
                        
                        String enteredPassword = JOptionPane.showInputDialog("Enter your password:");
                        if (enteredPassword == null) break;

                        login.loginUser(enteredUsername, enteredPassword);
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Login Status: " + (login.returnLoginStatus() ? "Logged in" : "Not logged in"));
                        if (login.returnLoginStatus()) {
                            app.displayKanbanMenu();
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number from the menu.");
            }
        }
    }

    private void displayKanbanMenu() {
        while (true) {
            String menu = "1. Add Task\n2. Show Report\n3. Search Task\n4. Delete Task\n5. Find Longest Task\n6. Return to Main Menu";
            String choice = JOptionPane.showInputDialog(null, menu, "Kanban Menu", JOptionPane.QUESTION_MESSAGE);

            // Handle Cancel button
            if (choice == null) {
                JOptionPane.showMessageDialog(null, "Returning to the Main Menu.");
                return;
            }

            if (isValidNumericInput(choice)) {
                int option = Integer.parseInt(choice);

                switch (option) {
                    case 1:
                        manageTasks();
                        break;

                    case 2:
                        Task.displayReport(tasksList);
                        break;

                    case 3:
                        String searchName = JOptionPane.showInputDialog("Enter the task name to search:");
                        if (searchName == null) break;

                        Task task = Task.searchTaskByName(tasksList, searchName);
                        if (task != null) {
                            task.printTaskDetails();
                        } else {
                            JOptionPane.showMessageDialog(null, "Task not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 4:
                        String deleteName = JOptionPane.showInputDialog("Enter the task name to delete:");
                        if (deleteName == null) break;

                        if (Task.deleteTaskByName(tasksList, deleteName)) {
                            JOptionPane.showMessageDialog(null, "Task deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Task not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 5:
                        Task longestTask = Task.findLongestTask(tasksList);
                        if (longestTask != null) {
                            JOptionPane.showMessageDialog(null, "Longest Task:\n" + longestTask.getTaskID() + "\nDuration: " + longestTask.getTaskDuration() + " hours");
                        } else {
                            JOptionPane.showMessageDialog(null, "No tasks found.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 6:
                        return;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number from the menu.");
            }
        }
    }

    private void manageTasks() {
        String input = JOptionPane.showInputDialog("How many tasks would you like to add?");
        if (input == null) {
            JOptionPane.showMessageDialog(null, "Operation cancelled. Returning to Kanban menu.");
            return;
        }

        if (isValidNumericInput(input)) {
            int numTasks = Integer.parseInt(input);

            for (int i = 0; i < numTasks; i++) {
                String taskName = JOptionPane.showInputDialog("Enter the task name:");
                if (taskName == null) break;

                String taskDescription = JOptionPane.showInputDialog("Enter the task description (max 50 characters):");
                if (taskDescription == null) break;

                String developerDetails = JOptionPane.showInputDialog("Enter the developer's first and last name:");
                if (developerDetails == null) break;

                String durationInput = JOptionPane.showInputDialog("Enter task duration (hours):");
                if (durationInput == null || !isValidNumericInput(durationInput)) {
                    JOptionPane.showMessageDialog(null, "Invalid duration. Task not added.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }

                int taskDuration = Integer.parseInt(durationInput);
                String[] statuses = {"To Do", "Doing", "Done"};
                String taskStatus = (String) JOptionPane.showInputDialog(null, "Choose a task status:", "Task Status", JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
                if (taskStatus == null) break;

                Task newTask = new Task(taskName, i + 1, taskDescription, developerDetails, taskDuration, taskStatus);
                tasksList.add(newTask);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid number of tasks. Operation cancelled.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean isValidNumericInput(String input) {
        return input != null && input.matches("\\d+");
    }
}
