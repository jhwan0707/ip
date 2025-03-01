package johan.command;

import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Command to sort tasks in the task list.
 */
public class SortCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort();
        ui.showMessage("Tasks sorted: deadlines by date, others by name.");
        ui.showTaskList(tasks);
    }
}
