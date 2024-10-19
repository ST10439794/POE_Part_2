package org.yourcompany.yourproject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KhanbanTasksTest {
    private KhanbanTasks khanbanTasks;

    @BeforeEach
    public void setUp() {
        khanbanTasks = new KhanbanTasks(); // Initialize the KhanbanTasks before each test
    }

    @Test
    public void testAddTask() {
        // Add a task directly
        khanbanTasks.addTask("Task 1", "Description for Task 1", "Developer A", 2, "To Do");
        List<String> tasks = khanbanTasks.getTasks();
        assertEquals(1, tasks.size(), "There should be 1 task in the list");
        assertTrue(tasks.contains("Task 1"), "Task 1 should be in the list");
    }

    @Test
    public void testRemoveTask() {
        khanbanTasks.addTask("Task 1", "Description for Task 1", "Developer A", 2, "To Do");
        khanbanTasks.removeTask("Task 1");
        List<String> tasks = khanbanTasks.getTasks();
        assertEquals(0, tasks.size(), "There should be no tasks in the list after removal");
    }

    @Test
    public void testGetTasks() {
        khanbanTasks.addTask("Task 1", "Description for Task 1", "Developer A", 2, "To Do");
        khanbanTasks.addTask("Task 2", "Description for Task 2", "Developer B", 3, "Doing");
        List<String> tasks = khanbanTasks.getTasks();
        assertEquals(2, tasks.size(), "There should be 2 tasks in the list");
        assertTrue(tasks.contains("Task 1"), "Task 1 should be in the list");
        assertTrue(tasks.contains("Task 2"), "Task 2 should be in the list");
    }
}
