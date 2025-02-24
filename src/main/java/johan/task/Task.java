package johan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static int nextTaskID = 1;
    private final int ID;
    protected LocalDate deadline;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.ID = nextTaskID++;
        this.deadline = null;
    }

    public String getID() {
        return Integer.toString(this.ID);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String baseString = "[" + this.getStatusIcon() + "] " + description;
        if (deadline != null) {
            baseString += "(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return baseString;
    }
}
