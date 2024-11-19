package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TaskTest {
    private List<Task> tasksList;

    @Test
    void testCheckTaskDescriptionSuccess() {
        Task task = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        
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
    Task task1 = new Task("Login Feature", 1, "Short description", "Robyn Harrison", 8, "To Do");
    assertEquals("LF:1:SON", task1.getTaskID(), "Task ID should match the expected format");

    Task task2 = new Task("Add Task Feature", 2, "Short description", "Mike Smith", 10, "To Do");
    assertEquals("AT:2:ITH", task2.getTaskID(), "Task ID should match the expected format");
}


    @Test
    void testPrintTaskDetails() {
        Task task = tasksList.get(0); // "Login Feature"
        String expectedDetails = 
                """
                Task Status: To Do
                Developer Details: Robyn Harrison
                Task Number: 1
                Task Name: Login Feature
                Task Description: Create Login to authenticate users
                Task ID: LF:1:SON
                Task Duration: 8 hours
                """;

        // Simulate printTaskDetails output (adapt logic if the real method uses dialogs)
        assertEquals(expectedDetails.trim(), task.printTaskDetails(), "Task details output is incorrect.");
    }

    @Test
    void testDisplayReport() {
        String expectedReport =
                """
                Task Report:
                Task Name: Login Feature
                Task ID: LF:1:SON
                Description: Create Login to authenticate users
                Developer: Robyn Harrison
                Duration: 8 hours
                Status: To Do

                Task Name: Add Task Feature
                Task ID: AT:2:ITH
                Description: Create Add Task feature to add task users
                Developer: Mike Smith
                Duration: 10 hours
                Status: Doing

                """;

        String actualReport = Task.displayReport(tasksList);
        assertEquals(expectedReport.trim(), actualReport.trim(), "Task report output is incorrect.");
    }

    @Test
    void testFindLongestTask() {
        Task longestTask = Task.findLongestTask(tasksList);
        assertNotNull(longestTask, "Longest task should not be null.");
        assertEquals("Add Task Feature", longestTask.getTaskName(), "Longest task name is incorrect.");
        assertEquals(10, longestTask.getTaskDuration(), "Longest task duration is incorrect.");
    }

    @Test
    void testSearchTaskByName() {
        Task task = Task.searchTaskByName(tasksList, "Login Feature");
        assertNotNull(task, "Task search result should not be null.");
        assertEquals("Robyn Harrison", task.getDeveloperDetails(), "Developer details are incorrect.");
        assertEquals("Login Feature", task.getTaskName(), "Task name is incorrect.");
    }

    @Test
    void testDeleteTaskByName() {
        boolean isDeleted = Task.deleteTaskByName(tasksList, "Add Task Feature");
        assertTrue(isDeleted, "Task should be deleted.");
        assertNull(Task.searchTaskByName(tasksList, "Add Task Feature"), "Deleted task should not be found.");
    }

    @Test
    void testReturnTotalHours() {
        // Clear the list of task durations before starting the test
        Task.getAllTaskDurations().clear();

        // Creating tasks
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do"));
        tasks.add(new Task("Add Task Feature", 2, "Create Add Task feature to add task users", "Mike Smith", 10, "Doing"));
        tasks.add(new Task("Another Task", 3, "Some task description", "John Doe", 12, "Done"));
        tasks.add(new Task("Final Task", 4, "Another task", "Jane Doe", 55, "In Progress"));
        tasks.add(new Task("Quick Task", 5, "Quick task", "Robert Brown", 1, "Completed"));

        // Calling the method to get total hours
        int totalHours = Task.returnTotalHours();

        // Asserting that the total hours is 86
        assertEquals(86, totalHours);  // The expected total is 86 (8 + 10 + 12 + 55 + 1 = 86)
    }



}
