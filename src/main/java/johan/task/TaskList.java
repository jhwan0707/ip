package johan.task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    /**
     * Constructs a TaskList with the specified initial tasks.
     *
     * @param tasks The initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Adds a task to the list.
     *
     * @param task The task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Deletes a task at the specified index.
     *
     * @param index The zero-based index of the task to delete
     * @return The deleted task
     * @throws IllegalArgumentException If the index is invalid
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        throw new IllegalArgumentException("Invalid task index.");
    }
    /**
     * Gets the task at the specified index.
     *
     * @param index The zero-based index of the task to retrieve
     * @return The task at the specified index
     * @throws IllegalArgumentException If the index is invalid
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        throw new IllegalArgumentException("Invalid task index.");
    }
    /**
     * Gets the complete list of tasks.
     *
     * @return The ArrayList containing all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    /**
     * Gets the number of tasks in the list.
     *
     * @return The size of the task list
     */
    public int size() {
        return tasks.size();
    }
    /**
     * Sorts the task list chronologically for deadlines, alphabetically otherwise.
     */
    public void sort() {
        Collections.sort(tasks);
    }
}
