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
        if (taskDescription == null || taskDescription.length() > 50) {
            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Task successfully captured", "Success", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    

    public String createTaskID() {
        // Generate task prefix based on the first two letters of the task name.
        String taskPrefix = taskName.substring(0, 1).toUpperCase(); // Get the first letter of the task name.
        if (taskName.length() > 1 && taskName.contains(" ")) {
            // Get the first letter of the second word (if available).
            taskPrefix += taskName.substring(taskName.indexOf(" ") + 1, taskName.indexOf(" ") + 2).toUpperCase();
        } else {
            taskPrefix = taskPrefix.toUpperCase();
        }
    
        // Generate developer suffix using the last three characters of the developer's name.
        String developerSuffix = developerDetails.length() > 2 ? developerDetails.substring(developerDetails.length() - 3).toUpperCase() : developerDetails.toUpperCase();
    
        return taskPrefix + ":" + taskNumber + ":" + developerSuffix;
    }
    
    public String printTaskDetails() {
        String details = 
                "Task Status: " + taskStatus + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Number: " + taskNumber + "\n" +
                "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Task ID: " + taskID + "\n" +
                "Task Duration: " + taskDuration + " hours";
    
        JOptionPane.showMessageDialog(null, details, "Task Details", JOptionPane.INFORMATION_MESSAGE);
        return details;
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

    public static void setAllDeveloperNames(List<String> allDeveloperNames) {
        Task.allDeveloperNames = allDeveloperNames;
    }

    public static void setAllTaskNames(List<String> allTaskNames) {
        Task.allTaskNames = allTaskNames;
    }

    public static void setAllTaskIDs(List<String> allTaskIDs) {
        Task.allTaskIDs = allTaskIDs;
    }

    public static void setAllTaskDurations(List<Integer> allTaskDurations) {
        Task.allTaskDurations = allTaskDurations;
    }

    public static void setAllTaskStatuses(List<String> allTaskStatuses) {
        Task.allTaskStatuses = allTaskStatuses;
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

    // Static method to display the report of all tasks
    public static String displayReport(List<Task> tasks) {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks to display.", "Task Report", JOptionPane.INFORMATION_MESSAGE);
            return "No tasks to display.";
        }
    
        StringBuilder report = new StringBuilder("Task Report:\n");
        for (Task task : tasks) {
            report.append("Task Name: ").append(task.getTaskName()).append("\n")
                  .append("Task ID: ").append(task.getTaskID()).append("\n")
                  .append("Description: ").append(task.getTaskDescription()).append("\n")
                  .append("Developer: ").append(task.getDeveloperDetails()).append("\n")
                  .append("Duration: ").append(task.getTaskDuration()).append(" hours\n")
                  .append("Status: ").append(task.getTaskStatus()).append("\n\n");
        }
    
        JOptionPane.showMessageDialog(null, report.toString(), "Task Report", JOptionPane.INFORMATION_MESSAGE);
        return report.toString();
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
}
