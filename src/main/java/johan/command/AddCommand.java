package johan.command;

import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Abstract base class for commands that add tasks to the task list.
 */
public abstract class AddCommand extends Command {
    protected final String description;

    /**
     * Constructs an AddCommand with the specified task description.
     *
     * @param description the description of the task to be added
     */
    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param tasks The task list to modify
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     * @throws Exception If an error occurs during exception
     */
    @Override
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}




