package johan.command;

import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Performs no action as termination is handled by isExit().
     * @param tasks The task list to operate on
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No action needed; isExit() handles termination
    }

    /**
     * Indicates that this command terminates the application.
     * @return true always
     */
    @Override
    public boolean isExit() {
        return true;
    }
}