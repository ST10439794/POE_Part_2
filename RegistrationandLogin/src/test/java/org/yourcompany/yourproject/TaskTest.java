package org.yourcompany.yourproject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
    private List<Task> tasksList;


    @BeforeEach
    void setUp() {
        tasksList = new ArrayList<>();
        tasksList.add(new Task("Create Login", 1, "Implement user login feature", "Mike Smith", 5, "To Do"));
        tasksList.add(new Task("Add Security", 2, "Enhance app security", "Edward Harrington", 8, "Doing"));
        tasksList.add(new Task("Create Reports", 3, "Generate user reports", "Samantha Paulson", 7, "Done"));
        tasksList.add(new Task("Optimize Database", 4, "Optimize database performance", "Glenda Oberholzer", 11, "Doing"));
    }

    @Test
    void testDeveloperArrayCorrectlyPopulated() {
        List<String> developers = new ArrayList<>();
        for (Task task : tasksList) {
            developers.add(task.getDeveloperDetails());
        }

        assertEquals(List.of("Mike Smith", "Edward Harrington", "Samantha Paulson", "Glenda Oberholzer"), developers);
    }

    @Test
    void testLongestDurationTask() {
        Task longestTask = Task.findLongestTask(tasksList);
        assertNotNull(longestTask);
        assertEquals("Glenda Oberholzer", longestTask.getDeveloperDetails());
        assertEquals(11, longestTask.getTaskDuration());
    }

    @Test
    void testSearchTaskByName() {
        Task task = Task.searchTaskByName(tasksList, "Create Login");
        assertNotNull(task);
        assertEquals("Mike Smith", task.getDeveloperDetails());
        assertEquals("Create Login", task.getTaskName());
    }

    @Test
    void testSearchTasksByDeveloper() {
        List<Task> developerTasks = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.getDeveloperDetails().equals("Samantha Paulson")) {
                developerTasks.add(task);
            }
        }

        assertEquals(1, developerTasks.size());
        assertEquals("Create Reports", developerTasks.get(0).getTaskName());
    }

    @Test
    void testDeleteTaskByName() {
        boolean isDeleted = Task.deleteTaskByName(tasksList, "Create Reports");
        assertTrue(isDeleted);
        assertNull(Task.searchTaskByName(tasksList, "Create Reports"));
    }

    @Test
    void testDisplayReport() {
        String report = Task.displayReport(tasksList);
        String expectedReport = 
                "Task Report:\n" +
                "Task Name: Create Login\n" + 
                "Task ID: CR:1:ITH\n" + 
                "Description: Implement user login feature\n" + 
                "Developer: Mike Smith\n" + 
                "Duration: 5 hours\n" + 
                "Status: To Do\n\n" + 

                "Task Name: Add Security\n" + 
                "Task ID: AD:2:TON\n" + 
                "Description: Enhance app security\n" + 
                "Developer: Edward Harrington\n" + 
                "Duration: 8 hours\n" + 
                "Status: Doing\n\n" + 

                "Task Name: Create Reports\n" + 
                "Task ID: CR:3:SON\n" + 
                "Description: Generate user reports\n" + 
                "Developer: Samantha Paulson\n" + 
                "Duration: 7 hours\n" + 
                "Status: Done\n\n" + 

                "Task Name: Optimize Database\n" + 
                "Task ID: OP:4:ZER\n" + 
                "Description: Optimize database performance\n" + 
                "Developer: Glenda Oberholzer\n" + 
                "Duration: 11 hours\n" + 
                "Status: Doing\n\n";

        assertEquals(expectedReport, report);
    }
}
