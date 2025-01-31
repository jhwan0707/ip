public class Task {
    protected String description;
    protected boolean isDone;
    private static int nextTaskID = 1;
    private final int ID;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.ID = nextTaskID++;
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

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
