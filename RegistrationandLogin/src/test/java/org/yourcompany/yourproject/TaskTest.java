package org.yourcompany.yourproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {

    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        // Instantiate the Task objects before each test
        task1 = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        task2 = new Task("Add Task Feature", 2, "Create Add Task feature to add task users", "Mike Smith", 10, "Doing");
    }

    @Test
    public void checkTaskDescription() {
        // Test with a valid description
        assertTrue(task1.checkTaskDescription("Valid description")); // Expect true
        assertTrue(task2.checkTaskDescription("Valid description")); // Expect true

        // Test with an empty description
        assertFalse(task1.checkTaskDescription("")); // Expect false
        assertFalse(task2.checkTaskDescription("")); // Expect false

        // Test with a description longer than 50 characters
        String longDescription = "This description is definitely more than fifty characters long and should return false.";
        assertFalse(task1.checkTaskDescription(longDescription)); // Expect false
        assertFalse(task2.checkTaskDescription(longDescription)); // Expect false
    }

    @Test
    public void createTaskID() {
        // Test with a single character task name and valid developer details
        Task task1 = new Task("A", 2, "Valid task description", "Mit", 123, "To Do");
        String expected1 = "A:2:MIT";  // Task ID expected format
        String actual1 = task1.createTaskID();

        Task task2 = new Task("B", 3, "Valid task description", "Cit", 456, "Doing");
        String expected2 = "B:3:CIT";  // Task ID expected format
        String actual2 = task2.createTaskID();

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void printTaskDetails() {
        // Create a task with known values
        // Output will be printed to the console; no assertions are made here
        task1.printTaskDetails();
        task2.printTaskDetails();
    }

    @Test
    public void returnTotalHours() {
        // Clear previous task durations to ensure a clean test environment
        Task.clearTaskDurations();

        // Create multiple tasks with varying durations
        new Task("Task 1", 0, "First Task", "Dev A", 10, "To Do");
        new Task("Task 2", 1, "Second Task", "Dev B", 12, "Doing");
        new Task("Task 3", 2, "Third Task", "Dev C", 55, "Done");
        new Task("Task 4", 3, "Fourth Task", "Dev D", 11, "To Do");
        new Task("Task 5", 4, "Fifth Task", "Dev E", 1, "Doing");

        int totalHours = Task.returnTotalHours();
        assertEquals(89, totalHours, "Total hours should be correctly accumulated to 89.");

        // Clear and add additional data to test
        Task.clearTaskDurations();
        new Task("Task 1", 0, "Task 1 description", "Dev F", 10, "To Do");
        new Task("Task 2", 1, "Task 2 description", "Dev G", 12, "Doing");

        totalHours = Task.returnTotalHours();
        assertEquals(22, totalHours, "Total hours should be correctly accumulated to 22.");
    }
}
