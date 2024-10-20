package org.yourcompany.yourproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        // Reset the static list of durations before each test
        Task.clearTaskDurations(); // Add this method to clear durations if necessary
    }

    @Test
    void testTaskDescriptionValidation() {
        // Test valid description
        task1 = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        assertNotNull(task1.getTaskID(), "Task should be created successfully with a valid description.");

        // Test invalid description
        task2 = new Task("Invalid Task", 2, "This description is way too long and exceeds the maximum length of fifty characters.", "Mike Smith", 10, "Doing");
        assertNull(task2.getTaskID(), "Task ID should be null for invalid task description.");
    }

    @Test
    void testTaskIDGeneration() {
        // Create task with valid description
        task1 = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        assertEquals("LO:1:SON", task1.getTaskID(), "Task ID should be correctly generated.");

        task2 = new Task("Add Task Feature", 2, "Create Add Task feature to add task users", "Mike Smith", 10, "Doing");
        assertEquals("AD:2:ITH", task2.getTaskID(), "Task ID should be correctly generated.");
    }

    @Test
    void testTotalHoursAccumulation() {
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

    // Additional tests can be added here as necessary
}
