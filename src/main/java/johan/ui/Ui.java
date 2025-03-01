package johan.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

import johan.task.Deadline;
import johan.task.Event;
import johan.task.Task;
import johan.task.TaskList;

/**
 * Handles user interface interactions for the Johan task management application.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final Consumer<String> outputConsumer;

    public Ui() {
        this.outputConsumer = System.out::println;
    }
    public Ui(Consumer<String> outputConsumer) {
        this.outputConsumer = outputConsumer;
    }
    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        outputConsumer.accept("Hello! I'm johan.Johan");
        outputConsumer.accept("What can I do for you?");
        outputConsumer.accept("Type 'list' or 'sort' to get started!");
    }
    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        outputConsumer.accept("Bye. Hope to see you again soon!");
    }
    /**
     * Displays the list of tasks in the provided TaskList.
     *
     * @param tasks The TaskList containing tasks to display
     */
    public void showTaskList(TaskList tasks) {
        outputConsumer.accept("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            outputConsumer.accept((i + 1) + "." + tasks.getTask(i).toString());
        }
    }
    /**
     * Displays a confirmation message for a newly added task.
     *
     * @param task The task that was added
     * @param taskCount The total number of tasks in the list after adding
     */
    public void showTaskAdded(Task task, int taskCount) {
        outputConsumer.accept("Got it. I've added this task:");
        outputConsumer.accept(task.toString());
        outputConsumer.accept("Now you have " + taskCount + " tasks in the list.");
    }
    /**
     * Displays a confirmation message when a task's completion status is changed.
     *
     * @param task The task whose status was updated
     * @param isDone True if the task was marked as done, false if marked as not done
     */
    public void showTaskMarked(Task task, boolean isDone) {
        if (isDone) {
            outputConsumer.accept("Nice! I've marked this task as done:");
        } else {
            outputConsumer.accept("OK, I've marked this task as not done yet:");
        }
        outputConsumer.accept(task.toString());
    }
    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param task The task that was removed
     * @param taskCount The total number of tasks remaining in the list
     */
    public void showTaskDeleted(Task task, int taskCount) {
        outputConsumer.accept("____________________________________________________________");
        outputConsumer.accept("Noted. I've removed this task:");
        outputConsumer.accept(task.toString());
        outputConsumer.accept("Now you have " + taskCount + " tasks in the list.");
        outputConsumer.accept("____________________________________________________________");
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display
     */
    public void showError(String message) {
        outputConsumer.accept("____________________________________________________________");
        outputConsumer.accept(" OOPS!!! " + message);
        outputConsumer.accept("____________________________________________________________");
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
        outputConsumer.accept("____________________________________________________________");
    }
    /**
     * Displays tasks occurring on the specified date from the TaskList.
     *
     * @param tasks The TaskList to search for tasks
     * @param targetDate The date to filter tasks by
     */
    public void showTasksOnDate(TaskList tasks, LocalDate targetDate) {
        outputConsumer.accept("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy")) + ":");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getBy();
                if (deadline != null && deadline.equals(targetDate)) {
                    outputConsumer.accept((i + 1) + "." + task.toString());
                    found = true;
                }
            } else if (task instanceof Event) {
                LocalDate startDate = ((Event) task).getStartDate();
                LocalDate endDate = ((Event) task).getEndDate();
                if (startDate != null && endDate != null && !startDate.isAfter(targetDate)
                        && !endDate.isBefore(targetDate)) {
                    outputConsumer.accept((i + 1) + "." + task.toString());
                    found = true;
                }
            }
        }
        if (!found) {
            outputConsumer.accept("No tasks found on this date.");
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
    /**
     * Displays a generic message to the user.
     *
     * @param message The message to display
     */
    public void showMessage(String message) {
        outputConsumer.accept(message);
    }
}

