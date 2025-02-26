package johan.command;

import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Displays all tasks in the task list.
     * @param tasks The task list to display
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
