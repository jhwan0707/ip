package johan.task;
/**
 * Represents a simple todo task without a deadline.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The task description
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns a string representation of the todo task.
     *
     * @return The formatted string with "[T]" prefix
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
