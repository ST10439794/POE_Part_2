package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public final class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration; // This is private
    private String taskID;
    private String taskStatus;

    // Static list to keep track of all task durations to calculate total hours
    private static List<Integer> allTaskDurations = new ArrayList<>();

    // Constructor to initialize a new task with its details
    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
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

    public boolean checkTaskDescription(String taskDescription) {
        boolean isValid = taskDescription != null && !taskDescription.isEmpty() && taskDescription.length() <= 50;
        if (isValid) {
            JOptionPane.showMessageDialog(null, "Task successfully captured", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    public String createTaskID() {
        String taskPrefix = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        String developerSuffix = developerDetails.length() >= 3 ? developerDetails.substring(developerDetails.length() - 3).toUpperCase() : developerDetails.toUpperCase();
        return taskPrefix + ":" + taskNumber + ":" + developerSuffix;
    }

    public String getTaskID() {
        return taskID;
    }

    public int getTaskDuration() {
        return taskDuration; // Getter for taskDuration
    }

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

    public static Task searchTaskByName(List<Task> tasks, String taskName) {
        for (Task task : tasks) {
            if (task.taskName.equalsIgnoreCase(taskName)) {
                return task;
            }
        }
        return null;
    }

    public static boolean deleteTaskByName(List<Task> tasks, String taskName) {
        return tasks.removeIf(task -> task.taskName.equalsIgnoreCase(taskName));
    }

    public static Task findLongestTask(List<Task> tasks) {
        return tasks.stream().max((a, b) -> Integer.compare(a.getTaskDuration(), b.getTaskDuration())).orElse(null);
    }

    public static void displayReport(List<Task> tasks) {
        StringBuilder report = new StringBuilder("Task Report:\n");
        for (Task task : tasks) {
            report.append(String.format("Task: %s | Developer: %s | Duration: %d hours\n", task.taskName, task.developerDetails, task.getTaskDuration()));
        }
        JOptionPane.showMessageDialog(null, report.toString(), "Task Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int returnTotalHours() {
        int totalHours = 0;
        for (Integer duration : allTaskDurations) {
            totalHours += duration;
        }
        return totalHours;
    }

    public static void displayTotalHours(List<Task> tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getTaskDuration(); // Use the getter to access taskDuration
        }
        JOptionPane.showMessageDialog(null, "Total Hours for All Tasks: " + totalHours + " hours", "Total Hours", JOptionPane.INFORMATION_MESSAGE);
    }
}
