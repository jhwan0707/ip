package johan.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import johan.task.Deadline;
import johan.task.Event;
import johan.task.Task;
import johan.task.TaskList;

/**
 * Handles user interface interactions for the Johan task management application.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm johan.Johan");
        System.out.println("What can I do for you?");
    }
    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    /**
     * Displays the list of tasks in the provided TaskList.
     *
     * @param tasks The TaskList containing tasks to display
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }
    }
    /**
     * Displays a confirmation message for a newly added task.
     *
     * @param task The task that was added
     * @param taskCount The total number of tasks in the list after adding
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    /**
     * Displays a confirmation message when a task's completion status is changed.
     *
     * @param task The task whose status was updated
     * @param isDone True if the task was marked as done, false if marked as not done
     */
    public void showTaskMarked(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task.toString());
    }
    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param task The task that was removed
     * @param taskCount The total number of tasks remaining in the list
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" OOPS!!! " + message);
        System.out.println("____________________________________________________________");
    }
    /**
     * Reads a command from the user input.
     *
     * @return The user's command as a lowercase, trimmed string
     */
    public String readCommand() {
        return scanner.nextLine().toLowerCase().trim();
    }
    /**
     * Displays a horizontal line as a visual separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays tasks occurring on the specified date from the TaskList.
     *
     * @param tasks The TaskList to search for tasks
     * @param targetDate The date to filter tasks by
     */
    public void showTasksOnDate(TaskList tasks, LocalDate targetDate) {
        System.out.println("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy")) + ":");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getBy();
                if (deadline != null && deadline.equals(targetDate)) {
                    System.out.println((i + 1) + "." + task.toString());
                    found = true;
                }
            } else if (task instanceof Event) {
                LocalDate startDate = ((Event) task).getStartDate();
                LocalDate endDate = ((Event) task).getEndDate();
                if (startDate != null && endDate != null && !startDate.isAfter(targetDate)
                        && !endDate.isBefore(targetDate)) {
                    System.out.println((i + 1) + "." + task.toString());
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No tasks found on this date.");
        }
    }

    /**
     * Displays tasks whose descriptions contain the search keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword
     */
    public void showFoundTasks(ArrayList<Task> matchingTasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        if (matchingTasks.isEmpty()) {
            System.out.println(" No matching tasks found.");
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
        System.out.println("____________________________________________________________");
    }
}
