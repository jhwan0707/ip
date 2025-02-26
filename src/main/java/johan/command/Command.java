package johan.command;

import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Abstract base class for all commands in the application
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     * @param tasks The task list to operate on
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     * @throws Exception If an error occurs during execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Indicates whether this command should terminate the application.
     * @return true if this is an exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
