package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class KhanbanTasks {
    private List<Task> tasks = new ArrayList<>(); // List to store all tasks
    private int totalHours = 0; // Total duration of all tasks

    /**
     * Method to start the Kanban Task management process.
     * Displays a menu to the user and handles task-related operations.
     */
    public void manageTasks() {
        String welcomeMessage = "Welcome to EasyKanban";
        JOptionPane.showMessageDialog(null, welcomeMessage);

        int option;
        // Loop to keep displaying the menu until the user chooses to quit
        do {
            String menu = "Choose an option:\n1) Add Task\n2) Show Report\n3) Quit";
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (option) {
                case 1:
                    // Prompt the user for the number of tasks they wish to enter
                    int numberOfTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks would you like to enter?"));
                    for (int i = 0; i < numberOfTasks; i++) {
                        createNewTask(i);
                    }
                    break;
                case 2:
                    // Placeholder message for the report feature
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case 3:
                    // Exit the task management system
                    JOptionPane.showMessageDialog(null, "Exiting the task management system.");
                    break;
                default:
                    // Handle invalid menu input
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        } while (option != 3); // Continue looping until the user selects to quit
    }

    /**
     * Method to create a new task with details entered by the user.
     * @param taskNumber The auto-generated task number.
     */
    private void createNewTask(int taskNumber) {
        // Prompt the user for task details
        String taskName = JOptionPane.showInputDialog("Enter Task Name:");
        String taskDescription = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");

        // Validate the length of the task description
        if (taskDescription.length() > 50) {
            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
            return; // Exit the method if the description is too long
        }

        // Get additional task details from the user
        String developerDetails = JOptionPane.showInputDialog("Enter Developer's First and Last Name:");
        int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours):"));
        String taskStatus = JOptionPane.showInputDialog("Enter Task Status (To Do / Doing / Done):");

        // Generate a unique Task ID using the task name, task number, and developer details
        String taskID = createTaskID(taskName, taskNumber, developerDetails);
        totalHours += taskDuration; // Accumulate the total task duration

        // Create and store the new task in the list of tasks
        Task newTask = new Task(taskName, taskNumber, taskDescription, developerDetails, taskDuration, taskID, taskStatus);
        tasks.add(newTask);

        // Display the task details after creation
        JOptionPane.showMessageDialog(null, newTask.printTaskDetails());
    }

    /**
     * Method to create a unique Task ID using task and developer details.
     * @param taskName The name of the task.
     * @param taskNumber The auto-generated task number.
     * @param developerDetails The developer's name.
     * @return The generated Task ID.
     */
    private String createTaskID(String taskName, int taskNumber, String developerDetails) {
        // Format Task ID: First two letters of task name : task number : last three letters of developer's name
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" +
               developerDetails.substring(developerDetails.length() - 3).toUpperCase();
    }

    /**
     * Method to return the total duration of all tasks.
     * @return The sum of the durations of all tasks.
     */
    public int getTotalHours() {
        return totalHours;
    }

    /**
     * Method to add a task directly for testing purposes.
     * @param taskName The name of the task to be added.
     * @param taskDescription A brief description of the task.
     * @param developerDetails The name of the developer assigned to the task.
     * @param taskDuration The estimated duration of the task.
     * @param taskStatus The status of the task (To Do, Doing, Done).
     */
    public void addTask(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        int taskNumber = tasks.size() + 1; // Assign a task number based on the current list size
        String taskID = createTaskID(taskName, taskNumber, developerDetails);
        Task newTask = new Task(taskName, taskNumber, taskDescription, developerDetails, taskDuration, taskID, taskStatus);
        tasks.add(newTask);
        totalHours += taskDuration; // Update the total hours
    }

    /**
     * Method to remove a task based on task name.
     * @param taskName The name of the task to be removed.
     */
    public void removeTask(String taskName) {
        tasks.removeIf(task -> task.taskName.equals(taskName)); // Remove the task if found
    }

    /**
     * Method to get the list of task names.
     * @return List of task names.
     */
    public List<String> getTasks() {
        List<String> taskNames = new ArrayList<>();
        for (Task task : tasks) {
            taskNames.add(task.taskName);
        }
        return taskNames;
    }

    /**
     * Inner class representing an individual task.
     * Holds details about the task such as name, developer, duration, and status.
     */
    private class Task {
        private String taskName;
        private int taskNumber;
        private String taskDescription;
        private String developerDetails;
        private int taskDuration;
        private String taskID;
        private String taskStatus;

        /**
         * Constructor to initialize a Task object with the required details.
         * @param taskName The name of the task.
         * @param taskNumber The number of the task.
         * @param taskDescription A brief description of the task.
         * @param developerDetails The name of the developer assigned to the task.
         * @param taskDuration The estimated duration of the task.
         * @param taskID The unique ID of the task.
         * @param taskStatus The status of the task (To Do, Doing, Done).
         */
        public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskID, String taskStatus) {
            this.taskName = taskName;
            this.taskNumber = taskNumber;
            this.taskDescription = taskDescription;
            this.developerDetails = developerDetails;
            this.taskDuration = taskDuration;
            this.taskID = taskID;
            this.taskStatus = taskStatus;
        }

        /**
         * Method to display the full details of a task.
         * @return A formatted string containing all the task details.
         */
        public String printTaskDetails() {
            return "Task Status: " + taskStatus + "\nDeveloper Details: " + developerDetails +
                   "\nTask Number: " + taskNumber + "\nTask Name: " + taskName +
                   "\nTask Description: " + taskDescription + "\nTask ID: " + taskID +
                   "\nDuration: " + taskDuration + " hours";
        }
    }
}
