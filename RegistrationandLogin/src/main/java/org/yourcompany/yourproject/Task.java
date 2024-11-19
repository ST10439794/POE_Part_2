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

    // Arrays to store task-related data for all tasks
    private static List<String> allDeveloperNames = new ArrayList<>();
    private static List<String> allTaskNames = new ArrayList<>();
    private static List<String> allTaskIDs = new ArrayList<>();
    private static List<Integer> allTaskDurations = new ArrayList<>();
    private static List<String> allTaskStatuses = new ArrayList<>();

    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        if (!checkTaskDescription(taskDescription)) {
            return; // Exit if the description is invalid
        }

        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();

        // Adding data to the arrays
        allDeveloperNames.add(developerDetails);
        allTaskNames.add(taskName);
        allTaskIDs.add(taskID);
        allTaskDurations.add(taskDuration);
        allTaskStatuses.add(taskStatus);
    }

    public boolean checkTaskDescription(String taskDescription) {
        if (taskDescription.length() <= 50) {
            JOptionPane.showMessageDialog(null, "Task successfully captured", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public String createTaskID() {
        String taskPrefix = taskName.substring(0, Math.min(2, taskName.length())).toUpperCase();  // "CR"
        String developerSuffix = developerDetails.substring(Math.max(0, developerDetails.length() - 3)).toUpperCase();  // "ITH"
        return taskPrefix + ":" + taskNumber + ":" + developerSuffix;  // "CR:1:ITH"
    }

    public void printTaskDetails() {
        JOptionPane.showMessageDialog(null,
                "Task Status: " + taskStatus + "\n" +
                        "Developer Details: " + developerDetails + "\n" +
                        "Task Number: " + taskNumber + "\n" +
                        "Task Name: " + taskName + "\n" +
                        "Task Description: " + taskDescription + "\n" +
                        "Task ID: " + taskID + "\n" +
                        "Task Duration: " + taskDuration + " hours",
                "Task Details", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getter methods for accessing private fields
    public String getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getDeveloperDetails() {
        return developerDetails;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    // Static method to display the report of all tasks
    public static String displayReport(List<Task> tasks) {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks to display.", "Task Report", JOptionPane.INFORMATION_MESSAGE);
        }
    
        String report = "Task Report:\n";
        for (Task task : tasks) {
            report += "Task Name: " + task.getTaskName() + "\n" +
                      "Task ID: " + task.getTaskID() + "\n" +
                      "Description: " + task.getTaskDescription() + "\n" +
                      "Developer: " + task.getDeveloperDetails() + "\n" +
                      "Duration: " + task.getTaskDuration() + " hours\n" +
                      "Status: " + task.getTaskStatus() + "\n\n";
        }
    
        JOptionPane.showMessageDialog(null, report, "Task Report", JOptionPane.INFORMATION_MESSAGE);
        return report;
    }
    

    public static Task searchTaskByName(List<Task> tasks, String name) {
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(name)) {
                return task;
            }
        }
        return null;
    }

    public static boolean deleteTaskByName(List<Task> tasks, String name) {
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(name)) {
                tasks.remove(task);
                return true;
            }
        }
        return false;
    }

    public static Task findLongestTask(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return null;
        }

        Task longestTask = tasks.get(0);
        for (Task task : tasks) {
            if (task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }
        return longestTask;
    }

    public static int returnTotalHours() {
        int total = 0;
        for (int duration : allTaskDurations) {
            total += duration;
        }
        return total;
    }

    // Static method to get all developer names
    public static List<String> getAllDeveloperNames() {
        return allDeveloperNames;
    }

    // Static method to get all task names
    public static List<String> getAllTaskNames() {
        return allTaskNames;
    }

    // Static method to get all task IDs
    public static List<String> getAllTaskIDs() {
        return allTaskIDs;
    }

    // Static method to get all task durations
    public static List<Integer> getAllTaskDurations() {
        return allTaskDurations;
    }

    // Static method to get all task statuses
    public static List<String> getAllTaskStatuses() {
        return allTaskStatuses;
    }
}
