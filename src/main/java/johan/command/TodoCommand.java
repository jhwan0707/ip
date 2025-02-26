package johan.command;

import johan.storage.Storage;
import johan.task.Task;
import johan.task.TaskList;
import johan.task.Todo;
import johan.ui.Ui;

/**
 * Command to add a todo task to the task list
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs a TodoCommand with the specified description.
     * @param description The description of the todo task
     */
    public TodoCommand(String description) {
        super(description);
    }

    /**
     * Adds a todo task to the task list and updates storage.
     * @param tasks The task list to modify
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     * @throws IllegalArgumentException If the description is empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
