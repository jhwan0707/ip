package johan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract base class representing a task in the application.
 */
public abstract class Task implements Comparable<Task> {
    private static int nextTaskID = 1;
    protected String description;
    protected boolean isDone;
    protected LocalDate deadline;
    private final int id;
    /**
     * Constructs a Task with the specified description.
     * @param description The task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.id = nextTaskID++;
        this.deadline = null;
    }

    /**
     * Gets the unique ID of the task as a string.
     *
     * @return The task ID
     */
    public String getID() {
        return Integer.toString(this.id);
    }
    /**
     * Gets the status icon representing whether the task is done.
     *
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }
    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }
    /**
     * Gets the deadline of the task, if any.
     *
     * @return The deadline date, or null if not set
     */
    public LocalDate getDeadline() {
        return deadline;
    }
    /**
     * Sets the deadline for the task.
     *
     * @param deadline The deadline date to set
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Gets the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string including status and deadline if present
     */
    @Override
    public String toString() {
        String baseString = "[" + this.getStatusIcon() + "] " + description;
        if (deadline != null) {
            baseString += "(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return baseString;
    }
    /**
     * Compares tasks alphabetically by description as a default.
     *
     * @param other The other task to compare to
     * @return Negative if this task comes before, positive if after, zero if equal
     */
    @Override
    public int compareTo(Task other) {
        return this.description.compareTo(other.description);
    }
}
