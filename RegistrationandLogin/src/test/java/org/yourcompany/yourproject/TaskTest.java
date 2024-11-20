package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

    private List<Task> tasksList;

    @BeforeEach
    public void setUp() {
        tasksList = new ArrayList<>();
        tasksList.add(new Task("Create Login", 1, "Create Login to authenticate users", "Mike Smith", 5, "To Do"));
        tasksList.add(new Task("Add Task Feature", 2, "Add Task features", "Edward Harrison", 8, "Doing"));
        tasksList.add(new Task("Create Reports", 3, "Create Reports", "Samantha Paulson", 2, "Done"));
        tasksList.add(new Task("Add Arrays", 4, "Add Arrays", "Glenda Oberholzer", 11, "To Do"));
    }

    @Test
    void testCheckTaskDescriptionSuccess() {
        Task task = new Task("Create Login", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");

        // Test that the description is valid
        boolean isValid = task.checkTaskDescription(task.getTaskDescription());
        assertTrue(isValid);  // Description is <= 50 characters, so this should pass
    }

    @Test
    void testCheckTaskDescriptionFailure() {
        // Initialize with a description longer than 50 characters
        String longDescription = "This description is definitely longer than fifty characters, and should fail validation";

        Task task = new Task("Long Task Name", 2, longDescription, "Mike Smith", 10, "Doing");

        // Test that the description is invalid
        boolean isValid = task.checkTaskDescription(task.getTaskDescription());
        assertFalse(isValid);  // Description exceeds 50 characters, so this should fail
    }

    @Test
    void testTaskIDGeneration() {
        Task task1 = new Task("Create Login", 1, "Short description", "Mike Smith", 5, "To Do");
        assertEquals("CR:1:IKE", task1.getTaskID(), "Task ID should match the expected format");

        Task task2 = new Task("Add Task Features", 2, "Short description", "Edward Harrison", 8, "Doing");
        assertEquals("AD:2:ARD", task2.getTaskID(), "Task ID should match the expected format");
    }

    @Test
    void testPrintTaskDetails() {
        Task task = tasksList.get(0); // "Login Feature"
        String expectedDetails
                = """
                Task Status: To Do
                Developer Details: Mike Smith
                Task Number: 1
                Task Name: Create Login
                Task Description: Create Login to authenticate users
                Task ID: CR:1:IKE
                Task Duration: 5 hours
                """;

        // Simulate printTaskDetails output (adapt logic if the real method uses dialogs)
        assertEquals(expectedDetails.trim(), task.printTaskDetails(), "Task details output is incorrect.");
    }

    @Test
    public void testDisplayReport() {
        String report = Task.displayReport(tasksList);  // Assuming displayReport reads all tasks
        String expectedReport = """
                                Task Report:
                                Task Name: Create Login
                                Task ID: CR:1:IKE
                                Description: Create Login to authenticate users
                                Developer: Mike Smith
                                Duration: 5 hours
                                Status: To Do
                                
                                Task Name: Add Task Feature
                                Task ID: AD:2:ARD
                                Description: Add Task features
                                Developer: Edward Harrison
                                Duration: 8 hours
                                Status: Doing
                                
                                Task Name: Create Reports
                                Task ID: CR:3:THA
                                Description: Create Reports
                                Developer: Samantha Paulson
                                Duration: 2 hours
                                Status: Done
                                
                                Task Name: Add Arrays
                                Task ID: AD:4:NDA
                                Description: Add Arrays
                                Developer: Glenda Oberholzer
                                Duration: 11 hours
                                Status: To Do
                                
                                """;
        assertEquals(expectedReport, report);
    }

    @Test
    void testFindLongestTask() {
        Task longestTask = Task.findLongestTask(tasksList);
        assertNotNull(longestTask, "Longest task should not be null.");
        assertEquals("Add Arrays", longestTask.getTaskName(), "Longest task name is correct.");
        assertEquals(11, longestTask.getTaskDuration(), "Longest task duration is correct.");
    }

    @Test
    void testSearchTaskByName() {
        Task task = Task.searchTaskByName(tasksList, "Create Login");
        assertNotNull(task, "Task search result should not be null.");
        assertEquals("Mike Smith", task.getDeveloperDetails(), "Developer details are correct.");
        assertEquals("Create Login", task.getTaskName(), "Task name is correct.");
    }

    @Test
    void testDeleteTaskByName() {
        boolean isDeleted = Task.deleteTaskByName(tasksList, "Create Login");
        assertTrue(isDeleted, "Task should be deleted.");
        assertNull(Task.searchTaskByName(tasksList, "Create Login"), "Deleted task should not be found.");
    }

    @Test
    void testReturnTotalHours() {
        // Clear the list of task durations before starting the test
        Task.getAllTaskDurations().clear();

        // Creating tasks
        tasksList.add(new Task("Create Login", 1, "Create Login to authenticate users", "Mike Smith", 5, "To Do"));
        tasksList.add(new Task("Create Add Feature", 2, "Create Add Task feature to add task users", "Edward Harrison", 8, "Doing"));
        tasksList.add(new Task("Create Reports", 3, "Create Reports", "Samantha Paulson", 2, "Done"));
        tasksList.add(new Task("Add Arrays", 4, "Add Arrays", "Glenda Oberholzer", 11, "To Do"));

        // Calling the method to get total hours
        int totalHours = Task.returnTotalHours();

        // Asserting that the total hours is 86
        assertEquals(26, totalHours);
    }

}
