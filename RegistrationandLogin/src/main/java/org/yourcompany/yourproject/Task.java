package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public final class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;

    // Static list to keep track of all task durations to calculate total hours
    private static List<Integer> allTaskDurations = new ArrayList<>();

    // Constructor to initialize a new task with its details
    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        
        // Validate the task description before setting the values
        if (!checkTaskDescription(taskDescription)) {
            return; // If invalid, do not proceed with task creation
        }

        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
        allTaskDurations.add(taskDuration); // Add the task duration to the list of all durations
    }

    // Method to ensure that the task description is not more than 50 characters
    public boolean checkTaskDescription(String taskDescription) {
        boolean isValid = taskDescription.length() <= 50;
        if (isValid) {
            JOptionPane.showMessageDialog(null, "Task successfully captured", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    // Method to create and return the TaskID
    public String createTaskID() {
        String taskPrefix = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        String developerSuffix = developerDetails.length() >= 3 ? developerDetails.substring(developerDetails.length() - 3).toUpperCase() : developerDetails.toUpperCase();
        return taskPrefix + ":" + taskNumber + ":" + developerSuffix;
    }

    // Method to get the Task ID
    public String getTaskID() {
        return taskID;
    }

    // Method to clear all task durations
    public static void clearTaskDurations() {
        allTaskDurations.clear();
    }

    // Method to display the full task details using JOptionPane
    public void printTaskDetails() {
        String taskDetails = "Task Status: " + taskStatus + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Number: " + taskNumber + "\n" +
                "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Task ID: " + taskID + "\n" +
                "Task Duration: " + taskDuration + " hours\n";

        JOptionPane.showMessageDialog(null, taskDetails, "Task Details", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to return the total combined hours of all entered tasks
    public static int returnTotalHours() {
        return allTaskDurations.stream().mapToInt(Integer::intValue).sum();
    }

    // Method to display the total combined hours of all tasks using JOptionPane
    public static void displayTotalHours() {
        int totalHours = returnTotalHours();
        JOptionPane.showMessageDialog(null, "Total hours for all tasks: " + totalHours + " hours", "Total Hours", JOptionPane.INFORMATION_MESSAGE);
    }
}
