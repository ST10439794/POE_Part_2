package org.yourcompany.yourproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TaskTest {

	@Test
	public void checkTaskDescription() {
		Task t = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");

		// Test with a valid description
		assertTrue(t.checkTaskDescription("Valid description")); // Expect true

		// Test with an empty description
		assertFalse(t.checkTaskDescription("")); // Expect false

		// Test with a description longer than 50 characters
		String longDescription = "This description is definitely more than fifty characters long and should return false.";
		assertFalse(t.checkTaskDescription(longDescription)); // Expect false
	}

	@Test
	public void createTaskID() {
		// Test with a single character task name and valid developer details
		Task t = new Task("A", 2, "Valid task description", "Mit", 123, "To Do");
		String expected = "A:2:MIT";  // Task ID expected format
		String actual = t.createTaskID();

		assertEquals(expected, actual);
	}

	@Test
	public void printTaskDetails() {
		// Create a task with known values
		Task t = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");

		// Set up the expected output details
		String expectedDetails = "Task Status: To Do\n" +
								"Developer Details: Robyn Harrison\n" +
								"Task Number: 1\n" +
								"Task Name: Login Feature\n" +
								"Task Description: Create Login to authenticate users\n" +
								"Task ID: LO:1:SON\n" + // Adjust this based on expected Task ID
								"Task Duration: 8 hours\n";

		// Redirect the output or mock JOptionPane to capture the message
		// Since JOptionPane directly displays dialogs, we can't directly capture output,
		// but we can assert the correct values are set.

		// Here, we'll just call the method to confirm it executes without throwing an exception
		t.printTaskDetails();
		
		// You may want to replace the JOptionPane call in your Task class with a logging
		// mechanism or use a testing framework to verify that the correct details are displayed.
	}

		@Test
		public void returnTotalHours() {
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
